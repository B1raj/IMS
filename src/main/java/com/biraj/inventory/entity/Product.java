package com.biraj.inventory.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * 
 * @author birajmishra
 * Product Entity
 *
 */

@Entity
public class Product extends Audit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int categoryId;
	private String name;
	private String description;
	private double price;
	private int availQty;
	private int outletId;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "product_ingredient_mapper", joinColumns = {
			@JoinColumn(name = "product_id") }, inverseJoinColumns = { @JoinColumn(name = "ingredient_id") })
	List<Ingredient> ingredents = new ArrayList<Ingredient>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

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

	public int getAvailQty() {
		return availQty;
	}

	public void setAvailQty(int availQty) {
		this.availQty = availQty;
	}

	public List<Ingredient> getIngredents() {
		return ingredents;
	}

	public void setIngredents(List<Ingredient> ingredents) {
		this.ingredents = ingredents;
	}

	public int getOutletId() {
		return outletId;
	}

	public void setOutletId(int outletId) {
		this.outletId = outletId;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", categoryId=" + categoryId + ", name=" + name + ", description=" + description
				+ ", price=" + price + ", availQty=" + availQty + ", outletId=" + outletId + "]";
	}

}
