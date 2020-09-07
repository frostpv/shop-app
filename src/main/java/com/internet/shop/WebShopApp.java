package com.internet.shop;

import com.internet.shop.lib.Injector;
import com.internet.shop.models.Product;
import com.internet.shop.models.ShoppingCart;
import com.internet.shop.models.User;
import com.internet.shop.services.OrderServiceInterface;
import com.internet.shop.services.ProductServiceInterface;
import com.internet.shop.services.ShoppingCartServiceIntercace;
import com.internet.shop.services.UserServiceInterface;

public class WebShopApp {
    public static void main(String[] args) {
        ProductServiceInterface productService =
                (ProductServiceInterface) Injector.getInstance("com.internet.shop")
                 .getInstance(ProductServiceInterface.class);

        Product iphone6s = new Product("Iphone6s", 100);
        Product iphone7 = new Product("Iphone7", 400);
        Product iphone7plus = new Product("Iphone7plus", 500);
        Product iphone8s = new Product("Iphone8s", 600);
        Product iphoneX = new Product("Iphone7plus", 900);
        Product iphone11 = new Product("Iphone7plus", 1500);
        productService.create(iphone6s);
        productService.create(iphone7);
        productService.create(iphone7plus);
        productService.create(iphone8s);
        productService.create(iphoneX);
        productService.create(iphone11);
        System.out.println(productService.getAll());
        UserServiceInterface userService =
                (UserServiceInterface) Injector.getInstance("com.internet.shop")
                        .getInstance(UserServiceInterface.class);
        User jon = new User("Jon", "LamaR@@", "XXXsre");
        userService.create(jon);
        User mike = new User("Mike", "Rabbit453", "erydsg234e");
        userService.create(mike);
        User liam = new User("Liam", "LoveBOY", "rwew8868sre");
        userService.create(liam);
        System.out.println(userService.getAll());
        ShoppingCart jonCart = new ShoppingCart();
        jonCart.setUserId(1);
        ShoppingCart mikeCart = new ShoppingCart();
        mikeCart.setUserId(2);
        ShoppingCart liamCart = new ShoppingCart();
        liamCart.setUserId(3);
        ShoppingCartServiceIntercace shoppingCartService =
                (ShoppingCartServiceIntercace) Injector.getInstance("com.internet.shop")
                        .getInstance(ShoppingCartServiceIntercace.class);
        shoppingCartService.create(jonCart);
        shoppingCartService.create(mikeCart);
        shoppingCartService.create(liamCart);
        System.out.println(shoppingCartService.getByUserId(jon.getId()));
        shoppingCartService.addProduct(jonCart, iphone6s);
        shoppingCartService.addProduct(jonCart, iphone6s);
        shoppingCartService.addProduct(jonCart, iphone7plus);
        System.out.println(shoppingCartService.getByUserId(jon.getId()));
        OrderServiceInterface orderService =
                (OrderServiceInterface) Injector.getInstance("com.internet.shop")
                        .getInstance(OrderServiceInterface.class);
        orderService.completeOrder(jonCart);
        System.out.println(shoppingCartService.getByUserId(jon.getId()));
        System.out.println(orderService.getAll());
    }
}
