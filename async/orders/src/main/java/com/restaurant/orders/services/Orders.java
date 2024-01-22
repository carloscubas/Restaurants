package com.restaurant.orders.services;

import com.restaurant.orders.model.Item;
import com.restaurant.orders.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Orders {

    @Autowired
    RabbitMQSender rabbitMQSender;

    private final Map<Integer, Item> itens = new HashMap<>();

    public Orders(){
        itens.put(1, new Item(1, "lasanha", 30.0));
        itens.put(2, new Item(2, "churrasco", 60.0));
        itens.put(3, new Item(3, "peixe", 80.0));
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public Map<Integer, Item> getItensMenu(){
        return itens;
    }

    public int sendOrders(List<Integer> ordersCustomer){
        int id = getRandomNumber(0, 1000);

        List<Item> itemsOrder = new ArrayList<>();
        for(Integer idOrder: ordersCustomer){
            itemsOrder.add(itens.get(idOrder));
        }

        rabbitMQSender.send(new Order(id, itemsOrder));
        return id;
    }
}
