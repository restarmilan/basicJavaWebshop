package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ProductDaoMemTest {

    private ProductDaoMem productDaoMem = ProductDaoMem.getInstance();
    private ProductCategoryDaoMem productCategoryDaoMem = ProductCategoryDaoMem.getInstance();
    private SupplierDaoMem supplierDaoMem = SupplierDaoMem.getInstance();

    @Test
    public void testAddNewProduct() {
        Product product = createProduct();
        productDaoMem.add(product);
        assertEquals(product, productDaoMem.find(1));
    }

    @Test
    public void testIsNumberOfProductsCorrect() {
        Product product = createProduct();
        productDaoMem.add(product);
        assertEquals(1, productDaoMem.getNumberOfProducts());
    }

    @Test
    public void testFindProductByGivenID() {
        Product product = createProduct();
        productDaoMem.add(product);
        assertEquals("testProduct", productDaoMem.find(1).getName());
        assertEquals("product for test", productDaoMem.find(1).getDescription());
    }

    @Test
    public void testRemoveByGivenId() {
        Product product = createProduct();
        productDaoMem.add(product);
        productDaoMem.remove(1);
        assertEquals(0, productDaoMem.getNumberOfProducts());
        assertNull(productDaoMem.find(1));
    }

    @Test
    public void testFindProductBySupplier() {
        Product product = createProduct();
        productDaoMem.add(product);
        List<Product> testList = new ArrayList<>();
        testList.add(product);
        assertEquals(testList, productDaoMem.getBy(supplierDaoMem.find(1)));
    }

    @Test
    public void testFindProductByCategory() {
        Product product = createProduct();
        productDaoMem.add(product);
        List<Product> testList = new ArrayList<>();
        testList.add(product);
        assertEquals(testList, productDaoMem.getBy(productCategoryDaoMem.find(1)));
    }

    @Test
    public void testGetAllProducts() {
        Product product = createProduct();
        productDaoMem.add(product);
        Product product2 = new Product("testProduct2", 0, "USD", "product for test", productCategoryDaoMem.find(1), supplierDaoMem.find(1));
        productDaoMem.add(product2);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(product2);
        assertEquals(productList, productDaoMem.getAll());
    }

    public Product createProduct() {
        ProductCategory pc = new ProductCategory("testCategory", "testDepartment", "category only for test");
        productCategoryDaoMem.add(pc);
        Supplier supp = new Supplier("testSupplier", "supplier only for test");
        supplierDaoMem.add(supp);
        Product product = new Product("testProduct", 0, "USD", "product for test", productCategoryDaoMem.find(1), supplierDaoMem.find(1));
        return product;
    }
}