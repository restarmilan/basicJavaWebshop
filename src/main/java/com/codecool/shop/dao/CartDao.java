package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

import java.util.List;
import java.util.Map;

public interface CartDao {

    void addToCart(Product product);
    Map<Product,Integer> getCart();
    void removeFromCart(Product product);
    float getSumOfPrices();
}
