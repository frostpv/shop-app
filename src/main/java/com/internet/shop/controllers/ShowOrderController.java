package com.internet.shop.controllers;

import com.internet.shop.lib.Injector;
import com.internet.shop.models.Product;
import com.internet.shop.services.OrderService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowOrderController extends HttpServlet {
    private static final Injector injector =
            Injector.getInstance("com.internet.shop");
    private OrderService orderService =
            (OrderService) injector.getInstance(OrderService.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        List<Product> products = orderService.get(Long.parseLong(id)).getProducts();
        double sum = products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
        req.setAttribute("sum", sum);
        req.setAttribute("products", products);
        req.getRequestDispatcher("/WEB-INF/views/order/details.jsp").forward(req, resp);
    }
}
