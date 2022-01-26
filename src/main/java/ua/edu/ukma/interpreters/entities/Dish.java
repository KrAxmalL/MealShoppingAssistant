package ua.edu.ukma.interpreters.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ua.edu.ukma.interpreters.serializers.DishSerializer;
import ua.edu.ukma.interpreters.serializers.IngredientSerializer;

@Entity
@Table(name="dish")
@JsonSerialize(using = DishSerializer.class)
public class Dish {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
    private int id;

	@Column(name="dish_name")
    private String title;
	
	@Column(name="dish_description")
    private String description;
	
	@Column(name="image_sourse")
	private String src;
    
	@Column(name="portions")
    private int portions;
	
	@Column(name="is_public")
    private boolean isPublic;
    
	@ManyToOne
	@JoinColumn(name="dish_category_id", referencedColumnName="id")
	private DishLabel label;

    @OneToMany(mappedBy="dish")
    private List<Ingredient> products;
    
    public Dish() {
    	products = new ArrayList<>();
    }
    
	public Dish(String title, String description, String src, int portions) {
		this();
		this.title = title;
		this.description = description;
		this.src = src;
		this.portions = portions;
		this.isPublic = true;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPortions() {
		return portions;
	}

	public void setPortions(int portions) {
		this.portions = portions;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public DishLabel getLabel() {
		return label;
	}

	public void setLabel(DishLabel label) {
		this.label = label;
	}
	

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public List<Ingredient> getProducts() {
		return products;
	}

	public void setProducts(List<Ingredient> products) {
		this.products = products;
	}

	// change the dish recipe
    public boolean addProduct(Product product, double quantity) {
        if (product != null) {
        	Ingredient ing = new Ingredient(this, product, quantity);
            if (!products.contains(ing)) {
                products.add(ing);
                return true;
            }
        }
        return false;
    }

    public boolean removeProduct(Product product) {
        if (product != null) {
            for (Ingredient i : products) {
                if (i.getProduct().getId() == product.getId()) {
                    products.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean removeProduct(int productID) {
        for (Ingredient i : products) {
            if (i.getProduct().getId() == productID) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean changeQuantityOfProduct(Product product, int quantity) {
        if (product == null) {
            return false;
        }
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProduct().getId() == product.getId()) {
                products.get(i).setAmount(quantity);
                return true;
            }
        }
        return false;
    }

    public boolean changeQuantityOfProduct(int productID, int quantity) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProduct().getId() == productID) {
                products.get(i).setAmount(quantity);
                return true;
            }
        }
        return false;
    }
    
    
	 @Override
	public String toString() {
		return "Dish [id=" + id + ", title=" + title + ", description=" + description + ", portions=" + portions
				+ ", isPublic=" + isPublic + ", label=" + label + ", products=" + products + "]";
	}

	@Override
	    public boolean equals(Object o) {
			if(o == null) {
				return false;
			}
			
	        if (this == o) {
	            return true;
	        }
	        if (!(o instanceof Product)) {
	            return false;
	        }
	        
	        Dish other = (Dish) o;
	        return (this.getId() == other.getId());
	    }
}


