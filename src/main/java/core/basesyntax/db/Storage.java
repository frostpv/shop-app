package core.basesyntax.db;

import core.basesyntax.models.Order;
import core.basesyntax.models.Product;
import core.basesyntax.models.ShoppingCart;
import core.basesyntax.models.User;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static List<Product> products = new ArrayList<>();
    public static List<Order> orders = new ArrayList<>();
    public static List<ShoppingCart> shoppingCarts = new ArrayList<>();
    public static List<User> users = new ArrayList<>();
}
