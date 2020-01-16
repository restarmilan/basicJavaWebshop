package com.codecool.shop.controller;


import com.codecool.shop.dao.implementation.CartDaoMem;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/session"})
public class SessionController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> sessionData = new HashMap<>();
        HttpSession session = req.getSession(false);
        CartDaoMem cart = (CartDaoMem) session.getAttribute("cart");
        sessionData.put("username", session.getAttribute("name"));
        sessionData.put("cartSize", cart.getsumOfAllProducts());
        sessionData.put("loginStatus", session.getAttribute("loginStatus"));
        JSONObject responseData = new JSONObject(sessionData);
        System.out.println(responseData);
        resp.getWriter().print(responseData);
    }
}
