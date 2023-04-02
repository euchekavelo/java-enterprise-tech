package ru.skillbox.paymentservice.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skillbox.paymentservice.domain.*;
import ru.skillbox.paymentservice.service.TransactionService;

@Component
public class PaymentEventHandler implements EventHandler<PaymentEvent, TransactionEvent> {

    private final TransactionService transactionService;

    @Autowired
    public PaymentEventHandler(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public TransactionEvent handleEvent(PaymentEvent event) {
        Transaction transaction = new Transaction();
        transaction.setOrderId(event.getOrderId());
        transaction.setPrice(event.getPrice());
        transactionService.saveTransaction(transaction);

        TransactionEvent transactionEvent = new TransactionEvent();
        transactionEvent.setOrderId(event.getOrderId());
        transactionEvent.setStatus(PaymentStatus.APPROVED.equals(event.getStatus())
                ? TransactionStatus.SUCCESSFUL
                : TransactionStatus.UNSUCCESSFUL);

        return transactionEvent;
    }
}
