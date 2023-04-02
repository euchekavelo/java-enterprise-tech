package ru.skillbox.orderservice.exception;

public class NotFoundProductException extends Exception {

    public NotFoundProductException(String message) {
        super(message);
    }
}
