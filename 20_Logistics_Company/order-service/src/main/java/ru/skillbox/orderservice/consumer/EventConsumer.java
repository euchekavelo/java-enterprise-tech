package ru.skillbox.orderservice.consumer;

import ru.skillbox.orderservice.domain.Event;

public interface EventConsumer<T extends Event> {

    void consumeEvent(T event);
}
