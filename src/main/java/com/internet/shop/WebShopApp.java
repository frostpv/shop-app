package com.internet.shop;

import com.internet.shop.lib.Injector;
import com.internet.shop.models.Product;
import com.internet.shop.models.ShoppingCart;
import com.internet.shop.services.ProductService;
import com.internet.shop.services.ShoppingCartService;
import java.util.ArrayList;
import java.util.List;

public class WebShopApp {
    public static void main(String[] args) {
        ProductService productService =
                (ProductService) Injector.getInstance("com.internet.shop")
                        .getInstance(ProductService.class);
        ShoppingCartService shoppingCartService =
                (ShoppingCartService) Injector.getInstance("com.internet.shop")
                        .getInstance(ShoppingCartService.class);
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
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(product1);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setProducts(productList);
        shoppingCart.setUserId(1L);
        //shoppingCart.setId(3);
        shoppingCartService.create(shoppingCart);
    }
}
