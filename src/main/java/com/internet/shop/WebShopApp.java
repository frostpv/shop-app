package com.internet.shop;

import com.internet.shop.lib.Injector;
import com.internet.shop.models.Product;
import com.internet.shop.services.ProductServiceInterface;

public class WebShopApp {
    public static void main(String[] args) {
        ProductServiceInterface productService = (ProductServiceInterface) Injector.getInstance("com.internet.shop")
                .getInstance(ProductServiceInterface.class);



        Product banana = productService.create(new Product("Banana", 10.00));
        Product orange = productService.create(new Product("Orange", 20.00));
        productService.create(new Product("Apple", 40.00));
        System.out.println(productService.getAll());
        Product changedOrange = productService.get(orange.getId());
        productService.delete(banana.getId());
        changedOrange.setPrice(30.00);
        System.out.println(productService.getAll());
        System.out.println(productService.update(changedOrange));


    }
}
