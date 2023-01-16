package ru.skillbox.orderservice.domain;

import lombok.Data;

@Data
public class OrderDto {
    private String description;
    private String departureAddress;
    private String destinationAddress;
    private Long cost;
}
