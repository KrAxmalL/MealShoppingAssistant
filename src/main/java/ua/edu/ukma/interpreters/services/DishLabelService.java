package ua.edu.ukma.interpreters.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.edu.ukma.interpreters.entities.DishLabel;
import ua.edu.ukma.interpreters.repositories.DishLabelRepository;

@Service
public class DishLabelService {

	private DishLabelRepository dishLabelRepository;
	
	@Autowired
	public DishLabelService(DishLabelRepository dishLabelRepository) {
		this.dishLabelRepository = dishLabelRepository;
	}
	
	@Transactional
	public boolean dishLabelExists(Integer id) {
		return dishLabelRepository.existsById(id);
	}

	@Transactional
	public Iterable<DishLabel> getAll() {
		return dishLabelRepository.findAll();
	}
	
	@Transactional
	public DishLabel getDishLabel(Integer id) {
		Optional<DishLabel> label = dishLabelRepository.findById(id);
		if(label.isPresent()) {
			return label.get();
		}
		
		throw new EntityNotFoundException("Dish label " + id + " doesn't exist!");
	}
	
	@Transactional
	public DishLabel addDishLabel(DishLabel dishLabel) {
		return dishLabelRepository.save(dishLabel);
	}
	
	@Transactional
	public DishLabel updateProducLabel(DishLabel dishLabel) {
		dishLabelRepository.deleteById(dishLabel.getId());
		return dishLabelRepository.save(dishLabel);
	}
	
	@Transactional
	public void deleteDishLabel(Integer id) {
		dishLabelRepository.deleteById(id);
	}
}
