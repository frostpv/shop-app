package com.internet.shop.controllers;

import com.internet.shop.lib.Injector;
import com.internet.shop.models.User;
import com.internet.shop.services.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private UserService userService = (UserService) injector.getInstance(UserService.class);

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
            User user = new User(name, login, psw);
            userService.create(user);
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            req.setAttribute("message", "You password and repeat aren't same.");
            req.getRequestDispatcher("/WEB-INF/views/user/register.jsp").forward(req, resp);
        }
    }
}
