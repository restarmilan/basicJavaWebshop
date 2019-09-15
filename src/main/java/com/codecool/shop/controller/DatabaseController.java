package com.codecool.shop.controller;

import java.sql.*;
import java.lang.*;

public class DatabaseController {


    private String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private String DB_USER = System.getenv("DB_USER");
    private String DB_PASSWORD = System.getenv("DB_PASSWORD");

    public DatabaseController() {}

    public DatabaseController(String database, String dbUser, String dbPassword) {
        this.DATABASE = database;
        this.DB_USER = dbUser;
        this.DB_PASSWORD = dbPassword;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    DATABASE,
                    DB_USER,
                    DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
   }

}