package com.restaurant.orders.controllers;

import com.restaurant.orders.model.Item;
import com.restaurant.orders.services.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class Controllers {

    @Autowired
    private Orders orders;

    @GetMapping("/menu")
    public Map<Integer, Item> menu(@RequestParam(value = "name", defaultValue = "World") String name) {
        return orders.getItensMenu();
    }

    @PostMapping("/order")
    public Integer order(@RequestBody List<Integer> ordersCustomer) {
        return orders.sendOrders(ordersCustomer);
    }
}
