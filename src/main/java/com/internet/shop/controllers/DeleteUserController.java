package com.internet.shop.controllers;

import com.internet.shop.lib.Injector;
import com.internet.shop.models.ShoppingCart;
import com.internet.shop.models.User;
import com.internet.shop.services.ShoppingCartService;
import com.internet.shop.services.UserService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private UserService userService = (UserService) injector.getInstance(UserService.class);
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<User> users = userService.getAll();
        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/views/user/all.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        ShoppingCart userShoppingCart = shoppingCartService.getByUserId(Long.parseLong(id));
        shoppingCartService.delete(userShoppingCart);
        userService.delete(Long.parseLong(id));
        resp.sendRedirect(req.getContextPath() + "/users");
    }
}
