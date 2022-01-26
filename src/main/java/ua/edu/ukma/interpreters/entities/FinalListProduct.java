package ua.edu.ukma.interpreters.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ua.edu.ukma.interpreters.serializers.FinalListProductSerializer;

@JsonSerialize(using = FinalListProductSerializer.class)
public class FinalListProduct {

	private Product product;
	private int packageQuantity;
	private double amount;
	
	public FinalListProduct() {}
	
	public FinalListProduct(Product product) {
		this.product = product;
		this.packageQuantity = 0;
		this.amount = 0;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getPackageQuantity() {
		return packageQuantity;
	}

	public void setPackageQuantity(int packageQuantity) {
		this.packageQuantity = packageQuantity;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
