package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDaoJdbc;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoMemJdbc implements SupplierDaoJdbc {

    private static SupplierDaoMemJdbc instance = null;

    private SupplierDaoMemJdbc() {}

    public static SupplierDaoMemJdbc getInstance() {
        if (instance == null) {
            instance = new SupplierDaoMemJdbc();
        }
        return instance;
    }

    public String add(Connection connection, String name, String description) {
        String query = "INSERT INTO Suppliers (name, description) VALUES (?, ?);";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, description);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }
        return null;
    }

    public String remove(Connection connection, int supplierId) {
        String query = "DELETE FROM Suppliers WHERE id = ?;";
        /*
        "DELETE * FROM Products WHERE supplier_id = ?"; az összes hozzá tartozó productot is kitöröljük
         */
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, supplierId);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }
        return null;

    }

    public Supplier find(Connection connection, int id) {
        String query = "SELECT * FROM Suppliers WHERE ID = ?;";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) { // ide kell az ID is, ezért a Supplier class-nál át kell írni a constructort?
                // csak akkor hozok létre új objectet ebből, ha vmiért használnom kell, pl. itt és meg is adom az id-t az adatbázisból
                Supplier supplier = new Supplier(resultSet.getString("name"), resultSet.getString("description"));
                return supplier;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }

        return null;
    }

    public List<Supplier> getAll(Connection connection) {
        String query = "SELECT * FROM Suppliers;";
        PreparedStatement ps = null;
        List<Supplier> resultList = new ArrayList<>();
        try {
            ps = connection.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery(query);
            while (resultSet.next()){ // ide is kell majd az id!!!
                Supplier supplier = new Supplier(resultSet.getString("name"),
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
