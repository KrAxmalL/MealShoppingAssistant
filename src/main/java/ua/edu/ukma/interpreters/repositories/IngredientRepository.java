package ua.edu.ukma.interpreters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ua.edu.ukma.interpreters.entities.Ingredient;
import ua.edu.ukma.interpreters.entities.IngredientId;

public interface IngredientRepository extends CrudRepository<Ingredient, IngredientId>{
	
}
