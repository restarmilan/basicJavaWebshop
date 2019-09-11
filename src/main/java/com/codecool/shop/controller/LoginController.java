package com.codecool.shop.controller;


import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.UserDaoJdbcMem;
import com.codecool.shop.model.User;
import org.json.JSONObject;
import org.json.JSONTokener;

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


    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        JSONTokener tokener = new JSONTokener(reader);
        JSONObject json = new JSONObject(tokener);
        String username = json.getString("username");
        String password = json.getString("password");
        User user = UserDaoJdbcMem.getInstance().getUsernameAndPassword(username);
        if(user != null){
            if(user.getPassword().equals(password)){
                HttpSession session = req.getSession(true);
                session.setAttribute("name", username);
                session.setAttribute("loginStatus", true);
                session.setAttribute("cart", new CartDaoMem());
            }else{

            }

        }else{

        }
        Map<String,Boolean> jsonData = new HashMap<>();
        jsonData.put("ez", true);
        JSONObject responseData = new JSONObject(jsonData);
        resp.getWriter().print(responseData);
    }
}
