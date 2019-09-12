package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SupplierDaoMemTest {

    private SupplierDaoMem supplierDaoMem = SupplierDaoMem.getInstance();

    @Test
    public void testAddNewSupplier() {
        Supplier testSupplier = createSupplier().get(0);
        Supplier otherTestSupplier = createSupplier().get(1);
        supplierDaoMem.add(testSupplier);
        supplierDaoMem.add(otherTestSupplier);
        assertEquals(testSupplier, supplierDaoMem.find(1));
        assertEquals(otherTestSupplier, supplierDaoMem.find(2));
    }

    @Test
    public void testFindSupplierByGivenId() {
        Supplier supp = createSupplier().get(0);
        supplierDaoMem.add(supp);
        assertEquals("testSupplier1", supplierDaoMem.find(1).getName());
    }

    @Test
    public void testRemoveSupplierByGivenId() {
        Supplier supp = createSupplier().get(0);
        supplierDaoMem.add(supp);
        supplierDaoMem.remove(1);
        assertNull(supplierDaoMem.find(1));
        assertEquals(0, supplierDaoMem.getNumberOfSuppliers());
    }

    @Test
    public void testGetAllSuppliers() {
        List<Supplier> testList = new ArrayList<>();
        Supplier testSupplier = createSupplier().get(0);
        Supplier otherTestSupplier = createSupplier().get(1);
        supplierDaoMem.add(testSupplier);
        supplierDaoMem.add(otherTestSupplier);
        testList.add(testSupplier);
        testList.add(otherTestSupplier);
        assertEquals(testList, supplierDaoMem.getAll());
    }

    public List<Supplier> createSupplier() {
        List<Supplier> supplierList = new ArrayList<>();
        Supplier supp1 = new Supplier("testSupplier1", "only for test");
        Supplier supp2 = new Supplier("testSupplier2", "only for test");
        supplierList.add(supp1);
        supplierList.add(supp2);
        return supplierList;

    }
}