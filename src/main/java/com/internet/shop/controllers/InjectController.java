package com.internet.shop.controllers;

import com.internet.shop.lib.Injector;
import com.internet.shop.models.Product;
import com.internet.shop.models.Role;
import com.internet.shop.models.User;
import com.internet.shop.services.ProductService;
import com.internet.shop.services.ShoppingCartService;
import com.internet.shop.services.UserService;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InjectController extends HttpServlet {
    private static Injector injector = Injector.getInstance("com.internet.shop");
    private ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
    private UserService userService = (UserService) injector.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Product[] products = {
                new Product("audiA1", 10500),
                new Product("audiA3", 19500),
                new Product("AudiA4", 18500),
                new Product("AudiA5", 27500),
                new Product("AudiA6", 33500),
                new Product("AudiA7", 30500),
                new Product("AudiA8", 49500),
                new Product("AudiQ2", 50500),
                new Product("AudiQ3", 67500),
                new Product("AudiQ7", 70500),
                new Product("AudiR8", 85500),
                new Product("AudiTt", 90500),
                new Product("AudiQ8", 100750),
        };
        for (Product product : products) {
            productService.create(product);
        }
        User admin = new User("Admin", "Admin", "123");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(Role.of("ADMIN"));
        admin.setRoles(roleSet);
        userService.create(admin);
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
