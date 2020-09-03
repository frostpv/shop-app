package core.basesyntax;

import core.basesyntax.dao.ProductDao;
import core.basesyntax.dao.impl.ProductDaoImpl;
import core.basesyntax.db.Storage;
import core.basesyntax.models.Product;
import core.basesyntax.services.ProductServiceInterface;
import core.basesyntax.services.impl.ProductServiceImpl;

import java.security.Provider;

public class ShopApp {
    public static void main(String[] args) {

        ProductDao productDao = new ProductDaoImpl();



        productDao.create(new Product("soft", 5.5));
        System.out.println(productDao.getAll());

        System.out.println(productDao.getAll());

    }
}
