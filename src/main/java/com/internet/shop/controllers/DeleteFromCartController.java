package com.internet.shop.controllers;

import com.internet.shop.lib.Injector;
import com.internet.shop.models.Product;
import com.internet.shop.services.ShoppingCartService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteFromCartController extends HttpServlet {
    private static final Long USER_ID = 1L;
    private static final Injector injector =
            Injector.getInstance("com.internet.shop");
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        Product product = shoppingCartService
                .getByUserId(USER_ID)
                .getProducts().stream()
                .filter(product1 -> product1.getName().equals(name))
                .filter(product1 -> product1.getPrice() == Double.parseDouble(price))
                .findFirst()
                .get();
        shoppingCartService.deleteProduct(shoppingCartService.getByUserId(USER_ID), product);
        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}
