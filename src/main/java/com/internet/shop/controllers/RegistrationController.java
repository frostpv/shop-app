package com.internet.shop.controllers;

import com.internet.shop.lib.Injector;
import com.internet.shop.models.Role;
import com.internet.shop.models.ShoppingCart;
import com.internet.shop.models.User;
import com.internet.shop.services.ShoppingCartService;
import com.internet.shop.services.UserService;
import com.internet.shop.util.HashUtil;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private UserService userService = (UserService) injector.getInstance(UserService.class);
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/user/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String psw = req.getParameter("psw");
        String pswRepeat = req.getParameter("psw-repeat");
        if (psw.equals(pswRepeat)) {
            User user = new User();
            user.setName(name);
            user.setLogin(login);
            byte[] salt = HashUtil.getSalt();
            user.setSalt(salt);
            user.setPassword(HashUtil.hashPassword(psw, salt));
            Set<Role> roleSet = new HashSet<>();
            roleSet.add(Role.of("USER"));
            user.setRoles(roleSet);
            user = userService.create(user);
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setUserId(user.getId());
            shoppingCartService.create(shoppingCart);
            resp.sendRedirect(req.getContextPath() + "/users");
        } else {
            req.setAttribute("message", "You password and repeat aren't same.");
            req.getRequestDispatcher("/WEB-INF/views/user/register.jsp").forward(req, resp);
        }
    }
}
