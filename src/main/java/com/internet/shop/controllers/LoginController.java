package com.internet.shop.controllers;

import com.internet.shop.exceptions.AuthenticationException;
import com.internet.shop.lib.Injector;
import com.internet.shop.models.User;
import com.internet.shop.security.AuthenticationService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private AuthenticationService authenticationService =
            (AuthenticationService) injector.getInstance(AuthenticationService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String pasword = req.getParameter("psw");
        try {
            User user = authenticationService.login(login, pasword);
            HttpSession session = req.getSession();
            session.setAttribute(USER_ID, user.getId());
        } catch (AuthenticationException e) {
            req.setAttribute("errorMsg", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
