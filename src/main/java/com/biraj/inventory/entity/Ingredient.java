package com.biraj.inventory.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * 
 * @author birajmishra
 *Ingredient Entity
 */
@Entity
public class Ingredient{
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private int availQty;
	private int outletId;
	
	@ManyToMany(mappedBy="ingredents")
	private List<Product> products= new ArrayList<Product>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAvailQty() {
		return availQty;
	}

	public void setAvailQty(int availQty) {
		this.availQty = availQty;
	}

	public int getOutletId() {
		return outletId;
	}

	public void setOutletId(int outletId) {
		this.outletId = outletId;
	}

	@Override
	public String toString() {
		return "Ingredient [id=" + id + ", name=" + name + ", availQty=" + availQty + ", outletId=" + outletId
				+ ", products=" + products + "]";
	}



}
