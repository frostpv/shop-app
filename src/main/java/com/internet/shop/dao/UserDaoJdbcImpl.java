package com.internet.shop.dao;

import com.internet.shop.exceptions.DataProcessingException;
import com.internet.shop.lib.Dao;
import com.internet.shop.models.Role;
import com.internet.shop.models.User;
import com.internet.shop.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Dao
public class UserDaoJdbcImpl implements UserDao {
    @Override
    public Optional<User> findByLogin(String login) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM users"
                    + " JOIN user_roles ON user_roles.user_id = users.user_id"
                    + " JOIN roles ON  roles.role_id = user_roles.role_id"
                    + " WHERE user_login = ? AND deleted = false";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            return Optional.ofNullable(createUserObject(resultSet));
        } catch (SQLException e) {
            throw new DataProcessingException("User by Login " + login
                    + "is not found", e);
        }
    }

    @Override
    public User create(User user) {
        String query = "INSERT INTO users (user_name, user_login, user_pass, salt) "
                + "VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement
                     = ConnectionUtil.getConnection().prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setBytes(4, user.getSalt());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("User is not created with login "
                    + user.getLogin(), e);
        }
        setIdToRoles(user.getRoles());
        addRolesToUser(user);
        return user;
    }

    @Override
    public Optional<User> get(Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM users "
                    + " JOIN user_roles ON users.user_id = user_roles.user_id"
                    + " JOIN roles ON user_roles.role_id = roles.role_id"
                    + " WHERE users.user_id = ? AND deleted = false";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return Optional.ofNullable(createUserObject(resultSet));
        } catch (SQLException e) {
            throw new DataProcessingException("User by id " + id
                    + "is not found", e);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users WHERE deleted = false";
        try (PreparedStatement preparedStatement =
                     ConnectionUtil.getConnection().prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = setUserFields(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Cant get all list of users", e);
        }
        users.forEach(this::getUserRoles);
        return users;
    }

    @Override
    public User update(User user) {
        String query = "UPDATE users "
                + "SET user_name = ?, user_login = ?, user_pass = ? salt = ?"
                + "WHERE user_id = ? AND deleted != true";
        try (PreparedStatement preparedStatement =
                     ConnectionUtil.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setBytes(4, user.getSalt());
            preparedStatement.setLong(5, user.getId());
            preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        addRolesToUser(user);
        return user;
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE users SET deleted = true WHERE user_id = ?";
        deleteUserRoles(id);
        try (PreparedStatement preparedStatement =
                     ConnectionUtil.getConnection().prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DataProcessingException("cant to deleted user with id " + id, e);
        }
    }

    private void deleteUserRoles(Long id) {
        String query = "DELETE * FROM user_roles WHERE user_id = ?";
        try (PreparedStatement preparedStatement =
                     ConnectionUtil.getConnection().prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Cant delete roles to user "
                    + id,e);
        }
    }

    private User setUserFields(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("user_id"));
        user.setName(resultSet.getString("user_name"));
        user.setLogin(resultSet.getString("user_login"));
        user.setPassword(resultSet.getString("user_pass"));
        user.setSalt(resultSet.getBytes("salt"));
        return user;
    }

    private Role getRolesWithId(ResultSet resultSet) throws SQLException {
        Role role = Role.of(resultSet.getString("role_name"));
        role.setId(resultSet.getLong("role_id"));
        return role;
    }

    private void setIdToRoles(Set<Role> roles) {
        String query = "SELECT * FROM roles";
        try (PreparedStatement preparedStatement =
                     ConnectionUtil.getConnection().prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                for (Role role : roles) {
                    if (role.getRoleName().name().equals(resultSet.getString("role_name"))) {
                        role.setId(resultSet.getLong("role_id"));
                    }
                }
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get list of roles from data", e);
        }
    }

    private void getUserRoles(User user) {
        Set<Role> roles = new HashSet<>();
        String query = "SELECT * FROM user_roles "
                + "JOIN roles ON roles.role_id = user_roles.role_id "
                + "WHERE user_id = ?";
        try (PreparedStatement preparedStatement =
                     ConnectionUtil.getConnection().prepareStatement(query)) {
            preparedStatement.setLong(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                roles.add(getRolesWithId(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get user roles to user id "
                    + user.getId(), e);
        }
        user.setRoles(roles);
    }

    private void addRolesToUser(User user) {
        String query = "INSERT INTO user_roles (user_id, role_id) VALUES (?, ?)";
        try (PreparedStatement preparedStatement =
                     ConnectionUtil.getConnection().prepareStatement(query)) {
            for (Role role : user.getRoles()) {
                preparedStatement.setLong(1, user.getId());
                preparedStatement.setLong(2, role.getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't add roles to user "
            + user.getId(), e);
        }
    }

    private User createUserObject(ResultSet resultSet) throws SQLException {
        User user;
        Set<Role> roles = new HashSet<>();
        if (resultSet.next()) {
            user = setUserFields(resultSet);
            roles.add(getRolesWithId(resultSet));
        } else {
            return null;
        }
        while (resultSet.next()) {
            roles.add(getRolesWithId(resultSet));
            user.setRoles(roles);
        }
        user.setRoles(roles);
        return user;
    }

}
