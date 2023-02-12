package ru.skillbox.orderservice.service;

import ru.skillbox.orderservice.domain.dto.OrderKafkaDto;

public interface KafkaService {

    void produce(OrderKafkaDto orderKafkaDto);
}
