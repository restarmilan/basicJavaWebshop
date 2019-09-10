package com.codecool.shop.dao.implementation;

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

    private static ProductCategoryDaoMemJdbc instance = null;

    private ProductCategoryDaoMemJdbc() {}

    public static ProductCategoryDaoMemJdbc getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoMemJdbc();
        }
        return instance;
    }

    public String add(Connection connection, String name, String department, String description) {
        String query = "INSERT INTO Productcategory (name, department, description) VALUES (?, ?, ?);";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, department);
            ps.setString(3, description);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }
        return null;
    }

    public String remove(Connection connection, int prodCatId) {
        String query = "DELETE FROM Productcategory WHERE id = ?;";
        /*
        "DELETE * FROM Products WHERE productCategory_id = ?"; az összes hozzá tartozó productot is kitöröljük
         */
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, prodCatId);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }
        return null;

    }

    public ProductCategory find(Connection connection, int prodCatid) {
        String query = "SELECT * FROM Productcategory WHERE ID = ?;";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, prodCatid);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) { // ide kell az ID is, ezért a Supplier class-nál át kell írni a constructort?
                // csak akkor hozok létre új objectet ebből, ha vmiért használnom kell, pl. itt és meg is adom az id-t az adatbázisból
                ProductCategory productCategory = new ProductCategory(resultSet.getString("name"), resultSet.getString("department"), resultSet.getString("description"));
                return productCategory;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }

        return null;
    }

    public List<ProductCategory> getAll(Connection connection) {
        String query = "SELECT * FROM Productcategory;";
        PreparedStatement ps = null;
        List<ProductCategory> resultList = new ArrayList<>();
        try {
            ps = connection.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery(query);
            while (resultSet.next()){
                ProductCategory productCategory = new ProductCategory(resultSet.getString("name"), resultSet.getString("department"), resultSet.getString("description"));
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
