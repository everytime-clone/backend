package com.study.everytime.global.exception;

public record DefaultErrorResponse(
        String code,
        String message
) {
}
