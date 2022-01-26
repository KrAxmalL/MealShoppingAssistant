package ua.edu.ukma.interpreters.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.edu.ukma.interpreters.entities.Ingredient;
import ua.edu.ukma.interpreters.entities.IngredientId;
import ua.edu.ukma.interpreters.repositories.DishRepository;
import ua.edu.ukma.interpreters.repositories.IngredientRepository;

@Service
public class IngredientService {

	private IngredientRepository ingredientRepository;
	
	@Autowired
	public IngredientService(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}
	
	@Transactional
	public boolean ingredientExists(IngredientId id) {
		return ingredientRepository.existsById(id);
	}

	@Transactional
	public Iterable<Ingredient> getAll() {
		return ingredientRepository.findAll();
	}
	
	@Transactional
	public Ingredient getIngredient(IngredientId id) {
		Optional<Ingredient> ingredient = ingredientRepository.findById(id);
		if(ingredient.isPresent()) {
			return ingredient.get();
		}
		
		throw new EntityNotFoundException("Ingredient " + id + " doesn't exists");
	}
	
	
	@Transactional
	public Ingredient addIngredient(Ingredient ingredient) {
		return ingredientRepository.save(ingredient);
	}
	
	@Transactional
	public Ingredient updateIngredient(Ingredient ingredient) {
		ingredientRepository.deleteById(ingredient.getId());
		return ingredientRepository.save(ingredient);
	}
	
	@Transactional
	public void deleteIngredient(IngredientId id) {
		ingredientRepository.deleteById(id);
	}
}
