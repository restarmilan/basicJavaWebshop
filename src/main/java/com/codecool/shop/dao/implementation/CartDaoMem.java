package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartDaoMem implements CartDao {

    private static CartDaoMem instance = null;
    List<Product> cart = new ArrayList<>();

    private CartDaoMem(){}

    public static CartDaoMem getInstance(){
        if (instance == null){
            instance = new CartDaoMem();
        }
        return instance;
    }


    @Override
    public void addToCart(Product product) {
        cart.add(product);
    }

    @Override
    public List<Product>getCart() {

        return cart;
    }

    @Override
    public void removeFromCart(Product product) {
        cart.remove(product);
    }
}
