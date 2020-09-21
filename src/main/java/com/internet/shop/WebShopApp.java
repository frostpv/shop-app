package com.internet.shop;

import com.internet.shop.dao.ProductDaoJdbcImpl;
import com.internet.shop.models.Product;

public class WebShopApp {
    public static void main(String[] args) {
        ProductDaoJdbcImpl daoJdbc = new ProductDaoJdbcImpl();
        Product product = new Product("BMW M5", 54555);
        System.out.println(daoJdbc.create(product).toString());
        System.out.println(daoJdbc.get(1L).toString());
        daoJdbc.delete(1L);
        System.out.println(daoJdbc.getAll());

    }
}
