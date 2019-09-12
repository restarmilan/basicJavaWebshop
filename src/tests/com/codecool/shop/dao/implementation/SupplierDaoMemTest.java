package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SupplierDaoMemTest {

    private SupplierDaoMem supplierDaoMem = SupplierDaoMem.getInstance();

    @Test
    public void testIsAddingANewProductCategory() {

        Supplier supp1 = new Supplier("testSupplier1", "only for test");
        Supplier supp2 = new Supplier("testSupplier2", "only for test");
        supplierDaoMem.add(supp1);
        supplierDaoMem.add(supp2);
        assertEquals(2, supplierDaoMem.getDataSize());
        assertEquals("testSupplier2", supplierDaoMem.find(2).getName());
    }

    @Test
    public void testIsFindCategoryByAGivenId() {
        Supplier supp = supplierDaoMem.find(1);
        assertEquals("testSupplier1", supp.getName());
    }

    @Test
    public void testIsRemovingByAGivenId() {
        supplierDaoMem.remove(2);
        assertNull(supplierDaoMem.find(2));
    }

//    @Test
//    public void testIsGivesBackAll(){
//        Supplier supp3 = new Supplier ("testSupplier1", "only for test");
//        Supplier supp4 = new Supplier ("testSupplier2", "only for test");
//        supplierDaoMem.add(supp3);
//        supplierDaoMem.add(supp4);
//        Supplier testSupp = supplierDaoMem.find(1);
//        Supplier testSupp2 = supplierDaoMem.find(2);
//        List<Supplier>suppList = new ArrayList<>();
//        suppList.add(testSupp);
//        suppList.add(testSupp2);
//        assertEquals(suppList.size(), supplierDaoMem.getDataSize());
//    }
}