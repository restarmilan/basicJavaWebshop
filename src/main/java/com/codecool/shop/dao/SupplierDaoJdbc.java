package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.util.List;

public interface SupplierDaoJdbc {

    String add(Connection connection, String name, String description);

    Supplier find(Connection connection, int id);

    String remove(Connection connection, int id);

    List<Supplier> getAll(Connection connection);

}
