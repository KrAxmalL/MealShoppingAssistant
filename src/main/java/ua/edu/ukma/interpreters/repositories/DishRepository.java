package ua.edu.ukma.interpreters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ua.edu.ukma.interpreters.entities.Dish;

@Repository
public interface DishRepository extends CrudRepository<Dish, Integer>{

}
