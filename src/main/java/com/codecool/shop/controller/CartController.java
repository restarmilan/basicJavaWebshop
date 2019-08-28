package com.codecool.shop.controller;


import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    CartDaoMem cart = CartDaoMem.getInstance();
    ProductDaoMem productDaoMem = ProductDaoMem.getInstance();

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        JSONTokener tokener = new JSONTokener(reader);
        JSONObject json = new JSONObject(tokener);
        System.out.println("cart"+json.get("id"));
        //String jsonResponse = new Gson().toJson(json);
        //resp.setContentType("application/json");
        //resp.setCharacterEncoding("UTF-8");
        int id = json.getInt("id");
        cart.addToCart(productDaoMem.find(id));
        resp.getWriter().print(json);





    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

    }
}
