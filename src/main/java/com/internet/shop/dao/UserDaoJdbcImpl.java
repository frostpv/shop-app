package com.internet.shop.dao;

import com.internet.shop.exceptions.DataBaseProcessingException;
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
        User user = new User();
        Set<Role> roles = new HashSet<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM users"
                    + " JOIN user_roles ON user_roles.user_id = users.user_id"
                    + " JOIN roles ON  roles.role_id = user_roles.role_id"
                    + " WHERE user_login = ? AND deleted = false";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = setUserFields(resultSet);
                roles.add(getRoleWichId(resultSet));
            }

            while (resultSet.next()) {
                roles.add(getRoleWichId(resultSet));
                user.setRoles(roles);
            }
            user.setRoles(roles);
            return Optional.of(user);
        } catch (SQLException e) {
            throw new DataBaseProcessingException("User by Login " + login
                    + "is not found", e);
        }
    }

    @Override
    public User create(User user) {
        String query = "INSERT INTO users (user_name, user_login, user_pass) "
                + "VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement
                     = ConnectionUtil.getConnection().prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new DataBaseProcessingException("User is not create", e);
        }
        setIdToRoles(user.getRoles());
        addRolesToUser(user);
        return user;
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Optional<User> get(Long id) {
        User user = new User();
        Set<Role> roles = new HashSet<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM users "
                    + " JOIN user_roles ON users.user_id = user_roles.user_id"
                    + " JOIN roles ON user_roles.role_id = roles.role_id"
                    + " WHERE users.user_id = ? AND deleted = false";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = setUserFields(resultSet);
                roles.add(getRoleWichId(resultSet));
            }
            while (resultSet.next()) {
                roles.add(getRoleWichId(resultSet));
                user.setRoles(roles);
            }
            user.setRoles(roles);
            return Optional.of(user);
        } catch (SQLException e) {
            throw new DataBaseProcessingException("User by id " + id
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        users.forEach(this::getUserRoles);
        return users;
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
                roles.add(getRoleWichId(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        user.setRoles(roles);
    }

    @Override
    public User update(User user) {
        String query = "UPDATE users "
                + "SET user_name = ?, user_login = ?, user_pass = ? "
                + "WHERE user_id = ?";
        try (PreparedStatement preparedStatement =
                     ConnectionUtil.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setLong(4, user.getId());
            preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        deleteUserRoles(user.getId());
        setIdToRoles(user.getRoles());
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
            throw new DataBaseProcessingException("cant to deleted", e);
        }
    }

    private void deleteUserRoles(Long id) {
        String query = "DELETE * FROM user_roles WHERE user_id = ?";
        try (PreparedStatement preparedStatement =
                     ConnectionUtil.getConnection().prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private User setUserFields(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("user_id"));
        user.setName(resultSet.getString("user_name"));
        user.setLogin(resultSet.getString("user_login"));
        user.setPassword(resultSet.getString("user_pass"));
        return user;
    }

    private Role getRoleWichId(ResultSet resultSet) throws SQLException {
        Role role = Role.of(resultSet.getString("role_name"));
        role.setId(resultSet.getLong("role_id"));
        return role;
    }
}
