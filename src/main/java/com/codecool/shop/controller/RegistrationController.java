package com.codecool.shop.controller;


import com.codecool.shop.dao.implementation.UserDaoJdbcMem;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/registration"})
public class RegistrationController extends HttpServlet {

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String eMail = req.getParameter("email");
        String phoneNumber = req.getParameter("phone_number");
        String billingAddress = req.getParameter("billing_address");
        String shippingAddress = req.getParameter("shipping_address");
        Map<String, Boolean> responseData = new HashMap<>();
        if(!UserDaoJdbcMem.getInstance().checkIfUSerExist(username)){
            UserDaoJdbcMem.getInstance().addUser(username, password, eMail, phoneNumber, billingAddress, shippingAddress);
            responseData.put("registrationStatus", true);
        }else{
            responseData.put("registrationStatus", false);
        }
        resp.getWriter().print(responseData);
    }

}
