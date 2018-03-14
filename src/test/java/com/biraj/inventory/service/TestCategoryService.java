package com.biraj.inventory.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.biraj.inventory.bean.AccessToken;
import com.biraj.inventory.bean.Category;
import com.biraj.inventory.exception.InvalidDataException;
import com.biraj.inventory.service.impl.AccessFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({ "in-memory" })
public class TestCategoryService {

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	AccessFactory accessFactory;
	
	public static final String  at ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2JpcmFqLmludmVudG9yeS5jb20iLCJpYXQiOjE1MTExMjM1NTAsInN1YiI6IkpvaG4iLCJhdWQiOiJodHRwczovL2JpcmFqLmludmVudG9yeS5jb20vYWNjZXNzIiwib3V0bGV0IjoxfQ.vdqwSJbwRY0GM0ypmMdqmFIYNKpXlsojbc7Clahb4fM";
	
	@Test
	public void testSaveCategory() throws InvalidDataException{
		Category cat = new Category();
		cat.setName("test");
		cat.setEnabled('Y');
		cat.setDescription("test");
		
		AccessToken accessToken = accessFactory.verifyAccessToken(at);
		
		categoryService.saveCategory(cat, accessToken);
		
		
	}
}
