package com.biraj.inventory.service;

import java.util.List;

import com.biraj.inventory.bean.AccessToken;
import com.biraj.inventory.bean.IngredientRequest;
import com.biraj.inventory.entity.Ingredient;
/**
 * @author birajmishra
 */
public interface IngredientService {

	public Ingredient getById(int id, AccessToken accessToken);
	public void saveIngredient(Ingredient entity);
	public List<Ingredient> getAllIngridents(AccessToken accessToken);
	public void updateById(IngredientRequest ingredient, int id, AccessToken accessToken);
	public Ingredient save(IngredientRequest ingredient, AccessToken accessToken);
	public void updateAvalableQuantity(int qty,int id);
	public void delete(int id, AccessToken accessToken);
	
}
