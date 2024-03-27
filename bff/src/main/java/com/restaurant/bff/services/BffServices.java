package com.restaurant.bff.services;

import com.restaurant.bff.model.Item;
import org.springframework.http.ResponseEntity;
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
}
