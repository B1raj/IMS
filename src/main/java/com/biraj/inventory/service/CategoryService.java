package com.biraj.inventory.service;
/**
 * @author birajmishra
 */
import java.util.List;

import org.springframework.stereotype.Service;

import com.biraj.inventory.bean.AccessToken;
import com.biraj.inventory.bean.Category;
import com.biraj.inventory.exception.InvalidDataException;

@Service
public interface CategoryService {

	public com.biraj.inventory.entity.Category saveCategory(Category c,AccessToken accessToken) throws InvalidDataException;

	public List<com.biraj.inventory.entity.Category> getCategories(AccessToken accessToken) throws InvalidDataException;

	public com.biraj.inventory.entity.Category getCategory(int id, AccessToken accessToken) throws InvalidDataException;

	public com.biraj.inventory.entity.Category updateCategory(Category category, AccessToken accessToken, int id) throws InvalidDataException;

	public void removeCategory(int id, AccessToken accessToken) throws InvalidDataException;


}
