package ua.edu.ukma.interpreters.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ua.edu.ukma.interpreters.serializers.FinalListSerializer;

@JsonSerialize(using = FinalListSerializer.class)
public class PurchaseOrder {

    private List<FinalListProduct> finalProductList;
    private Menu menu;
    private double totalPrice;

    public PurchaseOrder(Menu menu) {
        this.menu = menu;
        this.finalProductList = new ArrayList<FinalListProduct>();
        this.totalPrice = 0;
        createProductList();
    }

    public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<FinalListProduct> getFinalProductList() {
		return finalProductList;
	}

	public void setFinalProductList(List<FinalListProduct> finalProductList) {
		this.finalProductList = finalProductList;
	}

	public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void createProductList() {
        HashMap<Product, Double> productList = new HashMap<>();
        HashMap<Product, Double> additionalProducts = menu.getAdditionalProducts();
        // fill product list
        for (Map.Entry<Dish, Integer> entry : menu.getDishes().entrySet()) {
            Dish d = entry.getKey();
            int recipe_portions = d.getPortions();
            System.out.println("recipe portions: " + recipe_portions);
            int user_portions = entry.getValue();
            System.out.println("user portions: " + user_portions);
            for (Ingredient ing : d.getProducts()) {
                Product p = ing.getProduct();
                double quantity_of_product_in_recipe = ing.getAmount();               
                double quantity_of_product_for_user = ((double)user_portions / (double)recipe_portions) * quantity_of_product_in_recipe;
                System.out.println("user portions to recipe portions: " + ((double)user_portions / (double)recipe_portions));
                
                if(!additionalProducts.isEmpty()) {
                	quantity_of_product_for_user += additionalProducts.getOrDefault(p, (double) 0);
                    additionalProducts.remove(p);               
                }
                
                if (!productList.containsKey(p)) {
                    productList.put(p, quantity_of_product_for_user);
                } else {
                    double old_quantity = productList.get(p);
                    productList.put(p, old_quantity + quantity_of_product_for_user);
                }
            }
        }
        
        
        // fill final product list
        for (Map.Entry<Product, Double> entry : productList.entrySet()) {
            Product p = entry.getKey();
            
            int packages = p.getQuantityOfPackages(entry.getValue());
            double priceToAdd = 0;
    		if(p.getMeasure().equals("л")) {
    			priceToAdd = p.getPrice() * packages;
    		}
    		else {
    			 priceToAdd = p.getPrice() * (entry.getValue() / p.getAmount());
    		}
            totalPrice += priceToAdd;
            
            FinalListProduct finalProduct = new FinalListProduct(p);
            finalProduct.setPackageQuantity(packages);
            finalProduct.setAmount(entry.getValue());
            
            finalProductList.add(finalProduct);
        }
        
        if(!additionalProducts.isEmpty()) {
        	for(Map.Entry<Product, Double> entry : additionalProducts.entrySet()) {
        		
        		Product p = entry.getKey();
        		int packages = p.getQuantityOfPackages(entry.getValue());
        		double priceToAdd = 0;
        		if(p.getMeasure().equals("л")) {
        			priceToAdd = p.getPrice() * packages;
        		}
        		else {
        			 priceToAdd = p.getPrice() * (entry.getValue() / p.getAmount());
        		}
                totalPrice += priceToAdd;
                
                FinalListProduct finalProduct = new FinalListProduct(p);
                finalProduct.setPackageQuantity(packages);
                finalProduct.setAmount(entry.getValue());
                
                finalProductList.add(finalProduct);
        	}
        }
    }
}
