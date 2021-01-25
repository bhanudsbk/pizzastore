package com.store.pizza.exception;

public class ToppingNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ToppingNotFoundException(String name) {
        super(String.format("Topping with name %s, does not exist",name));
	}
}
