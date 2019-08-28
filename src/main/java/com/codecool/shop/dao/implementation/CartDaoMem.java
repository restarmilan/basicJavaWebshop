package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartDaoMem implements CartDao {

    private static CartDaoMem instance = null;
    Map<Product, Integer> cart = new HashMap<>();

    private CartDaoMem(){}

    public static CartDaoMem getInstance(){
        if (instance == null){
            instance = new CartDaoMem();
        }
        return instance;
    }


    @Override
    public void addToCart(Product product) {
        if(cart.containsKey(product)){
            int currentValue = cart.get(product);
            cart.replace(product, currentValue+1);
        }else{
            cart.put(product, 1);
        }
    }

    @Override
    public Map<Product, Integer> getCart() {

        return cart;
    }

    @Override
    public void removeFromCart(Product product) {
        cart.remove(product);
    }

    @Override
    public float getSumOfPrices() {
        float sum = 0;
        for(Product item:cart.keySet()){
            sum += item.getDefaultPrice() * cart.get(item);
        }
        return sum;
    }
}
