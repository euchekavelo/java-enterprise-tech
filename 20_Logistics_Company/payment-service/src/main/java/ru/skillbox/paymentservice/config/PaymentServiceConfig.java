package ru.skillbox.paymentservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.skillbox.paymentservice.domain.OrderCreatedEvent;
import ru.skillbox.paymentservice.domain.PaymentEvent;
import ru.skillbox.paymentservice.domain.TransactionEvent;
import ru.skillbox.paymentservice.handler.EventHandler;

import java.util.function.Function;

@Configuration
public class PaymentServiceConfig {

    private final EventHandler<PaymentEvent, TransactionEvent> paymentEventHandler;
    private final EventHandler<OrderCreatedEvent, PaymentEvent> orderCreatedEventHandler;

    @Autowired
    public PaymentServiceConfig(EventHandler<PaymentEvent, TransactionEvent> paymentEventHandler,
            EventHandler<OrderCreatedEvent, PaymentEvent> orderCreatedEventHandler) {

        this.paymentEventHandler = paymentEventHandler;
        this.orderCreatedEventHandler = orderCreatedEventHandler;
    }

    @Bean
    public Function<OrderCreatedEvent, PaymentEvent> orderCreatedEventProcessor() {
        return orderCreatedEventHandler::handleEvent;
    }

    @Bean
    public Function<PaymentEvent, TransactionEvent> paymentEventProcessor() {
        return paymentEventHandler::handleEvent;
    }
}
