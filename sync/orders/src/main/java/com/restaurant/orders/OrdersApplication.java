package com.restaurant.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class OrdersApplication {

	public static void main(String[] args) {

		SpringApplication.run(OrdersApplication.class, args);

	}

}
