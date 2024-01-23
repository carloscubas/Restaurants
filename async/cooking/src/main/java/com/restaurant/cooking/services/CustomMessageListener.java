package com.restaurant.cooking.services;

import com.restaurant.cooking.model.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomMessageListener {

    @Autowired
    Orders orders;

    @RabbitListener(queues = "${cubas.rabbitmq.queue}")
    public void receiveMessage(final Order order) {
        orders.addOrder(order);
        System.out.println("order: " + order);
    }
}