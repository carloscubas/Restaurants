package com.restaurant.bff.services;

import com.restaurant.bff.model.Item;
import com.restaurant.bff.model.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BffServices {

    public List<Item> getMenu(){
        RestTemplate restTemplate = new RestTemplate();
        Map<Integer, Item> menu =  restTemplate.getForObject("http://localhost:8095/menu", Map.class);
        return new ArrayList<>(menu.values());
    }

    public List<Order> listOrders(){
        RestTemplate restTemplate = new RestTemplate();
        Map<Integer, Order> orders =  restTemplate.getForObject("http://localhost:8090/orders", Map.class);
        return new ArrayList<>(orders.values());
    }

    public Integer makeOrder(Order order){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://localhost:8095/order", order.getItems(), Integer.class);
    }
}
