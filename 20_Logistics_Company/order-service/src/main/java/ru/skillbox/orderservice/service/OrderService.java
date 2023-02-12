package ru.skillbox.orderservice.service;

import ru.skillbox.orderservice.domain.Order;
import ru.skillbox.orderservice.domain.dto.OrderDto;
import ru.skillbox.orderservice.domain.dto.StatusDto;

import java.util.Optional;

public interface OrderService {

    Optional<Order> addOrder(OrderDto orderDto);

    Boolean updateOrderStatus(Long id, StatusDto statusDto);
}
