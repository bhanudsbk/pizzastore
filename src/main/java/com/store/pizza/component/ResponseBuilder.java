package com.store.pizza.component;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseBuilder {
	//build a response entity with json payload
	public ResponseEntity<Object> buildResponseEntity(Object body, String message, HttpStatus status){
		HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Message", message);
		return new ResponseEntity<Object>(body, responseHeaders, status) ;
	}
	
	//build a generic response entity  
	public ResponseEntity<Object> buildResponseEntity(String message, String details,  HttpStatus status){
		HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Message", message);
        responseHeaders.set("Description", details);
		return new ResponseEntity<>(responseHeaders, status) ;
	}
	
}
