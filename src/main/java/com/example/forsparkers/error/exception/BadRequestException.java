package com.example.forsparkers.error.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseHttpException{

    public BadRequestException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
