package com.example.forsparkers.error;

import com.example.forsparkers.error.exception.BadRequestException;
import com.example.forsparkers.error.exception.NotFoundException;
import com.example.forsparkers.error.model.GeneralError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ResponseEntityExceptionHandler {

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

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<GeneralError> handleValidationException(ConstraintViolationException exception) {
        StringBuilder strBuilder = new StringBuilder();
        for (ConstraintViolation<?> s : exception.getConstraintViolations()) {
            if (StringUtils.hasLength(strBuilder)) {
                strBuilder.append(", ");
                strBuilder.append(s.getMessageTemplate());
                continue;
            }
            strBuilder.append(s.getMessageTemplate());
        }
        GeneralError error = new GeneralError(HttpStatus.BAD_REQUEST.value(), strBuilder.toString());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<GeneralError> handleMethodArgumentException(MethodArgumentTypeMismatchException exception) {
        String detailsMessage = "The type of '" + exception.getName() + "' must be " + exception.getRequiredType().getName();
        GeneralError error = new GeneralError(HttpStatus.BAD_REQUEST.value(), detailsMessage);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralError> handleAllExceptions(Exception exception) {
        GeneralError error = new GeneralError(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
