package core.basesyntax.factory;

import core.basesyntax.dao.OrderDao;
import core.basesyntax.dao.ProductDao;
import core.basesyntax.dao.ShoppingCartDao;
import core.basesyntax.dao.UserDao;
import core.basesyntax.dao.impl.OrderDaoImpl;
import core.basesyntax.dao.impl.ProductDaoImpl;
import core.basesyntax.dao.impl.ShoppingCartDaoImpl;
import core.basesyntax.dao.impl.UserDaoImpl;

public class Factory {
    private static OrderDao order;
    private static ProductDao product;
    private static ShoppingCartDao shoppingCar;
    private static UserDao user;

    private static OrderDao getOrderDao() {
        if (order == null) {
            order = new OrderDaoImpl();
        }
        return order;
    }

    private static ProductDao getProductDao() {
        if (product == null) {
            product = new ProductDaoImpl();
        }
        return product;
    }

    private static ShoppingCartDao getShoppingCartDao() {
        if (shoppingCar == null) {
            shoppingCar = new ShoppingCartDaoImpl();
        }
        return shoppingCar;
    }

    private static UserDao getUserDao() {
        if (user == null) {
            user = new UserDaoImpl();
        }
        return user;
    }
}
