package ru.skillbox.orderservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.orderservice.domain.dto.OrderDto;
import ru.skillbox.orderservice.exception.NotFoundProductException;
import ru.skillbox.orderservice.service.OrderService;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public ResponseEntity<?> addOrder(@RequestBody OrderDto input) throws NotFoundProductException {
        return ResponseEntity.ok(orderService.addOrder(input));
    }
}
