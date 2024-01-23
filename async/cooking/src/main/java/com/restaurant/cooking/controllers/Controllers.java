package com.restaurant.cooking.controllers;

import com.restaurant.cooking.model.Order;
import com.restaurant.cooking.services.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class Controllers {

    @Autowired
    private Orders orders;

    @GetMapping("/orders")
    public Map<Integer, Order> orders() {
        return orders.getOrders();
    }

}
