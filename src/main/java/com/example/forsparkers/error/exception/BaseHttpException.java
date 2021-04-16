package com.example.forsparkers.error.exception;

import org.springframework.http.HttpStatus;

public abstract class BaseHttpException extends RuntimeException{

    public BaseHttpException(String message) {
        super(message);
    }

    public abstract HttpStatus getHttpStatus();

    public int getCode() {
        return getHttpStatus().value();
    }

}
