package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductDaoMemJdbc;
import com.codecool.shop.model.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoJdbcTest {

    private ProductDaoJdbc productDaoJdbc = new ProductDaoMemJdbc("jdbc:postgresql://localhost:5432/mock_db_codecoolshop", "zsana", "mandolin");

    @Test
    void Should_Add_New_Products_To_Database() {
        productDaoJdbc.add("Mock product 1", 10, "USD", "Mock description 1", 1, 1);
        productDaoJdbc.add("Mock product 2", 20, "USD", "Mock description 1", 2, 2);
        productDaoJdbc.add("Mock product 3", 30, "USD", "Mock description 1", 3, 3);
        productDaoJdbc.add("Mock product 4", 40, "USD", "Mock description 1", 2, 2);
        productDaoJdbc.add("Mock product 5", 50, "USD", "Mock description 1", 1, 1);
        Product product1 = productDaoJdbc.find(2);
        Product product2 = productDaoJdbc.find(3);
        Product product3 = productDaoJdbc.find(4);
        assertEquals("id: 2, name: Mock product 1, defaultPrice: 10,000000, defaultCurrency: USD, productCategory: 1, supplier: 1", product1.stringify());
        assertEquals("id: 3, name: Mock product 2, defaultPrice: 20,000000, defaultCurrency: USD, productCategory: 2, supplier: 2", product2.stringify());
        assertEquals("id: 4, name: Mock product 3, defaultPrice: 30,000000, defaultCurrency: USD, productCategory: 3, supplier: 3", product3.stringify());

    }

    @Test
    void Should_Return_Product_With_Given_Id() {
        Product product1 = productDaoJdbc.find(2);
        Product product2 = productDaoJdbc.find(3);
        Product product3 = productDaoJdbc.find(4);
        assertEquals("id: 2, name: Mock product 1, defaultPrice: 10,000000, defaultCurrency: USD, productCategory: 1, supplier: 1", product1.stringify());
        assertEquals("id: 3, name: Mock product 2, defaultPrice: 20,000000, defaultCurrency: USD, productCategory: 2, supplier: 2", product2.stringify());
        assertEquals("id: 4, name: Mock product 3, defaultPrice: 30,000000, defaultCurrency: USD, productCategory: 3, supplier: 3", product3.stringify());
    }

    @Test
    void Should_Remove_Product_By_Id_From_Database() {
        productDaoJdbc.remove(5);
        productDaoJdbc.remove(6);
        assertNull(productDaoJdbc.find(5));
        assertNull(productDaoJdbc.find(6));
    }

    @Test
    void Should_Return_All_Products_From_Database() {
        List<Product> testList = new ArrayList<>();
        testList.add(new Product(2, "Mock product 1", 10, "USD", "Mock description 1", 1, 1));
        testList.add(new Product(3, "Mock product 2", 20, "USD", "Mock description 1", 2, 2));
        testList.add(new Product(4, "Mock product 3", 30, "USD", "Mock description 1", 3, 3));
        List<Product> mockList = new ArrayList<>();
        mockList.add(productDaoJdbc.find(2));
        mockList.add(productDaoJdbc.find(3));
        mockList.add(productDaoJdbc.find(4));
        String testListString = testList.stream().map(product -> product.stringify()).reduce(", ", String::concat);
        String mockListString = mockList.stream().map(product -> product.stringify()).reduce(", ", String::concat);
        assertEquals(testListString, mockListString);
    }
}