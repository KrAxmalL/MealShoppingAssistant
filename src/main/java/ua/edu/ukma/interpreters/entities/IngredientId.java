package ua.edu.ukma.interpreters.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IngredientId implements Serializable {

	@Column(name="dish_id")
	private int dishId;
	
	@Column(name="product_id")
	private int productId;
	
	public IngredientId() {}
	
	public IngredientId(int dishId, int productId) {
		this.dishId = dishId;
		this.productId = productId;
	}

	public int getDishId() {
		return dishId;
	}

	public void setDishId(int dishId) {
		this.dishId = dishId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		
		if(this == obj) {
			return true;
		}
		
		if(!(obj instanceof IngredientId)) {
			return false;
		}
		
		IngredientId other = (IngredientId) obj;
		return ((this.getProductId() == other.getProductId()) && (this.getDishId() == other.getDishId()));
	}
	
	@Override
	public int hashCode() {
		return Integer.hashCode(dishId) + Integer.hashCode(productId);
	}
}
