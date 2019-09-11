package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;
import java.util.List;

public interface ProductCategoryDaoJdbc {

    void add(String name, String department, String description);

    ProductCategory find(int id);

    void remove(int id);

    List<ProductCategory> getAll();

}
