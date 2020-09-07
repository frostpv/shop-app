package com.internet.shop.db;

import com.internet.shop.models.Order;
import com.internet.shop.models.Product;
import com.internet.shop.models.ShoppingCart;
import com.internet.shop.models.User;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static List<Product> products = new ArrayList<>();
    public static List<User> users = new ArrayList<>();
    public static List<Order> orders = new ArrayList<>();
    public static List<ShoppingCart> shoppingCarts = new ArrayList<>();
    private static long productId = 0;
    private static long userId = 0;
    private static long orderId = 0;
    private static long shoppingCartId = 0;

    public static Product addProduct(Product product) {
        if (isNotNull(product)) {
            product.setId(++productId);
            products.add(product);
        }
        return product;
    }

    public static User addUser(User user) {
        if (isNotNull(user)) {
            user.setId(++userId);
            users.add(user);
        }
        return user;
    }

    public static Order addOrder(Order order) {
        if (isNotNull(order)) {
            order.setId(++orderId);
            orders.add(order);
        }
        return order;
    }

    public static ShoppingCart addShopingCart(ShoppingCart shoppingCart) {
        if (isNotNull(shoppingCart)) {
            shoppingCart.setId(++shoppingCartId);
            shoppingCarts.add(shoppingCart);
        }
        return shoppingCart;
    }

    private static boolean isNotNull(Object object) {
        return object != null;
    }
}
