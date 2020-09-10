package com.internet.shop.controllers;

import com.internet.shop.lib.Injector;
import com.internet.shop.models.ShoppingCart;
import com.internet.shop.services.ProductService;
import com.internet.shop.services.ShoppingCartService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CartController extends HttpServlet {
    private static final Long USER_ID = 1L;
    private static final Injector injector =
            Injector.getInstance("com.internet.shop");
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
    private ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(USER_ID);
        req.setAttribute("cart", shoppingCart.getProducts());
        req.getRequestDispatcher("/WEB-INF/views/cart/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(USER_ID);
        shoppingCartService.addProduct(shoppingCart, productService.get(Long.parseLong(id)));
        resp.sendRedirect(req.getContextPath() + "/cart");

    }
}
