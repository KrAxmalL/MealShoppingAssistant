package ua.edu.ukma.interpreters.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.edu.ukma.interpreters.entities.Dish;
import ua.edu.ukma.interpreters.entities.DishLabel;
import ua.edu.ukma.interpreters.entities.Ingredient;
import ua.edu.ukma.interpreters.entities.Menu;
import ua.edu.ukma.interpreters.entities.Product;
import ua.edu.ukma.interpreters.repositories.DishLabelRepository;
import ua.edu.ukma.interpreters.repositories.DishRepository;
import ua.edu.ukma.interpreters.repositories.IngredientRepository;
import ua.edu.ukma.interpreters.repositories.ProductRepository;

@Service
public class DishService {

	private DishRepository dishRepository;
	private ProductService productService;
	private IngredientRepository ingredientRepository;
	private DishLabelService dishLabelService;
	
	@Autowired
	public DishService(DishRepository dishRepository, ProductService productService,
			IngredientRepository ingredientRepository, DishLabelService dishLabelService) {
		this.dishRepository = dishRepository;
		this.productService = productService;
		this.ingredientRepository = ingredientRepository;
		this.dishLabelService = dishLabelService;
	}

	@Transactional
	public boolean dishExists(Integer id) {
		return dishRepository.existsById(id);
	}

	@Transactional
	public Iterable<Dish> getAll() {
		return dishRepository.findAll();
	}
	
	@Transactional
	public Dish getDish(Integer id) {
		Optional<Dish> dish = dishRepository.findById(id);
		if(dish.isPresent()) {
			return dish.get();
		}
		
		throw new EntityNotFoundException("Dish " + id + " doesn't exist!");
	}
	
	public Iterable<Ingredient> getDishIngredients(int dishId) {
		return getDish(dishId).getProducts();
	}
	
	@Transactional
	public Dish addDish(Dish dish) {
		return dishRepository.save(dish);
	}
	
	@Transactional
	public Dish updateDish(Dish dish) {
		dishRepository.deleteById(dish.getId());
		return dishRepository.save(dish);
	}
	
	@Transactional
	public void deleteDish(Integer id) {
		dishRepository.deleteById(id);
	}
	
	public Dish parseDish(Map<String,Object> body, boolean hasId) {
		int portions = Integer.parseInt(body.get("portions").toString());
		Dish dish = new Dish(body.get("title").toString(), body.get("description").toString(), 
				             body.get("src").toString(), portions);

		int labelId = Integer.parseInt(body.get("labelId").toString());
		dish.setLabel(dishLabelService.getDishLabel(labelId));
		
		if(hasId) {
			int dishId = Integer.parseInt((body.get("id").toString()));
			dish.setId(dishId);
		}
		
		parseProducts(dish, (List<Object>)body.get("products"));
		
		return dish;
	}
	
	private void parseProducts(Dish dish, List<Object> products) {
		for(Object obj: products) {
			String toDeserialize = obj.toString();
			String[] fields = toDeserialize.split(",");
			int productId = Integer.parseInt(fields[0].replaceAll("\\{id=", "").trim());
			double amount =  Double.parseDouble(fields[1].replaceAll("amount=", "").replaceAll("\\}", "").trim());
			
			Product pr = productService.getProduct(productId);
			dish.addProduct(pr, amount);
		}
	}

	public Menu getMenu(Map<String, Object> body) {
		Menu menu = new Menu();
		
		List<Object> dishes = (List<Object>) body.get("dishes");
		List<Object> products = (List<Object>)body.get("products");
		
		for(Object dishAsString : dishes) {
			String[] dishToDeserialize = dishAsString.toString().split(",");
			int dishId = Integer.parseInt(dishToDeserialize[0].replaceAll("\\{dishId=", "").trim());
			int portions = Integer.parseInt(dishToDeserialize[1].replaceAll("portions=", "").replaceAll("\\}", "").trim());
			
			menu.addDish(getDish(dishId), portions);
		}
			
		for(Object productAsString : products) {
			String[] productToDeserialize = productAsString.toString().split(",");
			int productId = Integer.parseInt(productToDeserialize[0].replaceAll("\\{productId=", "").trim());
			double amount = Double.parseDouble(productToDeserialize[1].replaceAll("amount=", "").replaceAll("\\}", "").trim());
			
			menu.addProduct(productService.getProduct(productId), amount);
		}
		
		return menu;
	}
}
