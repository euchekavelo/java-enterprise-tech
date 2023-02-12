package ru.skillbox.orderservice.domain.dto;

import lombok.Data;
import ru.skillbox.orderservice.domain.OrderStatus;

@Data
public class StatusDto {
    private OrderStatus status;
}
