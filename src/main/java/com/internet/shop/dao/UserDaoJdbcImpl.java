package com.internet.shop.dao;

import com.internet.shop.exceptions.DataBaseProcessingException;
import com.internet.shop.lib.Dao;
import com.internet.shop.models.User;
import com.internet.shop.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Dao
public class UserDaoJdbcImpl implements UserDao {
    @Override
    public Optional<User> findByLogin(String login) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM users Where deleted = false AND user_login = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                User user = new User(
                        resultSet.getNString("user_name"),
                        resultSet.getNString("user_login"),
                        resultSet.getNString("user_pass")
                );
                user.setId(resultSet.getLong("user_id"));
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new DataBaseProcessingException("User by Login " + login
                    + "is not found", e);
        }
        return Optional.empty();
    }

    @Override
    public User create(User user) {
      return user;
    }

    @Override
    public Optional<User> get(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User update(User item) {
        return null;
    }

    @Override
    public boolean delete(Long item) {
        return false;
    }
}
