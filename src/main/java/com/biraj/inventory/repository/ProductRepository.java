package com.biraj.inventory.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.biraj.inventory.entity.Product;
/**
 * @author birajmishra
 */
public interface ProductRepository extends CrudRepository<Product, Integer> {

	public Product findByIdInAndCategoryId(int pid, int cid);

	public List<Product> findByCategoryId(int cid);

	public Product findByIdInAndCategoryIdInAndOutletId(int pid, int cid, int outletId);

	public List<Product> findByCategoryIdInAndOutletId(int cid, int outletId);


}