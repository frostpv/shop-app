package core.basesyntax;

import core.basesyntax.db.Storage;
import core.basesyntax.models.Product;

public class ShopApp {
    public static void main(String[] args) {
        Product product = new Product(0,"fhone", 5.5);

        Storage.addProduct(product);
        Storage.addProduct(null);
        Storage.addProduct(product);

        System.out.println(Storage.products.size());
        System.out.println(Storage.products.get(Storage.products.size()-1).getId());
    }
}
