package ru.skillbox.orderservice.domain.dto;

import lombok.Data;

@Data
public class OrderDto {

    private Integer userId;
    private Integer productId;
}
