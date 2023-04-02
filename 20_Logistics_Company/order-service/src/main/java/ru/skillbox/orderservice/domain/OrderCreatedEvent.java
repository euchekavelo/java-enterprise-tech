package ru.skillbox.orderservice.domain;

import lombok.Data;

@Data
public class OrderCreatedEvent implements Event {

    private Integer userId;

    private Integer orderId;

    private double price;

    @Override
    public String getEvent() {
        return "This is OrderCreatedEvent!";
    }
}
