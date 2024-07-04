package com.restaurant.orders.controllers;

import com.restaurant.orders.model.Item;
import com.restaurant.orders.services.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class Controllers {

    @Autowired
    private Orders orders;

    @GetMapping("/menu")
    public List<Item> menu(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new ArrayList<>(orders.getItensMenu().values());
    }

    @PostMapping("/order")
    public int order(@RequestBody List<Integer> ordersCustomer) {
        return orders.sendOrders(ordersCustomer);
    }
}
