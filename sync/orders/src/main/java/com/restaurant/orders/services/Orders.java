package com.restaurant.orders.services;

import com.restaurant.orders.model.Item;
import com.restaurant.orders.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.client.RestTemplate;

@Service
public class Orders {

    private final Map<Integer, Item> itens = new HashMap<>();

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public Orders(){
        itens.put(1, new Item(1, "lasanha", 30.0));
        itens.put(2, new Item(2, "churrasco", 60.0));
        itens.put(3, new Item(3, "peixe", 80.0));
    }

    public Map<Integer, Item> getItensMenu(){
        return itens;
    }

    private Order makeOrder(List<Integer> ordersCustomer){
        int id = getRandomNumber(0, 1000);
        List<Item> itemsOrder = new ArrayList<>();
        for(Integer idOrder: ordersCustomer){
            if(itens.containsKey(idOrder)){
                itemsOrder.add(itens.get(idOrder));
            }
        }
        return new Order(id, itemsOrder);
    }

    public int sendOrders(List<Integer> ordersCustomer){
        Order order = makeOrder(ordersCustomer);
        RestTemplate restTemplate = new RestTemplate();
        String makeOrderUrl = "http://localhost:8090/makeOrder";
        restTemplate.postForEntity(makeOrderUrl, makeOrder(ordersCustomer), Order.class);
        return order.getId();
    }
}
