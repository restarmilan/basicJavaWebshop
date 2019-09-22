package com.codecool.shop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseController {


    private String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private String DB_USER = System.getenv("DB_USER");
    private String DB_PASSWORD = System.getenv("DB_PASSWORD");
    private static final Logger logger = LoggerFactory.getLogger(DatabaseController.class);

    public DatabaseController() {}

    public DatabaseController(String database, String dbUser, String dbPassword) {
        this.DATABASE = database;
        this.DB_USER = dbUser;
        this.DB_PASSWORD = dbPassword;
    }

    public Connection getConnection() {
        try {
            logger.info("Connection created successfully");
            return DriverManager.getConnection(
                    DATABASE,
                    DB_USER,
                    DB_PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Connection with database failed", e);
        }
        return null;
    }

    public static void main(String[] args) {
   }

}