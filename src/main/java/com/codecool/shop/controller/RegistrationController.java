package com.codecool.shop.controller;


import com.codecool.shop.dao.implementation.UserDaoJdbcMem;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/registration"})
public class RegistrationController extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        BufferedReader reader = req.getReader();
        JSONTokener tokener = new JSONTokener(reader);
        JSONObject json = new JSONObject(tokener);
        String username = json.getString("username");
        String password = json.getString("password");
        String eMail = json.getString("email");
        String phoneNumber = json.getString("phone_number");
        String billingAddress = json.getString("billing_address");
        String shippingAddress = json.getString("shipping_address");
        Map<String, Boolean> responseData = new HashMap<>();
        if(!UserDaoJdbcMem.getInstance().checkIfUSerExist(username)){
            UserDaoJdbcMem.getInstance().addUser(username, password, eMail, phoneNumber, billingAddress, shippingAddress);
            logger.info("Successful registration as {}", username);
            responseData.put("status", true);
        }else{
            responseData.put("status", false);
            logger.warn("There is already a user with this username");
        }
        JSONObject jsonData = new JSONObject(responseData);
        resp.getWriter().print(jsonData);
        logger.info("Registration was successful");
    }

}
