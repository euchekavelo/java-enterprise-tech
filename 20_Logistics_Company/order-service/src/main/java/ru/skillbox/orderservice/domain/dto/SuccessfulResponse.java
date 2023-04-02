package ru.skillbox.orderservice.domain.dto;

import lombok.Data;

@Data
public class SuccessfulResponse {

    private String message;
    private boolean result;
}
