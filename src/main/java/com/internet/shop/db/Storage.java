package com.internet.shop.db;

import com.internet.shop.models.Product;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static List<Product> products = new ArrayList<>();
    private static long productId = 0;

    public static Product addProduct(Product product) {
        if (isNotNull(product)) {
            product.setId(++productId);
            products.add(product);
        }
        return product;
    }

    private static boolean isNotNull(Object object) {
        return object != null;
    }
}
