package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;

import java.util.HashMap;
import java.util.Map;

public class CartDaoMem implements CartDao {

    Map<Product, Integer> cartOfAllProducts;

    public CartDaoMem(){
        cartOfAllProducts = new HashMap<>();
    }




    @Override
    public void addToCart(Product product) {
        if(cartOfAllProducts.containsKey(product)){
            int currentValue = cartOfAllProducts.get(product);
            cartOfAllProducts.replace(product, currentValue+1);
        }else{
            cartOfAllProducts.put(product, 1);
        }
    }

    @Override
    public Map<Product, Integer> getCartOfAllProducts() {

        return cartOfAllProducts;
    }

    @Override
    public void removeFromCart(Product product) {
        cartOfAllProducts.remove(product);
    }

    @Override
    public float getSumOfPrices() {
        float sum = 0;
        for(Product item: cartOfAllProducts.keySet()){
            sum += item.getDefaultPrice() * cartOfAllProducts.get(item);
        }
        return sum;
    }

    @Override
    public int getsumOfAllProducts() {
        int sum = 0;
        for (Integer value : cartOfAllProducts.values()) {
            sum += value;
        }
        return sum;
    }
}
