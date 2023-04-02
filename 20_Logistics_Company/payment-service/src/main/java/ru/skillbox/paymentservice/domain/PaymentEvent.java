package ru.skillbox.paymentservice.domain;

import lombok.Data;

@Data
public class PaymentEvent implements Event {

    private Integer orderId;
    private double price;
    private PaymentStatus status;

    @Override
    public String getEvent() {
        return "This is PaymentEvent.";
    }
}
