package com.restaurant.cooking.services;

import com.restaurant.cooking.model.Order;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class Orders {

    private final Map<Integer, Order> orders = new HashMap<>();

    public void addOrder(Order order){
        orders.put(order.getId(), order);
    }

    public Map<Integer, Order> getOrders() {
        System.out.println(orders);
        return orders;
    }
}
