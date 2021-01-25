package com.store.pizza.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.store.pizza.model.Topping;
import com.store.pizza.service.IToppingService;

@RestController
@EnableAutoConfiguration
@RequestMapping("/toppings")
public class ToppingController {

	@Autowired 
	public IToppingService toppingService;
	//to retrieve all toppings 
	@GetMapping("/get/all")
	public ResponseEntity<Object> getToppings(){
		return toppingService.getToppings();
	}
	// to retrieve topping by name
	@GetMapping("/get/{name}")
	public ResponseEntity<Object> getToppingByName(@PathVariable String name) {
		return toppingService.getToppingByName(name);
	}

	//to add toppings accepts Collection of Objects
	@PostMapping("/add")
	public ResponseEntity<Object> addToppings(@RequestBody List<Topping> allToppings) {
		return toppingService.addToppings(allToppings);
	}
	//to remove toppings, accepts collection of Objects
	@DeleteMapping("/remove")
	public ResponseEntity<Object> removeTopping(@RequestBody List<Topping> removeToppings) {
		return toppingService.removeToppings(removeToppings);
	}
}
