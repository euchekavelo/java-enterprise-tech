package ru.skillbox.orderservice.domain.dto;

import lombok.Data;

@Data
public class ErrorResponse {

    private String error;
    private boolean result;
}
