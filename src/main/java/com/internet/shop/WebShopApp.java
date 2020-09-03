package com.internet.shop;

import com.internet.shop.lib.Injector;
import com.internet.shop.models.Product;
import com.internet.shop.services.ProductServiceInterface;

public class WebShopApp {
    public static void main(String[] args) {
        ProductServiceInterface productService =
                (ProductServiceInterface) Injector.getInstance("com.internet.shop")
                .getInstance(ProductServiceInterface.class);

        productService.create(new Product("Iphone", 108.00));
        Product xiaomi = productService.create(new Product("Xiaomi", 205.00));
        productService.create(new Product("ApplePro", 405.00));
        System.out.println(productService.getAll());
        productService.delete(xiaomi.getId());
        System.out.println(productService.getAll());
        Product newIphone = new Product("Iphone2", 125.00);
        newIphone.setId(1);
        productService.update(newIphone);
        System.out.println(productService.getAll());
        System.out.println(productService.get(3L));
    }
}
