package com.biraj.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.biraj.inventory.entity.Ingredient;
/**
 * @author birajmishra
 */
public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {

@Modifying
@Query("update Ingredient i set i.availQty = ?1 where i.id = ?2")
public void updateAvalableQuantity(int qty, int id);

public List<Ingredient> findByOutletId(int outletId);

public Ingredient findByIdInAndOutletId(int id, int outletId);
}