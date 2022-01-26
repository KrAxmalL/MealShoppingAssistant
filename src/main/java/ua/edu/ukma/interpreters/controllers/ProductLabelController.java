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

import ua.edu.ukma.interpreters.entities.Product;
import ua.edu.ukma.interpreters.entities.ProductLabel;
import ua.edu.ukma.interpreters.repositories.ProductLabelRepository;
import ua.edu.ukma.interpreters.repositories.ProductRepository;
import ua.edu.ukma.interpreters.services.ProductLabelService;

@RestController
public class ProductLabelController {

	private ProductLabelService productLabelService;
	
	@Autowired
	public ProductLabelController(ProductLabelService productLabelService) {
		this.productLabelService = productLabelService;
	}
	
	@GetMapping(path="/labels/products")
	@ResponseBody
	public Iterable<ProductLabel> getCategories() {
		return productLabelService.getAll();
	}
	
	@GetMapping(path="/labels/products/{id}")
	@ResponseBody
	public ProductLabel getProductCategory(@PathVariable("id") int id) {
		return productLabelService.getProductLabel(id);
	}
	
	@PostMapping(path="/labels/products/add/")
	@ResponseBody
	public ProductLabel addProductCategory(@RequestBody Map<String,Object> body) {
		ProductLabel label = new ProductLabel(body.get("name").toString());
		return productLabelService.addProductLabel(label);
	}
	
	@PostMapping(path="/labels/products/update/")
	@ResponseBody
	public ProductLabel updateProductLabel(@RequestBody Map<String,Object> body) {
		int labelId = (Integer)body.get("id");
		ProductLabel label = productLabelService.getProductLabel(labelId);
		label.setName(body.get("name").toString());
		return productLabelService.addProductLabel(label);

	}
	
	@PostMapping(path="/labels/products/delete/") 
	@ResponseBody
	public void deleteProductCategory(@RequestBody Map<String,Object> body) {
		int labelId = (Integer)body.get("id");
		productLabelService.deleteProductLabel(labelId);
	}
}
