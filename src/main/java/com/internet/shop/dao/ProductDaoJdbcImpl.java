package com.internet.shop.dao;

import com.internet.shop.exceptions.DataBaseProcessingException;
import com.internet.shop.lib.Dao;
import com.internet.shop.models.Product;
import com.internet.shop.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class ProductDaoJdbcImpl implements ProductDao {
    @Override
    public Product create(Product product) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "INSERT INTO products(name, price) VALUES(?, ?)";
            PreparedStatement preparedStatement
                    = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                product.setId(resultSet.getLong(1));
            }
            return product;
        } catch (SQLException e) {
            throw new DataBaseProcessingException("Product " + product + " was not created", e);
        }
    }

    @Override
    public Optional<Product> get(Long id) {
        String query = "SELECT * FROM products WHERE product_id = ? AND deleted = false ";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(fetchProduct(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataBaseProcessingException("Can`t get product with id " + id, e);
        }
    }

    @Override
    public Product update(Product product) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query
                    = "UPDATE products SET name = ?, price = ? "
                    + "WHERE product_id = ? AND deleted = false ";
            PreparedStatement preparedStatement
                    = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setLong(3, product.getId());
            preparedStatement.executeUpdate();
            return product;
        } catch (SQLException e) {
            throw new DataBaseProcessingException("Product " + product + " not updated", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "UPDATE products SET deleted = true WHERE product_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DataBaseProcessingException("Product with id - " + id + " not deleted", e);
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM products WHERE deleted = false ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(fetchProduct(resultSet));
            }
        } catch (SQLException e) {
            throw new DataBaseProcessingException("All products error", e);
        }
        return products;
    }

    private Product fetchProduct(ResultSet resultSet) throws SQLException {
        long productId = resultSet.getLong("product_id");
        String name = resultSet.getString("name");
        double price = resultSet.getDouble("price");
        Product product = new Product(name, price);
        product.setId(productId);
        return product;
    }
}
