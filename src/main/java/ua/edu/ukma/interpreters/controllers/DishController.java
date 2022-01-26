package ua.edu.ukma.interpreters.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ua.edu.ukma.interpreters.entities.Dish;
import ua.edu.ukma.interpreters.entities.DishLabel;
import ua.edu.ukma.interpreters.entities.Ingredient;
import ua.edu.ukma.interpreters.entities.Menu;
import ua.edu.ukma.interpreters.entities.Product;
import ua.edu.ukma.interpreters.entities.PurchaseOrder;
import ua.edu.ukma.interpreters.repositories.DishLabelRepository;
import ua.edu.ukma.interpreters.repositories.DishRepository;
import ua.edu.ukma.interpreters.repositories.IngredientRepository;
import ua.edu.ukma.interpreters.repositories.ProductRepository;
import ua.edu.ukma.interpreters.services.DishLabelService;
import ua.edu.ukma.interpreters.services.DishService;

@RestController
public class DishController {

	private DishService dishService;
	private DishLabelService dishLabelService;
	
	@Autowired
	public DishController(DishService dishService, DishLabelService dishLabelService) {
		this.dishService = dishService;
		this.dishLabelService = dishLabelService;
	}

	@GetMapping(path="/dishes")
	public Iterable<Dish> getDishes() {
		return dishService.getAll();
	}
			
	@GetMapping(path="/dishes/{id}")
	public Dish getDish(@PathVariable("id") int id) {
		return dishService.getDish(id);
	}
		
	@GetMapping(path="/dish/ingredients/{id}")
	public Iterable<Ingredient> getDishIng(@PathVariable("id") int id) {
		return dishService.getDishIngredients(id);
	}
		
	@PostMapping(path="/dishes/add/")
	public Dish addDish(@RequestBody Map<String,Object> body) {
		Dish dish = dishService.parseDish(body, false);
		return dishService.addDish(dish);
	}
		
	@PostMapping(path="/dishes/update/")
	public Dish updateDish(@RequestBody Map<String,Object> body) {
		Dish dish = dishService.parseDish(body, true);
		return dishService.updateDish(dish);
	}
		
	@PostMapping(path="/dishes/delete/") 
	public void deleteDish(@RequestBody Map<String,Object> body) {
		int id = (Integer)body.get("id");
		dishService.deleteDish(id);
	}
		
	@PostMapping(path="/list/")
	public PurchaseOrder getFinalProductList(@RequestBody Map<String,Object> body) {
		return new PurchaseOrder(dishService.getMenu(body));
	}

}
