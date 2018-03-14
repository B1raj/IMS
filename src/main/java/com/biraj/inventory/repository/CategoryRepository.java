package com.biraj.inventory.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.biraj.inventory.entity.Category;
/**
 * @author birajmishra
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {

public List<com.biraj.inventory.entity.Category> findAllByOutletId(int outletId);

public com.biraj.inventory.entity.Category findByIdInAndOutletId(int id, int outletId);

@Modifying
@Query("update Category c set c.enabled = ?1 where c.id = ?2")
public void updateCategory(char c, int i);

}