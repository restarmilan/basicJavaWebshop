package com.codecool.shop.dao;

import com.codecool.shop.controller.DatabaseController;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMemJdbc;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoJdbcTest {

    private ProductCategoryDaoJdbc productCategoryDaoJdbc = new ProductCategoryDaoMemJdbc("jdbc:postgresql://localhost:5432/mock_db_codecoolshop", "zsana", "mandolin");

    @BeforeEach
    void addProductCategoriesToProductCategoryTable() {
        DatabaseController controller = new DatabaseController("jdbc:postgresql://localhost:5432/mock_db_codecoolshop", "zsana", "mandolin");
        Connection connection = controller.getConnection();
        PreparedStatement ps, ps2, ps3, ps4, ps5;
        try {
            ps = connection.prepareStatement("INSERT INTO productcategory(name, department, description) " +
                    "VALUES ('Mock productcategory 1', 'Hardware', 'Mock description 1')");
            ps2 = connection.prepareStatement("INSERT INTO productcategory(name, department, description) " +
                    "VALUES ('Mock productcategory 2', 'Hardware', 'Mock description 2')");
            ps3 = connection.prepareStatement("INSERT INTO productcategory(name, department, description) " +
                    "VALUES ('Mock productcategory 3', 'Hardware', 'Mock description 3')");
            ps4 = connection.prepareStatement("INSERT INTO productcategory(name, department, description) " +
                    "VALUES ('Mock productcategory 4', 'Hardware', 'Mock description 4')");
            ps5 = connection.prepareStatement("INSERT INTO productcategory(name, department, description) " +
                    "VALUES ('Mock productcategory 5', 'Hardware', 'Mock description 5')");
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
    void truncateTable() {
        DatabaseController controller = new DatabaseController("jdbc:postgresql://localhost:5432/mock_db_codecoolshop", "zsana", "mandolin");
        Connection connection = controller.getConnection();
        String query = "TRUNCATE TABLE productcategory CASCADE;";
        String query2 = "ALTER SEQUENCE productcategory_id_seq RESTART WITH 1;";
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
    void Should_Add_New_ProductCategory_With_Given_Parameters() {
        productCategoryDaoJdbc.add("Mock productcategory 6", "Hardware", "Mock description 6");
        assertEquals("id: 6, name: Mock productcategory 6, department: Hardware, description: Mock description 6", productCategoryDaoJdbc.find(6).stringify());
    }

    @Test
    void Should_Return_ProductCategory_Of_Given_Id() {
        assertEquals("id: 1, name: Mock productcategory 1, department: Hardware, description: Mock description 1", productCategoryDaoJdbc.find(1).stringify());
        assertEquals("id: 2, name: Mock productcategory 2, department: Hardware, description: Mock description 2", productCategoryDaoJdbc.find(2).stringify());
        assertEquals("id: 3, name: Mock productcategory 3, department: Hardware, description: Mock description 3", productCategoryDaoJdbc.find(3).stringify());
    }

    @Test
    void Should_Remove_Productcategory_Of_Given_Id() {
        productCategoryDaoJdbc.remove(4);
        productCategoryDaoJdbc.remove(5);
        assertNull(productCategoryDaoJdbc.find(4));
        assertNull(productCategoryDaoJdbc.find(5));
    }

    @Test
    void Should_Return_All_ProductCategory_From_Database() {
        List<ProductCategory> testList = new ArrayList<>();
        testList.add(new ProductCategory(1, "Mock productcategory 1", "Hardware", "Mock description 1"));
        testList.add(new ProductCategory(2, "Mock productcategory 2", "Hardware", "Mock description 2"));
        testList.add(new ProductCategory(3, "Mock productcategory 3", "Hardware", "Mock description 3"));
        testList.add(new ProductCategory(4, "Mock productcategory 4", "Hardware", "Mock description 4"));
        testList.add(new ProductCategory(5, "Mock productcategory 5", "Hardware", "Mock description 5"));
        String testListString = testList.stream().map(prodCat -> prodCat.stringify()).reduce(", ", String::concat);
        String mockListString = productCategoryDaoJdbc.getAll().stream().map(prodCat -> prodCat.stringify()).reduce(", ", String::concat);
        assertEquals(testListString, mockListString);
    }

}