package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ProductCategoryDaoMemTest {

    private ProductCategoryDaoMem productCategoryDaoMem = ProductCategoryDaoMem.getInstance();

    @Test
    public void testIsAddingANewProductCategory() {
        ProductCategory testCategory = new ProductCategory("testname", "testdepartment", "category only for testing");
        ProductCategory otherTestCategory = new ProductCategory("otherTestName", "testdepartment", "category only for testing");
        productCategoryDaoMem.add(testCategory);
        productCategoryDaoMem.add(otherTestCategory);
        assertEquals(testCategory, productCategoryDaoMem.find(1));
        assertEquals(otherTestCategory, productCategoryDaoMem.find(2));
    }

    @Test
    public void testIsFindCategoryByAGivenId() {
        ProductCategory category = productCategoryDaoMem.find(1);
        assertEquals("testname", category.getName());
        assertEquals("testdepartment", category.getDepartment());
    }

    @Test
    public void testIsRemovingByAGivenId() {
        productCategoryDaoMem.remove(2);
        assertNull(productCategoryDaoMem.find(2));
    }
}