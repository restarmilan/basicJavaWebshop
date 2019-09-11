package com.codecool.shop.dao.implementation;

import com.codecool.shop.controller.DatabaseController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoJdbcMem {

    private static UserDaoJdbcMem instance = null;
    private Connection connection = DatabaseController.getInstance().getConnection();


    private UserDaoJdbcMem() {

    }

    public static UserDaoJdbcMem getInstance() {
        if (instance == null) {
            instance = new UserDaoJdbcMem();
        }
        return instance;
    }

    public boolean checkIfUSerExist(String username) {
        String query = "SELECT name FROM users WHERE name = ?;";
        String name = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else{
                return false;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addUser(String username, String password, String email, String phoneNumber, String billingAdress, String shippingAdress){
        String query = "INSERT INTO users (name, password, email, phone_number, billing_address, shipping_address) VALUES (?,?,?,?,?,?);";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.setString(5, billingAdress);
            preparedStatement.setString(6, shippingAdress);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
