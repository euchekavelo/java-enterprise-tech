package ru.skillbox.orderservice.service;

import ru.skillbox.orderservice.domain.OrderStatus;
import ru.skillbox.orderservice.domain.dto.OrderDto;
import ru.skillbox.orderservice.domain.dto.SuccessfulResponse;
import ru.skillbox.orderservice.exception.NotFoundProductException;

public interface OrderService {

    SuccessfulResponse addOrder(OrderDto orderDto) throws NotFoundProductException;

    void updateOrderStatus(Integer orderId, OrderStatus orderStatus);
}
