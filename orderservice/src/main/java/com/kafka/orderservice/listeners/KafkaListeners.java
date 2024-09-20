package com.kafka.orderservice.listeners;

import com.kafka.orderservice.DTO.ShippingDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "order" ,groupId = "my-consumer")
    public void listen(ShippingDTO message)
    {
        System.out.println(message.toString());
    }
}
