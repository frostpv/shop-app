package com.internet.shop;

import com.internet.shop.lib.Injector;
import com.internet.shop.models.Product;
import com.internet.shop.services.ProductService;

public class WebShopApp {
    public static void main(String[] args) {
        ProductService productService =
                (ProductService) Injector.getInstance("com.internet.shop")
                        .getInstance(ProductService.class);

        Product product = new Product("BMW M5", 54555);
        Product product1 = productService.create(product);
        System.out.println(productService.getAll());
        System.out.println(productService.get(product1.getId()).toString());
        System.out.println(productService.getAll());
        product1.setPrice(99999);
        productService.update(product1);
        System.out.println(productService.getAll());
        productService.delete(product1.getId());
        System.out.println(productService.getAll());
    }
}
