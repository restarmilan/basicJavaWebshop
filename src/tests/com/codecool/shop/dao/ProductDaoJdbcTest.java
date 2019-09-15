package com.codecool.shop.dao;

import com.codecool.shop.controller.DatabaseController;
import com.codecool.shop.dao.implementation.ProductDaoMemJdbc;
import com.codecool.shop.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoJdbcTest {

    ProductDaoJdbc productDaoJdbc = new ProductDaoMemJdbc("jdbc:postgresql://localhost:5432/mock_db_codecoolshop", "zsana", "mandolin");

    @BeforeEach
    void insertDataToDatabase() {
        DatabaseController controller = new DatabaseController("jdbc:postgresql://localhost:5432/mock_db_codecoolshop", "zsana", "mandolin");
        Connection connection = controller.getConnection();
        PreparedStatement ps, ps2, ps3, ps4, ps5, psS1, psS2, psS3, psP1, psP2, psP3, psP4, psP5;
        try {
            ps = connection.prepareStatement("INSERT INTO product(name, default_price, default_currency, description, productcategory_id, supplier_id) " +
                    "VALUES ('Mock product 1', 10, 'USD', 'Mock description 1', 1, 1)");
            ps2 = connection.prepareStatement("INSERT INTO product(name, default_price, default_currency, description, productcategory_id, supplier_id) " +
                    "VALUES ('Mock product 2', 20, 'USD', 'Mock description 2', 2, 2)");
            ps3 = connection.prepareStatement("INSERT INTO product(name, default_price, default_currency, description, productcategory_id, supplier_id) " +
                    "VALUES ('Mock product 3', 30, 'USD', 'Mock description 3', 3, 3)");
            ps4 = connection.prepareStatement("INSERT INTO product(name, default_price, default_currency, description, productcategory_id, supplier_id) " +
                    "VALUES ('Mock product 4', 40, 'USD', 'Mock description 4', 2, 2)");
            ps5 = connection.prepareStatement("INSERT INTO product(name, default_price, default_currency, description, productcategory_id, supplier_id) " +
                    "VALUES ('Mock product 5', 50, 'USD', 'Mock description 5', 1, 1)");
            psS1 = connection.prepareStatement("INSERT INTO suppliers(name, description) VALUES ('Mock1 supplier', 'Mock description 1')");
            psS2 = connection.prepareStatement("INSERT INTO suppliers(name, description) VALUES ('Mock2 supplier', 'Mock description 2')");
            psS3 = connection.prepareStatement("INSERT INTO suppliers(name, description) VALUES ('Mock3 supplier', 'Mock description 3')");
            psP1 = connection.prepareStatement("INSERT INTO productcategory(name, department, description) " +
                    "VALUES ('Mock productcategory 1', 'Hardware', 'Mock description 1')");
            psP2 = connection.prepareStatement("INSERT INTO productcategory(name, department, description) " +
                    "VALUES ('Mock productcategory 2', 'Hardware', 'Mock description 2')");
            psP3 = connection.prepareStatement("INSERT INTO productcategory(name, department, description) " +
                    "VALUES ('Mock productcategory 3', 'Hardware', 'Mock description 3')");
            psP4 = connection.prepareStatement("INSERT INTO productcategory(name, department, description) " +
                    "VALUES ('Mock productcategory 4', 'Hardware', 'Mock description 4')");
            psP5 = connection.prepareStatement("INSERT INTO productcategory(name, department, description) " +
                    "VALUES ('Mock productcategory 5', 'Hardware', 'Mock description 5')");
            psS1.executeUpdate();
            psS2.executeUpdate();
            psS3.executeUpdate();
            psP1.executeUpdate();
            psP2.executeUpdate();
            psP3.executeUpdate();
            psP4.executeUpdate();
            psP5.executeUpdate();
            ps.executeUpdate();
            ps2.executeUpdate();
            ps3.executeUpdate();
            ps4.executeUpdate();
            ps5.executeUpdate();

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
    void truncateAllTables() {
        DatabaseController controller = new DatabaseController("jdbc:postgresql://localhost:5432/mock_db_codecoolshop", "zsana", "mandolin");
        Connection connection = controller.getConnection();
        String query = "TRUNCATE TABLE product CASCADE;";
        String query2 = "ALTER SEQUENCE product_id_seq RESTART WITH 1;";
        String query3 = "TRUNCATE TABLE suppliers CASCADE;";
        String query4 = "ALTER SEQUENCE suppliers_id_seq RESTART WITH 1;";
        String query5 = "TRUNCATE TABLE productcategory CASCADE;";
        String query6 = "ALTER SEQUENCE productcategory_id_seq RESTART WITH 1;";
        PreparedStatement ps, ps2, ps3, ps4, ps5, ps6;
        try {
            ps = connection.prepareStatement(query);
            ps2 = connection.prepareStatement(query2);
            ps3 = connection.prepareStatement(query3);
            ps4 = connection.prepareStatement(query4);
            ps5 = connection.prepareStatement(query5);
            ps6 = connection.prepareStatement(query6);
            ps.executeUpdate();
            ps2.executeUpdate();
            ps3.executeUpdate();
            ps4.executeUpdate();
            ps5.executeUpdate();
            ps6.executeUpdate();
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
    void Should_Add_New_Products_To_Database() {
        productDaoJdbc.add("Mock product 6", 10, "USD", "Mock description 6", 1, 1);
        assertEquals("id: 6, name: Mock product 6, defaultPrice: 10,000000, defaultCurrency: USD, productCategory: 1, supplier: 1", productDaoJdbc.find(6).toString());
    }

    @Test
    void Should_Return_Product_With_Given_Id() {
        Product testProduct1 = new Product(1, "Mock product 1", 10, "USD", "Mock description 1", 1, 1);
        Product testProduct2 = new Product(2, "Mock product 2", 20, "USD", "Mock description 2", 2, 2);
        assertEquals(testProduct1.toString(), productDaoJdbc.find(1).toString());
        assertEquals(testProduct2.toString(), productDaoJdbc.find(2).toString());
    }

    @Test
    void Should_Remove_Product_By_Id_From_Database() {
        productDaoJdbc.remove(3);
        productDaoJdbc.remove(4);
        productDaoJdbc.remove(5);
        assertNull(productDaoJdbc.find(3));
        assertNull(productDaoJdbc.find(4));
        assertNull(productDaoJdbc.find(5));
    }

    @Test
    void Should_Return_All_Products_From_Database() {
        List<Product> testList = new ArrayList<>();
        testList.add(new Product(1, "Mock product 1", 10, "USD", "Mock description 1", 1, 1));
        testList.add(new Product(2, "Mock product 2", 20, "USD", "Mock description 2", 2, 2));
        testList.add(new Product(3, "Mock product 3", 30, "USD", "Mock description 3", 3, 3));
        testList.add(new Product(4, "Mock product 4", 40, "USD", "Mock description 4", 2, 2));
        testList.add(new Product(5, "Mock product 5", 50, "USD", "Mock description 5", 1, 1));
        String testListString = testList.stream().map(product -> product.toString()).reduce(", ", String::concat);
        String mockListString = productDaoJdbc.getAll().stream().map(product -> product.toString()).reduce(", ", String::concat);
        assertEquals(testListString, mockListString);
    }
}