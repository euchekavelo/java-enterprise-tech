package ru.skillbox.paymentservice.service;

public interface UserService {

    boolean userBalanceChanged(double orderPrice, Integer userId);
}
