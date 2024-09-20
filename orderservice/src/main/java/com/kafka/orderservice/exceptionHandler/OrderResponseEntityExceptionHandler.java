package com.kafka.orderservice.exceptionHandler;

import com.kafka.orderservice.exception.Error;
import com.kafka.orderservice.exception.OrderException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class OrderResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(OrderException.class)
    public ResponseEntity<Error> getException(OrderException orderNotFoundException,
                                              WebRequest webRequest)
    {
        Error error = new Error(orderNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
