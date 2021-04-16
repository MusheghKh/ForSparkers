package com.example.forsparkers.error;

import com.example.forsparkers.error.exception.BadRequestException;
import com.example.forsparkers.error.exception.NotFoundException;
import com.example.forsparkers.error.model.GeneralError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ValidationException;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<GeneralError> handleBadRequest(BadRequestException exception) {
        GeneralError error = new GeneralError(exception.getCode(), exception.getMessage());
        return new ResponseEntity<>(error, exception.getHttpStatus());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<GeneralError> handleNotFound(NotFoundException exception) {
        GeneralError error = new GeneralError(exception.getCode(), exception.getMessage());
        return new ResponseEntity<>(error, exception.getHttpStatus());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<GeneralError> handleValidationException(Exception exception) {
        GeneralError error = new GeneralError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralError> handleAllExceptions(Exception exception) {
        GeneralError error = new GeneralError(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
