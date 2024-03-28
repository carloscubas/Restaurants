package com.restaurant.cooking.model;

import java.util.List;

public class Order {

    private int id;

    private List<Item> items;

    public Order(int id, List<Item> itens) {
        this.id = id;
        this.items = itens;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", items=" + items +
                '}';
    }
}
