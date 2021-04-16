package com.example.forsparkers.error.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseHttpException{

    public NotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
