package com.internet.shop.controllers;

import com.internet.shop.lib.Injector;
import com.internet.shop.models.Product;
import com.internet.shop.services.ProductService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditProductsController extends HttpServlet {
    private static final Injector injector =
            Injector.getInstance("com.internet.shop");
    private ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Product> products = productService.getAll();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/WEB-INF/views/product/products-edit.jsp").forward(req, resp);
    }
}
