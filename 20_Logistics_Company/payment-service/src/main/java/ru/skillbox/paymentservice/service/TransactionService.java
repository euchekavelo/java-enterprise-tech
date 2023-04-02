package ru.skillbox.paymentservice.service;

import ru.skillbox.paymentservice.domain.Transaction;

public interface TransactionService {

    void saveTransaction(Transaction transaction);
}
