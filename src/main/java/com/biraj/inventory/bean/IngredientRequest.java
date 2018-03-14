package com.biraj.inventory.bean;

public class IngredientRequest {
	
	private int availQty;
	private String name;

	public int getAvailQty() {
		return availQty;
	}

	public void setAvailQty(int availQty) {
		this.availQty = availQty;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "IngredientRequest [availQty=" + availQty + ", name=" + name + "]";
	}


	

}
