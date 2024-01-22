package com.restaurant.orders.services;

import com.restaurant.orders.model.Order;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${cubas.rabbitmq.exchange}")
    private String exchange;

    @Value("${cubas.rabbitmq.routingkey}")
    private String routingkey;

    public void send(Order Order) {
        rabbitTemplate.convertAndSend(exchange, routingkey, Order);
        System.out.println("Send msg = " + Order);
    }
}