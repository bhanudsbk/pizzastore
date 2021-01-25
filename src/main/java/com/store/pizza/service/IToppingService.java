package com.store.pizza.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.store.pizza.model.Topping;

@Service
public interface IToppingService {
	
	public ResponseEntity<Object> getToppings();
	public ResponseEntity<Object> getToppingByName(String name);
	public ResponseEntity<Object> addToppings(List<Topping> toppings);
	public ResponseEntity<Object> removeToppings(List<Topping> toppings);
}
