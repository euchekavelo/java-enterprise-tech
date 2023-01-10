package ru.skillbox.orderservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skillbox.orderservice.controller.OrderNotFoundException;
import ru.skillbox.orderservice.domain.*;
import ru.skillbox.orderservice.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;
    private final KafkaService kafkaService;

    public OrderServiceImpl(OrderRepository orderRepository, KafkaService kafkaService) {
        this.orderRepository = orderRepository;
        this.kafkaService = kafkaService;
    }

    @Override
    public Optional<Order> addOrder(OrderDto orderDto) {

        LocalDateTime dateTime = LocalDateTime.now();
        Order newOrder = new Order(
                orderDto.getDepartureAddress(),
                orderDto.getDestinationAddress(),
                orderDto.getDescription(),
                orderDto.getCost(),
                dateTime,
                dateTime,
                OrderStatus.await
        );
        Order order = orderRepository.save(newOrder);
        kafkaService.produce(OrderKafkaDto.toKafkaDto(order));
        return Optional.of(order);
    }

    @Override
    public Boolean updateOrderStatus(Long id, StatusDto statusDto) {
        try {
            Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
            order.setStatus(statusDto.getStatus());
            Order resultOrder = orderRepository.save(order);
            kafkaService.produce(OrderKafkaDto.toKafkaDto(resultOrder));
            return true;
        } catch (RuntimeException e) {
            logger.error(String.format("Update status failed: %s", e.getMessage()));
            return false;
        }
    }

}
