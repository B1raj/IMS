package com.biraj.inventory.bean;

import java.util.ArrayList;
import java.util.List;

public class ProductInfo {

	private String name;
	private String description;
	private double price;
	//private int availQty;
	List ingredents = new ArrayList();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

//	public int getAvailQty() {
//		return availQty;
//	}
//
//	public void setAvailQty(int availQty) {
//		this.availQty = availQty;
//	}

	public List getIngredents() {
		return ingredents;
	}

	public void setIngredients(List ingredents) {
		this.ingredents = ingredents;
	}

	@Override
	public String toString() {
		return "ProductInfo [name=" + name + ", description=" + description + ", price=" + price +  "]";
	}

}
