package com.rabbitmq.orderservice.controller;

import com.rabbitmq.orderservice.dto.Order;
import com.rabbitmq.orderservice.dto.OrderEvent;
import com.rabbitmq.orderservice.publisher.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    @Autowired
    private OrderProducer orderProducer;

    @PostMapping("/orders")
    public ResponseEntity<String> placeOrder(@RequestBody Order order){
        order.setId(UUID.randomUUID().toString());

        OrderEvent event = new OrderEvent();
        event.setStatus("PENDING");
        event.setMessage("Order is in pending!");
        event.setOrder(order);

        orderProducer.sendMessage(event);

        return ResponseEntity.ok("Order is placed and pending!");
    }
}
