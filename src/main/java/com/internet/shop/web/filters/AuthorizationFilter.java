package com.internet.shop.web.filters;

import com.internet.shop.lib.Injector;
import com.internet.shop.models.Role;
import com.internet.shop.models.User;
import com.internet.shop.services.UserService;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationFilter implements Filter {

    private static final String USER_ID = "user_id";
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private UserService userService = (UserService) injector.getInstance(UserService.class);
    private Map<String, List<Role.RoleName>> protectedUrls = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        protectedUrls.put("/", List.of(Role.RoleName.USER, Role.RoleName.ADMIN));
        protectedUrls.put("/users", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/products/add", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/products", List.of(Role.RoleName.USER, Role.RoleName.ADMIN));
        protectedUrls.put("/products/edit", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/products/delete", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/cart", List.of(Role.RoleName.USER, Role.RoleName.ADMIN));
        protectedUrls.put("/cart/add", List.of(Role.RoleName.USER, Role.RoleName.ADMIN));
        protectedUrls.put("/cart/remove", List.of(Role.RoleName.USER, Role.RoleName.ADMIN));
        protectedUrls.put("/user/remove", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/order/add", List.of(Role.RoleName.USER, Role.RoleName.ADMIN));
        protectedUrls.put("/orders", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/user/orders", List.of(Role.RoleName.USER, Role.RoleName.ADMIN));
        protectedUrls.put("/order/details", List.of(Role.RoleName.USER, Role.RoleName.ADMIN));
        protectedUrls.put("/order/delete", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/admin/index", List.of(Role.RoleName.ADMIN));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String servletPath = req.getServletPath();
        Long userId = (Long) req.getSession().getAttribute(USER_ID);

        if (Objects.isNull(protectedUrls.get(servletPath))) {
            chain.doFilter(req, resp);
            return;
        }

        User user = userService.get(userId);
        if (isAuthorized(user, protectedUrls.get(servletPath))) {
            chain.doFilter(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/views/errors/denied.jsp")
                    .forward(req, resp);
        }
    }

    @Override
    public void destroy() {

    }

    private boolean isAuthorized(User user, List<Role.RoleName> authorizedRoles) {

        for (Role.RoleName authorizedRole : authorizedRoles) {
            for (Role userRole : user.getRoles()) {
                if (authorizedRole.equals(userRole.getRoleName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
