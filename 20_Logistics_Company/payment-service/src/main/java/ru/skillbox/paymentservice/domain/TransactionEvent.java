package ru.skillbox.paymentservice.domain;

import lombok.Data;

@Data
public class TransactionEvent implements Event {

    Integer orderId;
    TransactionStatus status;

    @Override
    public String getEvent() {
        return "This is TransactionEvent.";
    }
}
