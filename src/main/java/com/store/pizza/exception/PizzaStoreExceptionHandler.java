package com.store.pizza.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PizzaStoreExceptionHandler {
//Custom exception handler when a specific is not found
	private static final Logger logger = LoggerFactory.getLogger(PizzaStoreExceptionHandler.class);
    @ExceptionHandler(value = { ToppingNotFoundException.class })
    public ResponseEntity<Object> handleNotFoundException(ToppingNotFoundException ex) {
    	logger.error("ToppingExceptionHandler::ToppingNotFoundException: "+ex.getMessage());
    	HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Message", "Topping Not Found");
        return new ResponseEntity<Object>(ex.getMessage(),responseHeaders,HttpStatus.NOT_FOUND);
    }
}
