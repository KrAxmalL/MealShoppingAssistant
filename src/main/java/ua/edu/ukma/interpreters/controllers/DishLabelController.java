package ua.edu.ukma.interpreters.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ua.edu.ukma.interpreters.entities.Dish;
import ua.edu.ukma.interpreters.entities.DishLabel;
import ua.edu.ukma.interpreters.entities.ProductLabel;
import ua.edu.ukma.interpreters.repositories.DishLabelRepository;
import ua.edu.ukma.interpreters.repositories.DishRepository;
import ua.edu.ukma.interpreters.services.DishLabelService;

@RestController
public class DishLabelController {

	private DishLabelService dishLabelService;
	
	@Autowired
	public DishLabelController(DishLabelService dishLabelService) {
		this.dishLabelService = dishLabelService;
	}

	@GetMapping(path="/labels/dishes")
	public Iterable<DishLabel> getCategories() {
		return dishLabelService.getAll();
	}
	
	@GetMapping(path="/labels/dishes/{id}")
	public DishLabel getDishLabel(@PathVariable("id") int id) {
		return dishLabelService.getDishLabel(id);
	}
	
	@PostMapping(path="/labels/dishes/add/")
	public DishLabel addDishLabel(@RequestBody Map<String,Object> body) {
		DishLabel label = new DishLabel(body.get("name").toString());
		return dishLabelService.addDishLabel(label);
	}
	
	@PostMapping(path="/labels/dishes/update/")
	public DishLabel updateDishLabel(@RequestBody Map<String,Object> body) {
		int labelId = (Integer)body.get("id");
		DishLabel label = dishLabelService.getDishLabel(labelId);
		label.setName(body.get("name").toString());
		return dishLabelService.addDishLabel(label);
	}
	
	@PostMapping(path="/labels/dishes/delete/") 
	public void deleteDishLabel(@RequestBody Map<String,Object> body) {
		int labelId = (Integer)body.get("id");
		dishLabelService.deleteDishLabel(labelId);
	}
}
