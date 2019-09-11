package com.codecool.shop.dao.implementation;

import com.codecool.shop.controller.DatabaseController;
import com.codecool.shop.dao.SupplierDaoJdbc;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoMemJdbc implements SupplierDaoJdbc {

    DatabaseController controller = new DatabaseController();

    public SupplierDaoMemJdbc() {}

    public SupplierDaoMemJdbc(String database, String user, String password) {
        controller = new DatabaseController(database, user, password);
    }

    public void add(String name, String description) {
        Connection connection = controller.getConnection();
        String query = "INSERT INTO Suppliers (name, description) VALUES (?, ?);";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }
    }

    public void remove(int supplierId) {
        Connection connection = controller.getConnection();
        String query = "DELETE FROM Suppliers WHERE id = ?;";
        String supplementQuery = "DELETE FROM Product WHERE supplier_id = ?";
        PreparedStatement supplementPs = null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, supplierId);
            supplementPs = connection.prepareStatement(supplementQuery);
            supplementPs.setInt(1, supplierId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }
    }

    public Supplier find(int id) { // null-t ad vissza, de nem tudom, miért. debugger nem segít.
        Connection connection = controller.getConnection();
        String query = "SELECT id, name, description FROM suppliers WHERE id = ?;";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                Supplier supplier = new Supplier(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("description"));
                return supplier;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }

        return null;
    }

    public List<Supplier> getAll() {
        Connection connection = controller.getConnection();
        String query = "SELECT * FROM Suppliers;";
        PreparedStatement ps = null;
        List<Supplier> resultList = new ArrayList<>();
        try {
            ps = connection.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                Supplier supplier = new Supplier(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("description"));
                resultList.add(supplier);
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
