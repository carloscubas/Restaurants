package com.restaurant.billing.services;

import com.restaurant.billing.model.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    @RabbitListener(queues = "${cubas.rabbitmq.queue}")
    public void receiveMessage(final Order order) {
        System.out.println("order: " + order);
    }
}