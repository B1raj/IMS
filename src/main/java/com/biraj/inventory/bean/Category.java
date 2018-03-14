package com.biraj.inventory.bean;
/**
 * 
 * @author birajmishra
 * 
 */
public class Category {

	private String name;
	private String description;
	private char enabled;
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
	public char getEnabled() {
		return enabled;
	}
	public void setEnabled(char enabled) {
		this.enabled = enabled;
	}
	@Override
	public String toString() {
		return "Category [name=" + name + ", description=" + description + ", enabled=" + enabled + "]";
	}
}
