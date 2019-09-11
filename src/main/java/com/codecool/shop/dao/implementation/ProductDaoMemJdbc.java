package com.codecool.shop.dao.implementation;

import com.codecool.shop.controller.DatabaseController;
import com.codecool.shop.dao.ProductDaoJdbc;
import com.codecool.shop.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class ProductDaoMemJdbc implements ProductDaoJdbc {

    private DatabaseController controller = new DatabaseController();

    public ProductDaoMemJdbc () {}

    public ProductDaoMemJdbc(String database, String user, String password) {
        this.controller = new DatabaseController(database, user, password);
    }

    public void add(String name, float defaultPrice, String defaultCurrency, String description, int productCategoryId, int supplierId) {
        Connection connection = controller.getConnection();
        String query = "INSERT INTO Product (name, default_price, default_currency, description, productcategory_id, supplier_id) VALUES (?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setFloat(2, defaultPrice);
            ps.setString(3, defaultCurrency);
            ps.setString(4, description);
            ps.setInt(5, productCategoryId);
            ps.setInt(6, supplierId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }
    }

    public void remove(int prodId) {
        Connection connection = controller.getConnection();
        String query = "DELETE FROM Product WHERE id = ?;";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, prodId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }
    }

    public Product find(int prodId) {
        Connection connection = controller.getConnection();
        String query = "SELECT * FROM Product WHERE ID = ?;";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, prodId);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                Product product = new Product(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getFloat("default_price"),
                        resultSet.getString("default_currency"), resultSet.getString("description"),
                        resultSet.getInt("productcategory_id"), resultSet.getInt("supplier_id"));
                return product;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }

        return null;
    }

    public List<Product> getAll() {
        Connection connection = controller.getConnection();
        String query = "SELECT * FROM Product;";
        PreparedStatement ps = null;
        List<Product> resultList = new ArrayList<>();
        try {
            ps = connection.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                Product product = new Product(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getFloat("default_price"),
                        resultSet.getString("default_currency"), resultSet.getString("description"),
                        resultSet.getInt("productcategory_id"), resultSet.getInt("supplier_id"));
                resultList.add(product);
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
