package com.kafka.orderservice.service;

import com.kafka.orderservice.DTO.OrderResponse;
import com.kafka.orderservice.DTO.ShippingDTO;
import com.kafka.orderservice.model.Order;
import com.kafka.orderservice.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final ModelMapper modelMapper;

    private final KafkaTemplate<String, ShippingDTO> kafkaTemplate;

    @Value("${topic.name}")
    private String topicName;

    public OrderService(OrderRepository orderRepository, ModelMapper modelMapper,KafkaTemplate<String,ShippingDTO> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    public OrderResponse addOrder(Order order)
    {
        order.setStatus("InpProgess");
        Order orderDetails = orderRepository.save(order);
        OrderResponse orderResponse = modelMapper.map(orderDetails,OrderResponse.class);
        ShippingDTO shippingDTO = modelMapper.map(orderDetails,ShippingDTO.class);
        Message<ShippingDTO> message = MessageBuilder.withPayload(shippingDTO)
                .setHeader(KafkaHeaders.TOPIC,topicName)
                .build();
        kafkaTemplate.send(message);
        return orderResponse;
    }
}
