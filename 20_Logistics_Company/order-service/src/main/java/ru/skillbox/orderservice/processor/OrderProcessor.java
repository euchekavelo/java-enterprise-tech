package ru.skillbox.orderservice.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;
import ru.skillbox.orderservice.domain.Order;
import ru.skillbox.orderservice.domain.OrderCreatedEvent;

@Component
public class OrderProcessor {

    private final Sinks.Many<OrderCreatedEvent> sink;

    @Autowired
    public OrderProcessor(Sinks.Many<OrderCreatedEvent> sink) {
        this.sink = sink;
    }

    public void process(Order order) {
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
        orderCreatedEvent.setOrderId(order.getId());
        orderCreatedEvent.setUserId(order.getUserId());
        orderCreatedEvent.setPrice(order.getPrice());

        sink.emitNext(orderCreatedEvent, Sinks.EmitFailureHandler.FAIL_FAST);
    }
}
