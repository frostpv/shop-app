package com.internet.shop.dao;

import com.internet.shop.exceptions.DataBaseProcessingException;
import com.internet.shop.models.User;
import com.internet.shop.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class UserDaoJdbcImpl implements UserDao {
    @Override
    public Optional<User> findByLogin(String login) {
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
