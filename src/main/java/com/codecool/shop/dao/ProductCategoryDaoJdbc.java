package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.util.List;

public interface ProductCategoryDaoJdbc {

    String add(Connection connection, String name, String department, String description);

    ProductCategory find(Connection connection, int id);

    String remove(Connection connection, int id);

    List<ProductCategory> getAll(Connection connection);

}
