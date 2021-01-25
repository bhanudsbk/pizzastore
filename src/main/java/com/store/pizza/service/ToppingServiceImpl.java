package com.store.pizza.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.store.pizza.component.ResponseBuilder;
import com.store.pizza.component.ToppingModelMapper;
import com.store.pizza.dao.IToppingRepository;
import com.store.pizza.dto.ToppingDTO;
import com.store.pizza.exception.ToppingNotFoundException;
import com.store.pizza.model.Topping;

@Service
public class ToppingServiceImpl implements IToppingService {

	private static final Logger logger = LoggerFactory.getLogger(ToppingServiceImpl.class);
	@Autowired
	IToppingRepository toppingDAO;
	@Autowired
	ToppingModelMapper modelMapper;
	@Autowired
	ResponseBuilder responseBuilder;

	@Override
	public ResponseEntity<Object> getToppings() {
		try {
			//retreive all toppings in the table
			List<ToppingDTO> result = toppingDAO.findAll().stream().map(this::convertToDTO)
					.collect(Collectors.toList());
			return responseBuilder.buildResponseEntity(result, "Success", HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Topping By Name retrieval failed: " + e.getMessage());
			return responseBuilder.buildResponseEntity(new ToppingDTO(), e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Object> getToppingByName(String name) {
		try {
			//retrieve a topping by name and if topping is not present then throw a custom exception
			Optional<Topping> result = toppingDAO.findByName(name);
			if (!result.isPresent()) {
				throw new ToppingNotFoundException(name);
			}
			return responseBuilder.buildResponseEntity(result.map(this::convertToDTO).get(), "Success", HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Topping By Name retrieval failed: " + e.getMessage());
			return responseBuilder.buildResponseEntity(new ToppingDTO(), e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Object> addToppings(List<Topping> toppings) {
		try {
			//add a topping and return the topping that was added
			List<ToppingDTO> result = toppingDAO.saveAll(toppings).stream().map(this::convertToDTO)
					.collect(Collectors.toList());
			return responseBuilder.buildResponseEntity(result, "Success", HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Topping addition failed: " + e.getMessage());
			return responseBuilder.buildResponseEntity(toppings, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@Transactional
	public ResponseEntity<Object> removeToppings(List<Topping> toppings) {
		try {
			//delete a topping, can do more here to check if the topping exists first and then delete
			toppings.forEach(topping -> {
				toppingDAO.removeByName(topping.getName());
			});
			return responseBuilder.buildResponseEntity("Delete Success", toppings.toString(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Topping deletion failed: " + e.getMessage());
			return responseBuilder.buildResponseEntity("Delete Failed", e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private ToppingDTO convertToDTO(Topping topping) {
		ToppingDTO toppingDto = modelMapper.map(topping, ToppingDTO.class);
		return toppingDto;
	}
}
