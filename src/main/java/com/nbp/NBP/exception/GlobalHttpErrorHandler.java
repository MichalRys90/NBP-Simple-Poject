package com.nbp.NBP.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler {

    @ExceptionHandler(RatesWithGivenDateDoesntExist.class)
    public ResponseEntity<Object> handleRatesWithGivenDateDoesntExist(RatesWithGivenDateDoesntExist exception) {
        return new ResponseEntity<>("the exchange rate for the given date does not exist", HttpStatus.BAD_REQUEST);
    }
}
