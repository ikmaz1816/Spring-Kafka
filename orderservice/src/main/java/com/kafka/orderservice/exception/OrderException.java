package com.kafka.orderservice.exception;

public class OrderException extends Exception{
    public OrderException(String message) {
        super(message);
    }
}
