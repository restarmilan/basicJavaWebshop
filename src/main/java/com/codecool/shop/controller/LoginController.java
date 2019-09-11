package com.codecool.shop.controller;


import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.UserDaoJdbcMem;
import com.codecool.shop.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {


    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
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
        resp.sendRedirect("/");
    }
}
