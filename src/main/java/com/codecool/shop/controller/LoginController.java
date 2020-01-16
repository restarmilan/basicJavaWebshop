package com.codecool.shop.controller;


import com.codecool.shop.dao.implementation.UserDaoJdbcMem;
import com.codecool.shop.model.User;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);


    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession previousSession = req.getSession(false);
        BufferedReader reader = req.getReader();
        JSONTokener tokener = new JSONTokener(reader);
        JSONObject json = new JSONObject(tokener);
        String username = json.getString("username");
        String password = json.getString("password");
        User user = UserDaoJdbcMem.getInstance().getUsernameAndPassword(username);
        Map<String,Boolean> jsonData = new HashMap<>();
        if(user != null){
            if(user.getPassword().equals(password)){
                HttpSession session = req.getSession(true);
                session.setAttribute("name", username);
                logger.debug("session username attribute is {}", username);
                session.setAttribute("loginStatus", true);
                session.setAttribute("cart", previousSession.getAttribute("cart"));
                logger.trace("session details {}, {} ", username, previousSession.getAttribute("cart"));
                jsonData.put("status", true);
                logger.info("successful login as {}", username);
                logger.info("session and cart created as {}", session.getAttribute("name"));
            }else{
                jsonData.put("status", false);
                logger.warn("unsuccessful login because of wrong password");
            }

        }else{
            jsonData.put("status", false);
        }

        JSONObject responseData = new JSONObject(jsonData);
        resp.getWriter().print(responseData);
    }
}
