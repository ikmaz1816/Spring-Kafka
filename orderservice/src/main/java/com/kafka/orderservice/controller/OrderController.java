package com.kafka.orderservice.controller;

import com.kafka.orderservice.DTO.OrderResponse;
import com.kafka.orderservice.exception.OrderException;
import com.kafka.orderservice.model.Order;
import com.kafka.orderservice.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;

    }

    @PostMapping(value="/save")
    public ResponseEntity<OrderResponse> saveOrder(@RequestBody Order order) {
        OrderResponse orderResponse = orderService.addOrder(order);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponse);
    }
}
