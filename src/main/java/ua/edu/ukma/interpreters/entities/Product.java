package ua.edu.ukma.interpreters.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="product")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="product_name")
	private String title;
	
	@Column(name="product_description")
	private String description;
	
	@Column(name="image_sourse")
	private String src;
	
	@Column(name="price")
	private double price;
	
	@Column(name="expiry_days")
	private int expiryDays;
	
	@Column(name="measure")
	private String measure;
	
	@Column(name="package_quantity")
	private double amount;
	
	@ManyToOne
	@JoinColumn(name="product_label_id", referencedColumnName="id")
	private ProductLabel label;

    public Product() {}

	public Product(String title, String description, String src, double price, String measure, double amount) {
		this.title = title;
		this.description = description;
		this.src = src;
		this.price = price;
		this.expiryDays = 0;
		this.measure = measure;
		this.amount = amount;
	}
	
	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getExpiryDays() {
		return expiryDays;
	}

	public void setExpiryDays(int expiryDays) {
		this.expiryDays = expiryDays;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public ProductLabel getLabel() {
		return label;
	}

	public void setProductLabel(ProductLabel label) {
		this.label = label;
	}
	
	public int getQuantityOfPackages(double productQuantity) {
        double ratio = productQuantity / this.amount;
        return (int)Math.ceil(ratio);
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
        
        Product other = (Product) o;
        return (this.getId() == other.getId());
    }

	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", description=" + description + ", src=" + src + ", price="
				+ price + ", measure=" + measure + ", amount=" + amount + ", label=" + label + "]";
	}
}
