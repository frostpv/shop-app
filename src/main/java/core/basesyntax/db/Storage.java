package core.basesyntax.db;

import core.basesyntax.models.Order;
import core.basesyntax.models.Product;
import core.basesyntax.models.ShoppingCart;
import core.basesyntax.models.User;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static long productId = 0;
    private static long orderId = 0;
    private static long shoppingCardId = 0;
    private static long userId = 0;
    public static List<Product> products = new ArrayList<>();
    public static List<Order> orders = new ArrayList<>();
    public static List<ShoppingCart> shoppingCarts = new ArrayList<>();
    public static List<User> users = new ArrayList<>();

    public static void addProduct(Product product) {
        productId++;
        product.setId(productId);
        products.add(product);
    }

    public static void addOrder(Order order) {
        orderId++;
        order.setId(orderId);
        orders.add(order);
    }

    public static void addCart(ShoppingCart shoppingCart) {
        shoppingCardId++;
        shoppingCart.setId(shoppingCardId);
        shoppingCarts.add(shoppingCart);
    }

    public static void addUser(User user) {
        userId++;
        user.setId(userId);
        users.add(user);
    }
}
