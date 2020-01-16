package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;

import java.util.List;

public interface SupplierDaoJdbc {

    void add(String name, String description);

    Supplier find(int id);

    void remove(int id);

    List<Supplier> getAll();

}
