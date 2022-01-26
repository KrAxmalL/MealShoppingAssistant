package ua.edu.ukma.interpreters.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ua.edu.ukma.interpreters.entities.Ingredient;
import ua.edu.ukma.interpreters.entities.IngredientId;
import ua.edu.ukma.interpreters.repositories.IngredientRepository;
import ua.edu.ukma.interpreters.repositories.ProductRepository;
import ua.edu.ukma.interpreters.serializers.IngredientSerializer;
import ua.edu.ukma.interpreters.services.IngredientService;

@RestController
public class IngredientController {

	private IngredientService ingredientService;
	
	@Autowired
	public IngredientController(IngredientService ingredientService) {
		this.ingredientService = ingredientService;
	}

	@GetMapping(path="/ingredients")
	public Iterable<Ingredient> getIngredients() {
		return ingredientService.getAll();
	}

	@GetMapping(path="/ingredients/{dishId}/{productId}")
	public Ingredient getIngredientsOfDish(@PathVariable("dishId") int dishId, 
			                               @PathVariable("productId") int productId) {
		return ingredientService.getIngredient(new IngredientId(dishId, productId));
	}
	
	@PostMapping(path="/ingredients/add/")
	public Ingredient addIngredient(Ingredient ingredient) {
		return ingredientService.addIngredient(ingredient);
	}
	
	@PostMapping(path="/ingredients/update/")
	public Ingredient updateIngredient(Ingredient ingredient) {
		return ingredientService.updateIngredient(ingredient);
	}
	
	@PostMapping(path="/ingredients/delete/") 
	public void deleteIngredient(IngredientId id) {
		ingredientService.deleteIngredient(id);
	}
}
