package ua.edu.ukma.interpreters.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ua.edu.ukma.interpreters.entities.Product;
import ua.edu.ukma.interpreters.entities.ProductLabel;
import ua.edu.ukma.interpreters.repositories.ProductLabelRepository;
import ua.edu.ukma.interpreters.repositories.ProductRepository;
import ua.edu.ukma.interpreters.services.ProductLabelService;
import ua.edu.ukma.interpreters.services.ProductService;

@RestController
public class ProductController {
	
	private ProductService productService;
	private ProductLabelService productLabelService;
	
	@Autowired
	public ProductController(ProductService productService, ProductLabelService productLabelService) {
		this.productService = productService;
		this.productLabelService = productLabelService;
	}
	
	@GetMapping(path="/products")
	public Iterable<Product> getProducts() {
		return productService.getAll();
	}

	@GetMapping(path="/products/{id}")
	public Product getProduct(@PathVariable("id") int id) {
		return productService.getProduct(id);
	}
	
	@PostMapping(path="/products/add/")
	public Product addProduct(@RequestBody Map<String,Object> body) {
		Product product = productService.parseProduct(body, false);
		return productService.addProduct(product);
	}
	
	@PostMapping(path="/products/update/")
	public Product updateProduct(@RequestBody Map<String,Object> body) {
		Product product = productService.parseProduct(body, true);
		return productService.updateProduct(product);
	}
	
	@PostMapping(path="/products/delete/") 
	public void deleteProduct(@RequestBody Map<String,Object> body) {
		int productId = (Integer)body.get("id");
		productService.deleteProduct(productId);
	}
}
