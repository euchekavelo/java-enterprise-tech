package ru.skillbox.orderservice.service;


import ru.skillbox.orderservice.domain.Event;

public interface EventHandler<T extends Event, R extends Event> {

    R handleEvent(T event);
}
