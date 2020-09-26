package com.internet.shop.dao;

import com.internet.shop.exceptions.DataBaseProcessingException;
import com.internet.shop.lib.Dao;
import com.internet.shop.models.Order;
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
public class OrderDaoJdbcImpl implements OrderDao {
    @Override
    public List<Order> getUserOrders(Long userId) {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM orders WHERE deleted = FALSE AND user_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("order_id"));
                order.setIdUser(resultSet.getLong("user_id"));
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DataBaseProcessingException("User's orders list"
                    + " was not created, user id: " + userId, e);
        }
        for (Order order : orders) {
            getOrderProducts(order);
        }
        return orders;
    }

    @Override
    public Order create(Order order) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "INSERT INTO orders (user_id) VALUES (?)";
            PreparedStatement preparedStatement
                    = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, order.getIdUser());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                order.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new DataBaseProcessingException("Order witch id "
                    + order.getId() + " was not created", e);
        }
        return addProductsIntoOrder(order);
    }

    @Override
    public Optional<Order> get(Long id) {
        Order order = new Order();
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM orders "
                    + "WHERE order_id = ? AND deleted = FALSE";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order.setIdUser(resultSet.getLong("user_id"));
                order.setId(id);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DataBaseProcessingException("Order with id "
                    + id + " was not created", e);
        }
        getOrderProducts(order);
        return Optional.of(order);
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM orders WHERE deleted = FALSE";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("order_id"));
                order.setIdUser(resultSet.getLong("user_id"));
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DataBaseProcessingException("All orders order list was not created", e);
        }
        for (Order order : orders) {
            getOrderProducts(order);
        }
        return orders;
    }

    @Override
    public Order update(Order order) {
        deleteProductsInOrder(order.getId());
        return addProductsIntoOrder(order);
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "UPDATE orders "
                    + "SET deleted = TRUE WHERE order_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DataBaseProcessingException("Order is not deleted", e);
        }
    }

    private boolean deleteProductsInOrder(Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "DELETE FROM orders_products WHERE order_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DataBaseProcessingException("Order product in order "
                    + id + " was not deleted", e);
        }
    }

    private Order addProductsIntoOrder(Order order) {
        String query = "INSERT INTO orders_products (order_id, product_id) "
                + "VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for (Product product : order.getProducts()) {
                preparedStatement.setLong(1, order.getId());
                preparedStatement.setLong(2, product.getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            return order;
        } catch (SQLException e) {
            throw new DataBaseProcessingException("Failed to add the products to"
                    + order, e);
        }
    }

    private void getOrderProducts(Order order) {
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM products JOIN orders_products "
                    + "ON products.product_id = orders_products.product_id "
                    + "WHERE orders_products.order_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, order.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getString("name"),
                        resultSet.getDouble("price"));
                product.setId(resultSet.getLong("product_id"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new DataBaseProcessingException("Order "
                    + order + " have problem which product list", e);
        }
        order.setProducts(products);
    }
}
