package com.restaurant.bff.controller;

/*
    Test Url
    http://localhost:8080/graphiql?path=/graphql
 */

import com.restaurant.bff.model.Item;
import com.restaurant.bff.model.Order;
import com.restaurant.bff.services.BffServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;


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

    /* query listOrders {
         listOrders{
            id
          items{
            id
            name
            value
          }
        }
    }*/

    @QueryMapping
    public List<Order> listOrders() {
        return bffServices.listOrders();
    }

    /* Mutation example
    mutation CreateOrder{
        createOrder(order: {id:1 items:[1,2]})
    } */

    @MutationMapping
    public Integer createOrder(@Argument Order order) {
        Integer idOrder = bffServices.makeOrder(order);
        System.out.println(order);
        return idOrder;
    }
}
