package com.internet.shop.controllers;

import com.internet.shop.lib.Injector;
import com.internet.shop.models.Order;
import com.internet.shop.services.OrderService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderDetailsController extends HttpServlet {
    private static final Injector injector =
            Injector.getInstance("com.internet.shop");
    private OrderService orderService =
            (OrderService) injector.getInstance(OrderService.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        Order order = orderService.get(Long.parseLong(id));
        req.setAttribute("sum", orderService.getOrderPrice(order));
        req.setAttribute("products", order.getProducts());
        req.getRequestDispatcher("/WEB-INF/views/order/details.jsp").forward(req, resp);
    }
}
