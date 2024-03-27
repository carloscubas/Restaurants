package com.restaurant.bff.controller;

import com.restaurant.bff.model.Item;
import com.restaurant.bff.model.Order;
import com.restaurant.bff.services.BffServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

@Controller
public class BffController {

    @Autowired
    BffServices bffServices;

    /* Query Example
    query menu {
        menu {
            name
            id
            value
        }
    } */
    @QueryMapping
    public List<Item> menu() {
        return bffServices.getMenu();
    }

    /* Mutation example
    mutation CreateOrder{
        createOrder(order: {id:1 item:[1,2]})
    } */

    @MutationMapping
    public boolean createOrder(@Argument Order order) {
        System.out.println(order);
        return true;
    }
}
