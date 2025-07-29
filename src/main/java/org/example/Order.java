package org.example;

import java.util.List;

public class Order {
    private static int counter = 1;
    private String id;
    private String user;
    private List<Product> products;

    public Order(String user, List<Product> products) {
        this.user = user;
        this.products = products;
        this.id = "CMD" + (counter++);
    }

    public String getId() {
        return id;
    }

    public List<Product> getProducts() {
        return products;
    }
}