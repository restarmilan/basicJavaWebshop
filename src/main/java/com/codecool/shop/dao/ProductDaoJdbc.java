package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import java.util.Currency;
import java.util.List;

public interface ProductDaoJdbc {

    void add(String name, float defaultPrice, String defaultCurrency, String description, int productCategoryId, int supplierId);

    Product find(int id);

    void remove(int id);

    List<Product> getAll();

}
