package ua.edu.ukma.interpreters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ua.edu.ukma.interpreters.entities.Dish;
import ua.edu.ukma.interpreters.entities.ProductLabel;

@Repository
public interface ProductLabelRepository extends CrudRepository<ProductLabel, Integer>{

}
