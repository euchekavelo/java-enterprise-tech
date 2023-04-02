package ru.skillbox.orderservice.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skillbox.orderservice.domain.OrderStatus;
import ru.skillbox.orderservice.domain.TransactionEvent;
import ru.skillbox.orderservice.domain.TransactionStatus;
import ru.skillbox.orderservice.service.OrderService;

@Component
public class TransactionEventConsumer implements EventConsumer<TransactionEvent> {

    private final OrderService orderService;

    @Autowired
    public TransactionEventConsumer(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void consumeEvent(TransactionEvent event) {
        Integer orderId = event.getOrderId();
        TransactionStatus transactionStatus = event.getStatus();
        if (transactionStatus.equals(TransactionStatus.SUCCESSFUL)) {
            orderService.updateOrderStatus(orderId, OrderStatus.COMPLETED);
        } else {
            orderService.updateOrderStatus(orderId, OrderStatus.FAILED);
        }
    }
}
