package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ProductCategoryDaoMemTest {

    private ProductCategoryDaoMem productCategoryDaoMem = ProductCategoryDaoMem.getInstance();

    @Test
    public void testAddANewProductCategory() {
        ProductCategory testCategory = createProductCategory().get(0);
        ProductCategory otherTestCategory = createProductCategory().get(1);
        productCategoryDaoMem.add(testCategory);
        productCategoryDaoMem.add(otherTestCategory);
        assertEquals(testCategory, productCategoryDaoMem.find(1));
        assertEquals(otherTestCategory, productCategoryDaoMem.find(2));
    }

    @Test
    public void testFindProductCategoryByAGivenId() {
        ProductCategory category = createProductCategory().get(0);
        productCategoryDaoMem.add(category);
        assertEquals("testname", productCategoryDaoMem.find(1).getName());
        assertEquals("testdepartment", productCategoryDaoMem.find(1).getDepartment());
    }

    @Test
    public void testRemoveProductByAGivenId() {
        ProductCategory category = createProductCategory().get(0);
        productCategoryDaoMem.add(category);
        productCategoryDaoMem.remove(1);
        assertNull(productCategoryDaoMem.find(1));
    }

    @Test
    public void testGetAllProductcategories() {
        List<ProductCategory> testList = new ArrayList<>();
        ProductCategory testCategory = createProductCategory().get(0);
        ProductCategory otherTestCategory = createProductCategory().get(1);
        productCategoryDaoMem.add(testCategory);
        productCategoryDaoMem.add(otherTestCategory);
        testList.add(testCategory);
        testList.add(otherTestCategory);
        assertEquals(testList, productCategoryDaoMem.getAll());
    }

    public List<ProductCategory> createProductCategory() {
        List<ProductCategory> categoryList = new ArrayList<>();
        ProductCategory testCategory = new ProductCategory("testname", "testdepartment", "category only for testing");
        ProductCategory otherTestCategory = new ProductCategory("otherTestName", "testdepartment", "category only for testing");
        categoryList.add(testCategory);
        categoryList.add((otherTestCategory));
        return categoryList;
    }
}