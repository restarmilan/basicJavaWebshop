package com.codecool.shop.controller;


import com.codecool.shop.dao.implementation.CartDaoMem;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/logout"})
public class LogoutController extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(LogoutController.class);

    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        logger.info("User's session has been invalidated");
        session = req.getSession(true);
        session.setAttribute("name", "Guest");
        session.setAttribute("loginStatus", false);
        session.setAttribute("cart", new CartDaoMem());
        logger.info("Created session and cart as {}", session.getAttribute("name"));
        Map<String, Boolean> jsonData = new HashMap<>();
        jsonData.put("status", true);
        JSONObject responseData = new JSONObject(jsonData);
        resp.getWriter().print(responseData);
        logger.info("Successful logout");

    }
}
