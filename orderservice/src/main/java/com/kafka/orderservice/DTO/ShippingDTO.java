package com.kafka.orderservice.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShippingDTO {
    int orderId;
    String source;
    String destination;
}
