package ua.edu.ukma.interpreters.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.bytebuddy.description.type.TypeDefinition.SuperClassIterator;
import ua.edu.ukma.interpreters.serializers.IngredientSerializer;

@Entity
@Table(name="ingredient")
@JsonSerialize(using = IngredientSerializer.class)
public class Ingredient implements Serializable{
	
	@EmbeddedId
	private IngredientId id;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="dish_id", insertable=false, updatable=false)
	@JsonBackReference
	private Dish dish;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="product_id", insertable=false, updatable=false)
	private Product product;
	
	@Column(name="quantity")
	private double amount;
	
	public Ingredient() {}
	
	public Ingredient(Dish dish, Product product, double amount) {
		this.dish = dish;
		this.product = product;
		this.amount = amount;
		
		this.id = new IngredientId(dish.getId(), product.getId());
	}

	public IngredientId getId() {
		return id;
	}

	public void setId(IngredientId id) {
		this.id = id;
	}

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Ingredient [product=" + product + ", amount=" + amount + "]";
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		
		if(this == o) {
			return true;
		}
		
		if(!(o instanceof Ingredient)) {
			return false;
		}
		
		Ingredient other = (Ingredient) o;
		return (this.getId() == other.getId());
	}
	
	@Override
	public int hashCode() {
		return id.hashCode() + super.hashCode();
	}
}
