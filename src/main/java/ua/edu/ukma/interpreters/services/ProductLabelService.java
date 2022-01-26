package ua.edu.ukma.interpreters.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.edu.ukma.interpreters.entities.Product;
import ua.edu.ukma.interpreters.entities.ProductLabel;
import ua.edu.ukma.interpreters.repositories.ProductLabelRepository;
import ua.edu.ukma.interpreters.repositories.ProductRepository;

@Service
public class ProductLabelService {

	private ProductLabelRepository productLabelRepository;
	
	@Autowired
	public ProductLabelService(ProductLabelRepository productLabelRepository) {
		this.productLabelRepository = productLabelRepository;
	}
	
	@Transactional
	public boolean productLabelExists(Integer id) {
		return productLabelRepository.existsById(id);
	}

	@Transactional
	public Iterable<ProductLabel> getAll() {
		return productLabelRepository.findAll();
	}
	
	@Transactional
	public ProductLabel getProductLabel(Integer id) {
		Optional<ProductLabel> label = productLabelRepository.findById(id);
		if(label.isPresent()) {
			return label.get();
		}
		
		throw new EntityNotFoundException("Product label " + id + " doesn't exist!");
	}
	
	@Transactional
	public ProductLabel addProductLabel(ProductLabel productLabel) {
		return productLabelRepository.save(productLabel);
	}
	
	@Transactional
	public ProductLabel updateProducLabel(ProductLabel productLabel) {
		productLabelRepository.deleteById(productLabel.getId());
		return productLabelRepository.save(productLabel);
	}
	
	@Transactional
	public void deleteProductLabel(Integer id) {
		productLabelRepository.deleteById(id);
	}
}
