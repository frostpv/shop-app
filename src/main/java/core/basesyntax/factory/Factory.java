package core.basesyntax.factory;

import com.internet.shop.dao.OrderDao;
import com.internet.shop.dao.ProductDao;
import com.internet.shop.dao.ShoppingCartDao;
import com.internet.shop.dao.UserDao;
import com.internet.shop.dao.OrderDaoImpl;
import com.internet.shop.dao.ProductDaoImpl;
import com.internet.shop.dao.ShoppingCartDaoImpl;
import com.internet.shop.dao.UserDaoImpl;

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
