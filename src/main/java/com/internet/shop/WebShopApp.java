package com.internet.shop;

import com.internet.shop.dao.ProductDaoJdbcImpl;
import com.internet.shop.models.Product;

public class WebShopApp {
    public static void main(String[] args) {
        ProductDaoJdbcImpl daoJdbc = new ProductDaoJdbcImpl();
        Product product = new Product("BMW M5", 54555);
        Product product1 = daoJdbc.create(product);
        System.out.println(daoJdbc.getAll());
        System.out.println(daoJdbc.get(product1.getId()).toString());
        System.out.println(daoJdbc.getAll());
        product1.setPrice(99999);
        daoJdbc.update(product1);
        System.out.println(daoJdbc.getAll());
        daoJdbc.delete(product1.getId());
        System.out.println(daoJdbc.getAll());
    }
}
