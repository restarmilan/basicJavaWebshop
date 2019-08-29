package com.codecool.shop.controller;


import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    CartDaoMem cart = CartDaoMem.getInstance();
    ProductDaoMem productDaoMem = ProductDaoMem.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        JSONTokener tokener = new JSONTokener(reader);
        JSONObject json = new JSONObject(tokener);
        System.out.println("cart" + json.get("id"));
        //String jsonResponse = new Gson().toJson(json);
        //resp.setContentType("application/json");
        //resp.setCharacterEncoding("UTF-8");
        int id = json.getInt("id");
        cart.addToCart(productDaoMem.find(id)); // itt küldi el a cartdaomemnek a prod id-t és teszi bele a hashmapbe
        System.out.println(cart.getsumOfAllProducts());
        Map<String,String> jsonData = new HashMap<>();
        jsonData.put("cartSize",String.valueOf(cart.getCartOfAllProducts().size()));
        int sumOfAllProductsInCart = cart.getsumOfAllProducts();
        jsonData.put("sumOfAllProductsInCart", String.valueOf(sumOfAllProductsInCart));
        JSONObject responseData = new JSONObject(jsonData);
        resp.getWriter().print(responseData);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        float sum = cart.getSumOfPrices();
        context.setVariable("sum", sum);
        context.setVariable("cart", cart.getCartOfAllProducts());
        engine.process("product/cart.html", context, resp.getWriter());
    }
}