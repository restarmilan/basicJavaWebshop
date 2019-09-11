package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMemJdbc;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoJdbcTest {

    private ProductCategoryDaoJdbc productCategoryDaoJdbc = new ProductCategoryDaoMemJdbc("jdbc:postgresql://localhost:5432/mock_db_codecoolshop", "zsana", "mandolin");

    @Test
    void Should_Add_New_Productcategory_With_Given_Parameters() {
        productCategoryDaoJdbc.add("Mock productcategory 1", "Hardware", "Mock description 1");
        productCategoryDaoJdbc.add("Mock productcategory 2", "Hardware", "Mock description 2");
        productCategoryDaoJdbc.add("Mock productcategory 3", "Hardware", "Mock description 3");
        productCategoryDaoJdbc.add("Mock productcategory 4", "Hardware", "Mock description 4");
        productCategoryDaoJdbc.add("Mock productcategory 5", "Hardware", "Mock description 5");
        productCategoryDaoJdbc.add("Mock productcategory 6", "Hardware", "Mock description 6");
        ProductCategory pc1 = productCategoryDaoJdbc.find(1);
        ProductCategory pc2 = productCategoryDaoJdbc.find(2);
        ProductCategory pc3 = productCategoryDaoJdbc.find(3);
        ProductCategory pc4 = productCategoryDaoJdbc.find(4);
        ProductCategory pc5 = productCategoryDaoJdbc.find(5);
        ProductCategory pc6 = productCategoryDaoJdbc.find(6);
        assertEquals("id: 1, name: Mock productcategory 1, department: Hardware, description: Mock description 1", pc1.stringify());
        assertEquals("id: 2, name: Mock productcategory 2, department: Hardware, description: Mock description 2", pc2.stringify());
        assertEquals("id: 3, name: Mock productcategory 3, department: Hardware, description: Mock description 3", pc3.stringify());
        assertEquals("id: 4, name: Mock productcategory 4, department: Hardware, description: Mock description 4", pc4.stringify());
        assertEquals("id: 5, name: Mock productcategory 5, department: Hardware, description: Mock description 5", pc5.stringify());
        assertEquals("id: 6, name: Mock productcategory 6, department: Hardware, description: Mock description 6", pc6.stringify());
    }

    @Test
    void Should_Return_ProductCategory_Of_Given_Id() {
        ProductCategory pc1 = productCategoryDaoJdbc.find(1);
        ProductCategory pc2 = productCategoryDaoJdbc.find(2);
        ProductCategory pc3 = productCategoryDaoJdbc.find(3);
        assertEquals("id: 1, name: Mock productcategory 1, department: Hardware, description: Mock description 1", pc1.stringify());
        assertEquals("id: 2, name: Mock productcategory 2, department: Hardware, description: Mock description 2", pc2.stringify());
        assertEquals("id: 3, name: Mock productcategory 3, department: Hardware, description: Mock description 3", pc3.stringify());
    }

    @Test
    void Should_Remove_Productcategory_By_Given_Id() {
        productCategoryDaoJdbc.remove(4);
        productCategoryDaoJdbc.remove(5);
        productCategoryDaoJdbc.remove(6);
        assertNull(productCategoryDaoJdbc.find(4));
        assertNull(productCategoryDaoJdbc.find(5));
        assertNull(productCategoryDaoJdbc.find(6));
    }

    @Test
    void Should_Return_All_ProductCategory_From_Database() {
        List<ProductCategory> testList = new ArrayList<>();
        testList.add(new ProductCategory(1, "Mock productcategory 1", "Hardware", "Mock description 1"));
        testList.add(new ProductCategory(2, "Mock productcategory 2", "Hardware", "Mock description 2"));
        testList.add(new ProductCategory(3, "Mock productcategory 3", "Hardware", "Mock description 3"));
        List<ProductCategory> mockList = new ArrayList<>();
        mockList.add(productCategoryDaoJdbc.find(1));
        mockList.add(productCategoryDaoJdbc.find(2));
        mockList.add(productCategoryDaoJdbc.find(3));
        String testListString = testList.stream().map(prodCat -> prodCat.stringify()).reduce(", ", String::concat);
        String mockListString = mockList.stream().map(prodCat -> prodCat.stringify()).reduce(", ", String::concat);
        assertEquals(testListString, mockListString);
    }

}