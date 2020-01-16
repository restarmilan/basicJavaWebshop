package com.codecool.shop.dao;

import com.codecool.shop.controller.DatabaseController;
import com.codecool.shop.dao.implementation.SupplierDaoMemJdbc;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoJdbcTest {

    private SupplierDaoJdbc supplierDaoJdbc = new SupplierDaoMemJdbc("jdbc:postgresql://localhost:5432/mock_db_codecoolshop", "zsana", "mandolin");

    @BeforeEach
    void insertDataToSuppliersTable() {
        DatabaseController controller = new DatabaseController("jdbc:postgresql://localhost:5432/mock_db_codecoolshop", "zsana", "mandolin");
        Connection connection = controller.getConnection();
        PreparedStatement ps, ps2, ps3;
        try {
            ps = connection.prepareStatement("INSERT INTO suppliers(name, description) VALUES ('Mock1 supplier', 'Mock description 1')");
            ps2 = connection.prepareStatement("INSERT INTO suppliers(name, description) VALUES ('Mock2 supplier', 'Mock description 2')");
            ps3 = connection.prepareStatement("INSERT INTO suppliers(name, description) VALUES ('Mock3 supplier', 'Mock description 3')");
            ps.executeUpdate();
            ps2.executeUpdate();
            ps3.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @AfterEach
    void deleteAllDataFromSuppliersTable() {
        DatabaseController controller = new DatabaseController("jdbc:postgresql://localhost:5432/mock_db_codecoolshop", "zsana", "mandolin");
        Connection connection = controller.getConnection();
        String query = "TRUNCATE TABLE suppliers CASCADE;";
        String query2 = "ALTER SEQUENCE suppliers_id_seq RESTART WITH 1;";
        PreparedStatement ps;
        PreparedStatement ps2;
        try {
            ps = connection.prepareStatement(query);
            ps2 = connection.prepareStatement(query2);
            ps.executeUpdate();
            ps2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    void Should_Add_New_Supplier_With_Given_Parameters() {
        supplierDaoJdbc.add("Mock4 supplier", "Mock description 4");
        assertEquals("id: 4, name: Mock4 supplier, description: Mock description 4", supplierDaoJdbc.find(4).stringify());
    }

    @Test
    void Should_Find_Supplier_By_Given_Id() {
        assertEquals("id: 1, name: Mock1 supplier, description: Mock description 1", supplierDaoJdbc.find(1).stringify());
        assertEquals("id: 2, name: Mock2 supplier, description: Mock description 2", supplierDaoJdbc.find(2).stringify());
        assertEquals("id: 3, name: Mock3 supplier, description: Mock description 3", supplierDaoJdbc.find(3).stringify());
    }

    @Test
    void Should_Remove_Supplier_Of_Given_Id() {
        supplierDaoJdbc.remove(1);
        supplierDaoJdbc.remove(2);
        supplierDaoJdbc.remove(3);
        assertNull(supplierDaoJdbc.find(1));
        assertNull(supplierDaoJdbc.find(2));
        assertNull(supplierDaoJdbc.find(3));
    }

    @Test
    void Should_Return_All_Suppliers_In_Database() {
        List<Supplier> testList = new ArrayList<>();
        testList.add(supplierDaoJdbc.find(1));
        testList.add(supplierDaoJdbc.find(2));
        testList.add(supplierDaoJdbc.find(3));
        String testListString = testList.stream().map(supplier -> supplier.stringify()).reduce(", ", String::concat);
        String mockListString = supplierDaoJdbc.getAll().stream().map(supplier -> supplier.stringify()).reduce(", ", String::concat);
        assertEquals(mockListString, testListString);
    }


}