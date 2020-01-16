package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.SupplierDaoMemJdbc;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoJdbcTest {

    private SupplierDaoJdbc supplierDaoJdbc = new SupplierDaoMemJdbc("jdbc:postgresql://localhost:5432/mock_db_codecoolshop", "zsana", "mandolin");

    @Test
    void Should_Add_New_Supplier_With_Given_Parameters() {
        supplierDaoJdbc.add("Mock1 supplier", "Mock description 1");
        supplierDaoJdbc.add("Mock2 supplier", "Mock description 2");
        supplierDaoJdbc.add("Mock3 supplier", "Mock description 3");
        Supplier supplier1 = supplierDaoJdbc.find(1);
        Supplier supplier2 = supplierDaoJdbc.find(2);
        Supplier supplier3 = supplierDaoJdbc.find(3);
        assertEquals("id: 1, name: Mock1 supplier, description: Mock description 1", supplier1.stringify());
        assertEquals("id: 2, name: Mock2 supplier, description: Mock description 2", supplier2.stringify());
        assertEquals("id: 3, name: Mock3 supplier, description: Mock description 3", supplier3.stringify());
    }

    @Test
    void Should_Find_Supplier_By_Given_Id() {
        Supplier supplier1 = supplierDaoJdbc.find(1);
        Supplier supplier2 = supplierDaoJdbc.find(2);
        Supplier supplier3 = supplierDaoJdbc.find(3);
        assertEquals("id: 1, name: Mock1 supplier, description: Mock description 1", supplier1.stringify());
        assertEquals("id: 2, name: Mock2 supplier, description: Mock description 2", supplier2.stringify());
        assertEquals("id: 3, name: Mock3 supplier, description: Mock description 3", supplier3.stringify());
    }

    @Test
    void Should_Remove_Supplier_By_Given_Id() {
        supplierDaoJdbc.remove(9);
        supplierDaoJdbc.remove(8);
        supplierDaoJdbc.remove(7);
        assertNull(supplierDaoJdbc.find(9));
        assertNull(supplierDaoJdbc.find(8));
        assertNull(supplierDaoJdbc.find(7));
    }

    @Test
    void Should_Return_All_Suppliers_In_Database() {
        List<Supplier> mockList = new ArrayList<>();
        mockList.add(supplierDaoJdbc.find(1));
        mockList.add(supplierDaoJdbc.find(2));
        mockList.add(supplierDaoJdbc.find(3));
        mockList.add(supplierDaoJdbc.find(4));
        mockList.add(supplierDaoJdbc.find(5));
        mockList.add(supplierDaoJdbc.find(6));
        List<Supplier> testList = supplierDaoJdbc.getAll();
        String mockListString = mockList.stream().map(supplier -> supplier.stringify()).reduce(", ", String::concat);
        String testListString = testList.stream().map(supplier -> supplier.stringify()).reduce(", ", String::concat);
        assertEquals(mockListString, testListString);
    }


}