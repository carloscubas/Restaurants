package com.restaurant.cooking.model;

import java.util.List;

public class Order {

    private int id;

    private List<Item> itens;

    public Order(int id, List<Item> itens) {
        this.id = id;
        this.itens = itens;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public int getId() {
        return id;
    }

    public List<Item> getItens() {
        return itens;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", itens=" + itens +
                '}';
    }
}
