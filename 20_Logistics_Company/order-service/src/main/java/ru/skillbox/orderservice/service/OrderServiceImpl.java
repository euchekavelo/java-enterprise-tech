package ru.skillbox.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.orderservice.domain.*;
import ru.skillbox.orderservice.domain.dto.OrderDto;
import ru.skillbox.orderservice.domain.dto.SuccessfulResponse;
import ru.skillbox.orderservice.exception.NotFoundProductException;
import ru.skillbox.orderservice.processor.OrderProcessor;
import ru.skillbox.orderservice.repository.OrderRepository;
import ru.skillbox.orderservice.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderProcessor orderProcessor;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderProcessor orderProcessor,
                            ProductRepository productRepository) {

        this.orderRepository = orderRepository;
        this.orderProcessor = orderProcessor;
        this.productRepository = productRepository;
    }

    @Override
    public SuccessfulResponse addOrder(OrderDto orderDto) throws NotFoundProductException {
        LocalDateTime dateTime = LocalDateTime.now();
        Optional<Product> optionalProduct = productRepository.findById(orderDto.getProductId());
        if (optionalProduct.isEmpty()) {
            throw new NotFoundProductException("Не найден продукт с таким идентификатором в базе данных.");
        }

        Order newOrder = new Order();
        newOrder.setProductId(orderDto.getProductId());
        newOrder.setUserId(orderDto.getUserId());
        newOrder.setStatus(OrderStatus.CREATED);
        newOrder.setPrice(optionalProduct.get().getPrice());
        newOrder.setModifiedTime(dateTime);
        newOrder.setCreationTime(dateTime);
        Order order = orderRepository.save(newOrder);
        orderProcessor.process(order);

        return getSuccessfulResponse();
    }

    @Override
    public void updateOrderStatus(Integer orderId, OrderStatus orderStatus) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setStatus(orderStatus);
            order.setModifiedTime(LocalDateTime.now());
            orderRepository.save(order);
        }
    }

    private SuccessfulResponse getSuccessfulResponse() {
        SuccessfulResponse successfulResponse = new SuccessfulResponse();
        successfulResponse.setMessage("Запрос на обработку заказа успешно запущен!");
        successfulResponse.setResult(true);

        return successfulResponse;
    }
}
