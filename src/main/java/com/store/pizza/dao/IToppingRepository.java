package com.store.pizza.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.pizza.model.Topping;

@Repository
public interface IToppingRepository extends JpaRepository<Topping, Long>{

	public Optional<Topping> findByName(String name);
	
	public void removeByName(String name); 
}
