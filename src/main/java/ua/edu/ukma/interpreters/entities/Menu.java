package ua.edu.ukma.interpreters.entities;

import java.util.HashMap;
import java.util.Map;

public class Menu {

    // dish -> quantity of portions
    private HashMap<Dish, Integer> dishes;
    private HashMap<Product, Double> additionalProducts;
    private int defaultPortions = 1;
    
    public Menu() {
    	dishes = new HashMap<>();
        additionalProducts = new HashMap<>();
    }

    public Menu(int defaultPortions) {
    	this();
        this.defaultPortions = defaultPortions;
    }
    
    public HashMap<Product, Double> getAdditionalProducts() {
		return additionalProducts;
	}

	public void setAdditionalProducts(HashMap<Product, Double> additionalProducts) {
		this.additionalProducts = additionalProducts;
	}

	public Menu(HashMap<Dish, Integer> dishes) {
        this.dishes = dishes;
    }

    public HashMap<Dish, Integer> getDishes() {
        return dishes;
    }

    public void setDishes(HashMap<Dish, Integer> dishes) {
        this.dishes = dishes;
    }

    public int getDefaultPortions() {
        return defaultPortions;
    }

    public boolean addDish(Dish dish){
        if (dish != null) {
            dishes.put(dish, defaultPortions);
            return true;
        }
        return false;
    }

    public boolean addDish(Dish dish, int portions) {
        if (dish != null) {
            dishes.put(dish, portions);
            return true;
        }
        return false;
    }
    
    public boolean addProduct(Product product, double amount) {
        if (product != null) {
            additionalProducts.put(product, amount);
            return true;
        }
        return false;
    }

    public boolean removeDish(Dish dish) {
        if (dish != null) {
            dishes.remove(dish);
            return true;
        }
        return false;
    }

    public boolean removeDish(int dishID) {
        for (Map.Entry<Dish, Integer> entry : dishes.entrySet()) {
            Dish d = entry.getKey();
            if (d.getId() == dishID) {
                dishes.remove(d);
                return true;
            }
        }
        return false;
    }

    public boolean changeNumberOfPortion(Dish dish, int portions) {
        if (dish == null) {
            return false;
        }
        if (dishes.containsKey(dish)) {
            dishes.put(dish, portions);
        }
        return false;
    }

    public boolean changeNumberOfPortion(int dishId, int portions) {
        Dish d = null;
        for (Map.Entry<Dish, Integer> entry : dishes.entrySet()) {
            Dish key = entry.getKey();
            if (key.getId() == dishId) {
                d = key;
                break;
            }
        }
        if (d == null) {
            return false;
        } else {
            int old_portions = dishes.get(d);
            return dishes.replace(d, old_portions, portions);
        }
    }

}
