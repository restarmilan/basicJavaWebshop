package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.util.List;

public interface ProductDaoJdbc {

    String add(Connection connection, String name, float defaultPrice, String defaultCurrency, String description, int productCategoryId, int supplierId);

    Supplier find(Connection connection, int id);

    String remove(Connection connection, int id);

    List<Supplier> getAll(Connection connection);

}
