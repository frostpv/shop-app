package com.internet.shop.dao;

import com.internet.shop.exceptions.DataBaseProcessingException;
import com.internet.shop.lib.Dao;
import com.internet.shop.models.Product;
import com.internet.shop.models.ShoppingCart;
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
public class ShoppingCartDaoJdbcImpl implements ShoppingCartDao {
    @Override
    public Optional<ShoppingCart> getByUserId(Long userId) {
        ShoppingCart shoppingCart = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT id_shoping_cart "
                    + "FROM shoping_cart "
                    + " WHERE id_user = ? AND deleted = FALSE";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                shoppingCart = new ShoppingCart();
                shoppingCart.setId(resultSet.getLong("id_shoping_cart"));
                shoppingCart.setUserId(userId);
                shoppingCart.setProducts(getCartProducts(shoppingCart.getId()));
                return Optional.of(shoppingCart);
            }
        } catch (SQLException e) {
            throw new DataBaseProcessingException("Shoping cart by user " + userId
                    + " was not found", e);
        }
        return Optional.empty();
    }

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "INSERT INTO shoping_cart (id_user) VALUES (?)";
            PreparedStatement preparedStatement
                    = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, shoppingCart.getUserId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                shoppingCart.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new DataBaseProcessingException("Shoping cart "
                    + shoppingCart + " was not created", e);
        }
        return shoppingCart;
    }

    @Override
    public Optional<ShoppingCart> get(Long id) {
        ShoppingCart shoppingCart = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM shoping_cart "
                    + "WHERE id_shoping_cart = ? AND deleted = FALSE";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                shoppingCart = new ShoppingCart();
                shoppingCart.setId(id);
                shoppingCart.setUserId(resultSet.getLong("id_user"));
                shoppingCart.setProducts(getCartProducts(shoppingCart.getId()));
                return Optional.of(shoppingCart);
            }
        } catch (SQLException e) {
            throw new DataBaseProcessingException("Shoping cart "
                    + shoppingCart + " was not created", e);
        }
        return Optional.empty();
    }

    @Override
    public List<ShoppingCart> getAll() {
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM shoping_cart WHERE deleted = FALSE";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ShoppingCart shoppingCart = new ShoppingCart();
                shoppingCart.setId(resultSet.getLong("id_shoping_cart"));
                shoppingCart.setUserId(resultSet.getLong("id_user"));
                shoppingCart.setProducts(getCartProducts(shoppingCart.getId()));
                shoppingCarts.add(shoppingCart);
            }
        } catch (SQLException e) {
            throw new DataBaseProcessingException("Shopping cart list was not created", e);
        }
        return shoppingCarts;
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {

        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "UPDATE shoping_cart "
                    + "SET id_user = ? WHERE id_shoping_cart=? "
                    + "AND deleted = FALSE";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, shoppingCart.getUserId());
            preparedStatement.setString(2, shoppingCart.getId().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseProcessingException("Shopping " + shoppingCart
                    + " was not created updated", e);
        }
        deleteProductsInShoppingCart(shoppingCart.getId());
        return addProductsIntoSHoppingCart(shoppingCart);
    }

    private ShoppingCart addProductsIntoSHoppingCart(ShoppingCart shoppingCart) {
        String query = "INSERT INTO shoping_cart_products (id_cart, id_product) "
                + "VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);

            for (Product product : shoppingCart.getProducts()) {
                statement.setLong(1, shoppingCart.getId());
                statement.setLong(2, product.getId());
                statement.executeUpdate();
            }
            return shoppingCart;
        } catch (SQLException e) {
            throw new DataBaseProcessingException("Failed to add the products to"
                    + shoppingCart, e);
        }
    }

    private boolean deleteProductsInShoppingCart(Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "DELETE FROM shoping_cart_products WHERE id_cart = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DataBaseProcessingException("Shopping product in cart "
                    + id + " was not deleted", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "UPDATE shoping_cart "
                    + "SET deleted = TRUE WHERE id_shoping_cart = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DataBaseProcessingException("Shopping cart is was not deleted", e);
        }
    }

    private List<Product> getCartProducts(Long id) {
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM products JOIN shoping_cart_products "
                    + "ON products.product_id = shoping_cart_products.id_product "
                    + "WHERE shoping_cart_products.id_cart=?";
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
            throw new DataBaseProcessingException("Shoping cart "
                    + id + " have problem which product list", e);
        }
        return products;
    }
}

