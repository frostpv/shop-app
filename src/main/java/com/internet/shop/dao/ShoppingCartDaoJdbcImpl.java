package com.internet.shop.dao;

import com.internet.shop.exceptions.DataBaseProcessingException;
import com.internet.shop.lib.Dao;
import com.internet.shop.models.ShoppingCart;
import com.internet.shop.util.ConnectionUtil;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Dao
public class ShoppingCartDaoJdbcImpl implements ShoppingCartDao {
    @Override
    public Optional<ShoppingCart> getByUserId(Long userId) {
        return Optional.empty();
    }

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "INSERT INTO `internet_shop`.`shoping_cart` (`id_user`) VALUES (?)";
            PreparedStatement preparedStatement
                    = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, shoppingCart.getUserId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                shoppingCart.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new DataBaseProcessingException("Shoping cart " + shoppingCart + " was not created", e);
        }
        return shoppingCart;
    }

    @Override
    public Optional<ShoppingCart> get(Long id) {
        return Optional.empty();
    }

    @Override
    public List<ShoppingCart> getAll() {
        return null;
    }

    @Override
    public ShoppingCart update(ShoppingCart item) {
        return null;
    }

    @Override
    public boolean delete(Long item) {
        return false;
    }
}
