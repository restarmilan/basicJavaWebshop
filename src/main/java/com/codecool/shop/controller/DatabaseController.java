package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDaoJdbc;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.lang.*;
import java.util.Arrays;
import java.util.List;



public class DatabaseController {

    private static DatabaseController instance = null;

    private DatabaseController() {}

    public static DatabaseController getInstance() {
        if (instance == null) {
            instance = new DatabaseController();
        }
        return instance;
    }

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = System.getenv("DB_USER");
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");


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
        ProductCategoryDaoJdbc pc = ProductCategoryDaoMemJdbc.getInstance();
        List<ProductCategory> pr = pc.getAll();
        for (ProductCategory c : pr) {
            System.out.println(c.stringify());
        }
   }

}