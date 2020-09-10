package com.internet.shop.controllers;

import com.internet.shop.lib.Injector;
import com.internet.shop.services.OrderService;
import com.internet.shop.services.ShoppingCartService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddOrderController extends HttpServlet {
    private static final Injector injector =
            Injector.getInstance("com.internet.shop");
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
    private OrderService orderService =
            (OrderService) injector.getInstance(OrderService.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("userid");
        orderService.completeOrder(shoppingCartService.getByUserId(Long.parseLong(id)));
        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}
