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

    ProductDaoJdbc productDaoJdbc;

    @BeforeEach
    void addProductsToProductTable() {
        productDaoJdbc = new ProductDaoMemJdbc("jdbc:postgresql://localhost:5432/mock_db_codecoolshop", "zsana", "mandolin");
        productDaoJdbc.add("Mock product 1", 10, "USD", "Mock description 1", 1, 1);
        productDaoJdbc.add("Mock product 2", 20, "USD", "Mock description 2", 2, 2);
        productDaoJdbc.add("Mock product 3", 30, "USD", "Mock description 3", 3, 3);
        productDaoJdbc.add("Mock product 4", 40, "USD", "Mock description 4", 2, 2);
        productDaoJdbc.add("Mock product 5", 50, "USD", "Mock description 5", 1, 1);
    }

    @AfterEach
    void truncateTable() {
        DatabaseController controller = new DatabaseController("jdbc:postgresql://localhost:5432/mock_db_codecoolshop", "zsana", "mandolin");
        Connection connection = controller.getConnection();
        String query = "TRUNCATE TABLE product;";
        String query2 = "ALTER SEQUENCE product_id_seq RESTART WITH 1;";
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
        Product testProduct1 = new Product(1, "Mock product 1", 10, "USD", "Mock description 1", 1, 1);
        Product testProduct2 = new Product(2, "Mock product 2", 20, "USD", "Mock description 2", 2, 2);
        Product testProduct3 = new Product(3, "Mock product 3", 30, "USD", "Mock description 3", 3, 3);
        Product testProduct4 = new Product(4, "Mock product 4", 40, "USD", "Mock description 4", 2, 2);
        Product testProduct5 = new Product(5, "Mock product 5", 50, "USD", "Mock description 5", 1, 1);
        assertEquals(testProduct1.toString(), productDaoJdbc.find(1).toString());
        assertEquals(testProduct2.toString(), productDaoJdbc.find(2).toString());
        assertEquals(testProduct3.toString(), productDaoJdbc.find(3).toString());
        assertEquals(testProduct4.toString(), productDaoJdbc.find(4).toString());
        assertEquals(testProduct5.toString(), productDaoJdbc.find(5).toString());
    }
}