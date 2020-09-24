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
import java.util.stream.Collectors;

@Dao
public class OrderDaoJdbcImpl implements OrderDao {
    @Override
    public List<Order> getUserOrders(Long userId) {
       return getAll().stream()
               .filter(order -> order.getIdUser().equals(userId))
               .collect(Collectors.toList());
    }

    @Override
    public Order create(Order order) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "INSERT INTO `internet_shop`.`orders` (`user_id`) VALUES (?)";
            PreparedStatement preparedStatement
                    = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, order.getIdUser());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                order.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new DataBaseProcessingException("Shoping cart "
                    + order + " was not created", e);
        }
        return addProductsIntoOrder(order);
    }

    private Order addProductsIntoOrder(Order order){
        String query = "INSERT INTO orders_products (order_id, product_id) "
                + "VALUES (?, ?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, order.getId());
            for (Product product : order.getProducts()) {
                statement.setLong(2, product.getId());
                statement.executeUpdate();
            }
            return order;
        } catch (SQLException e) {
            throw new DataBaseProcessingException("Failed to add the products to"
                    + order, e);
        }
    }

    @Override
    public Optional<Order> get(Long id) {
        Order order = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM internet_shop.shoping_cart "
                    + "WHERE id_shoping_cart = ? AND deleted = FALSE";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order = new Order(resultSet.getLong("id_user"));
                order.setId(id);
                order.setProducts(getOrderProducts(order.getId()));
                return Optional.of(order);
            }
        } catch (SQLException e) {
            throw new DataBaseProcessingException("Shoping cart "
                    + order + " was not created", e);
        }
        return Optional.empty();
    }

    List<Product> getOrderProducts(Long id){
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM products JOIN shoping_cart_products \n"
                    + "ON products.product_id = shoping_cart_products.id_product \n"
                    + "WHERE shoping_cart_products.id_cart=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
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
                    + id + " have problem which product list", e);
        }
        return products;
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM orders WHERE deleted = FALSE";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order(resultSet.getLong("order_id"));
                order.setIdUser(resultSet.getLong("user_id"));
                order.setProducts(getOrderProducts(order.getId()));
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DataBaseProcessingException("Order list was not created", e);
        }
        return orders;
    }

    @Override
    public Order update(Order order) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "UPDATE orders "
                    + "SET user_id = ? WHERE order_id =?"
                    + "AND deleted = FALSE";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, order.getIdUser());
            preparedStatement.setString(2, order.getId().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseProcessingException("Shopping " + order
                    + " was not created updated", e);
        }
        deleteProductsInOrder(order.getId());
        return addProductsIntoOrder(order);
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
}
