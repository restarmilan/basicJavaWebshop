package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Supplier extends BaseModel {
    private List<Product> products;

    public Supplier(String name, String description) {
        super(name);
        this.products = new ArrayList<>();
    }

    public Supplier(int databaseId, String name, String description) {
        super(databaseId, name, description);
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "description: %3$s",
                this.id,
                this.name,
                this.description
        );
    }

    public String stringify() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "description: %3$s",
                databaseId,
                this.name,
                this.description
        );
    }
}