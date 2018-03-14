package com.biraj.inventory.service;

import java.util.List;

import com.biraj.inventory.bean.AccessToken;
import com.biraj.inventory.bean.ProductInfo;
import com.biraj.inventory.entity.Ingredient;
import com.biraj.inventory.entity.Product;
/**
 * @author birajmishra
 */
public interface ProductService {

	Product saveProduct(ProductInfo product, int cid, AccessToken accessToken);

	Product getProduct(int cid, int pid, AccessToken accessToken); 
	
	List<Product> getProducts(int cid, AccessToken accessToken);

	Product updateProduct(ProductInfo product, AccessToken accessToken, int cid, int pid);

	void orderProduct(int qty, int cid, int pid, AccessToken accessToken);

	List<Ingredient> getAllIngredientsByProductId(int pid, int cid, AccessToken accessToken);

}
