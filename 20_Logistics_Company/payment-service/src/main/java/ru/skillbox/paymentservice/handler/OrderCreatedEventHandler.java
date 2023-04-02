package ru.skillbox.paymentservice.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.paymentservice.domain.OrderCreatedEvent;
import ru.skillbox.paymentservice.domain.PaymentEvent;
import ru.skillbox.paymentservice.domain.PaymentStatus;
import ru.skillbox.paymentservice.service.UserService;

@Component
public class OrderCreatedEventHandler implements EventHandler<OrderCreatedEvent, PaymentEvent> {

    private final UserService userService;

    @Autowired
    public OrderCreatedEventHandler(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    @Override
    public PaymentEvent handleEvent(OrderCreatedEvent event) {
        Integer userId = event.getUserId();
        double orderPrice = event.getPrice();

        PaymentEvent paymentEvent = new PaymentEvent();
        paymentEvent.setOrderId(event.getOrderId());
        paymentEvent.setPrice(orderPrice);
        paymentEvent.setStatus(PaymentStatus.DECLINED);

        boolean userBalanceChanged = userService.userBalanceChanged(orderPrice, userId);
        if (userBalanceChanged) {
            paymentEvent.setStatus(PaymentStatus.APPROVED);
        }

        return paymentEvent;
    }
}
