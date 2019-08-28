package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

import java.util.List;

public interface CartDao {

    void addToCart(Product product);
    List<Product> getCart();
    void removeFromCart(Product product);
}
