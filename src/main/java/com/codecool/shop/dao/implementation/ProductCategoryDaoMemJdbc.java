package com.codecool.shop.dao.implementation;

import com.codecool.shop.controller.DatabaseController;
import com.codecool.shop.dao.ProductCategoryDaoJdbc;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoMemJdbc implements ProductCategoryDaoJdbc {

    DatabaseController controller = DatabaseController.getInstance();

    private static ProductCategoryDaoMemJdbc instance = null;

    private ProductCategoryDaoMemJdbc() {}

    public static ProductCategoryDaoMemJdbc getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoMemJdbc();
        }
        return instance;
    }

    public void add(String name, String department, String description) {
        Connection connection = controller.getConnection();
        String query = "INSERT INTO Productcategory (name, department, description) VALUES (?, ?, ?);";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, department);
            ps.setString(3, description);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }
    }

    public void remove(int prodCatId) {
        Connection connection = controller.getConnection();
        String query = "DELETE FROM Productcategory WHERE id = ?;";
        String supplementQuery = "DELETE FROM Product WHERE productcategory_id = ?";
        PreparedStatement ps = null;
        PreparedStatement supplementPs = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, prodCatId);
            supplementPs = connection.prepareStatement(supplementQuery);
            supplementPs.setInt(1, prodCatId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }
    }

    public ProductCategory find(int prodCatId) {
        Connection connection = controller.getConnection();
        String query = "SELECT * FROM Productcategory WHERE ID = ?;";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, prodCatId);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                ProductCategory productCategory = new ProductCategory(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("department"), resultSet.getString("description"));
                return productCategory;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }

        return null;
    }

    public List<ProductCategory> getAll() {
        Connection connection = controller.getConnection();
        String query = "SELECT * FROM Productcategory;";
        PreparedStatement ps = null;
        List<ProductCategory> resultList = new ArrayList<>();
        try {
            ps = connection.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                ProductCategory productCategory = new ProductCategory(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("department"), resultSet.getString("description"));
                resultList.add(productCategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    void getFinallyClause(PreparedStatement ps, Connection connection) {
        try {
            if (ps != null) {
                ps.close();}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
