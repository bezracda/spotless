package com.example.demo.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductGlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<IncorrectProductData> handleException(NoSuchProductException exception) {
        IncorrectProductData data = new IncorrectProductData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectProductData> handleException(Exception exception) {
        IncorrectProductData data = new IncorrectProductData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
