package ua.edu.ukma.interpreters.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.edu.ukma.interpreters.entities.Product;
import ua.edu.ukma.interpreters.entities.ProductLabel;
import ua.edu.ukma.interpreters.repositories.ProductRepository;

@Service
public class ProductService {
	
	private ProductRepository productRepository;
	private ProductLabelService productLabelService;
	
	@Autowired
	public ProductService(ProductRepository productRepository, ProductLabelService productLabelService) {
		this.productRepository = productRepository;
		this.productLabelService = productLabelService;
	}
	
	@Transactional
	public boolean productExists(Integer id) {
		return productRepository.existsById(id);
	}

	@Transactional
	public Iterable<Product> getAll() {
		return productRepository.findAll();
	}
	
	@Transactional
	public Product getProduct(Integer id) {
		Optional<Product> product = productRepository.findById(id);
		if(product.isPresent()) {
			return product.get();
		}
		
		throw new EntityNotFoundException("Product " + id + " doesn't exist!");
	}
	
	@Transactional
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}
	
	@Transactional
	public Product updateProduct(Product product) {
		productRepository.deleteById(product.getId());
		return productRepository.save(product);
	}
	
	@Transactional
	public void deleteProduct(Integer id) {
		productRepository.deleteById(id);
	}
	
	public Product parseProduct(Map<String,Object> body, boolean hasId) {
		double price = Double.parseDouble(body.get("price").toString());
		double amount = Double.parseDouble(body.get("amount").toString());
		Product product = new Product(body.get("title").toString(), body.get("description").toString(), 
				                      body.get("src").toString(), price, body.get("measure").toString(),
				                      amount);
		
		int labelId = Integer.parseInt(body.get("labelId").toString());
        ProductLabel label = productLabelService.getProductLabel(labelId);
        product.setProductLabel(label);
        
        if(hasId) {
        	int productId = (Integer)body.get("id");
            product.setId(productId);
        }
        
        return product;
	}
	
}
