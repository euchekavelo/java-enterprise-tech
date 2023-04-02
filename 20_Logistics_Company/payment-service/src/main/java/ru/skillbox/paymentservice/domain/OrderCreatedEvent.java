package ru.skillbox.paymentservice.domain;

import lombok.Data;

@Data
public class OrderCreatedEvent implements Event{

    private Integer userId;

    private Integer orderId;

    private double price;

    @Override
    public String getEvent() {
        return null;
    }
}
