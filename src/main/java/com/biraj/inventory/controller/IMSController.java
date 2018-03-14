package com.biraj.inventory.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biraj.inventory.bean.AccessToken;
import com.biraj.inventory.bean.AuthenticatorResponse;
import com.biraj.inventory.bean.Category;
import com.biraj.inventory.bean.CategoryResponse;
import com.biraj.inventory.bean.IngredientRequest;
import com.biraj.inventory.bean.IngredientResponse;
import com.biraj.inventory.bean.LoginResponse;
import com.biraj.inventory.bean.ProductInfo;
import com.biraj.inventory.bean.ProductResponse;
import com.biraj.inventory.bean.UserTokens;
import com.biraj.inventory.constant.IMSConstant;
import com.biraj.inventory.entity.Ingredient;
import com.biraj.inventory.entity.Product;
import com.biraj.inventory.exception.AuthenticationException;
import com.biraj.inventory.exception.InvalidDataException;
import com.biraj.inventory.service.AuthenticatorService;
import com.biraj.inventory.service.CategoryService;
import com.biraj.inventory.service.IngredientService;
import com.biraj.inventory.service.ProductService;
import com.biraj.inventory.service.impl.AccessFactory;

/**
 * @author biraj Spring boot controller.
 *
 */
@RestController
@RequestMapping(value = "/ims")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IMSController {

	/**
	 * AccessTokenFactory.
	 */
	@Autowired
	private AccessFactory tokenFactory;

	/**
	 * AuthenticatorService.
	 */
	@Autowired
	AuthenticatorService authenticatorService;

	/**
	 * CategoryService.
	 */
	@Autowired
	CategoryService categoryService;

	/**
	 * AuthenticatorService.
	 */
	@Autowired
	ProductService productService;

	/**
	 * IngredientService.
	 */
	@Autowired
	IngredientService ingredientService;

	/**
	 * Logger.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(IMSController.class);

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	private ResponseEntity<LoginResponse> login(HttpServletRequest request,
			@RequestHeader(required = true, value = IMSConstant.UUID) String uuid,
			@RequestHeader(required = true, value = IMSConstant.AUTHORIZATION) String authorization)
			throws AuthenticationException {
		
		// 1. verify credentials
		AuthenticatorResponse response = authenticatorService.authenticate(authorization);
		// 2. generate JWT
		UserTokens userToken = tokenFactory.createToken(response.getUserInfo());
		LOG.info("User Token's successfully created.");
		if (LOG.isTraceEnabled()) {
			LOG.trace("IMSController :  login : userToken : " + userToken);
		}
		// 3. return back
		LoginResponse loginResponse = createResponse(userToken);
		return new ResponseEntity(loginResponse, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/category", method = RequestMethod.POST)
	private ResponseEntity<com.biraj.inventory.entity.Category> createCategory(HttpServletRequest request,
			@RequestHeader(required = true, value = IMSConstant.UUID) String uuid,
			@RequestHeader(required = true, value = IMSConstant.ACCESS_TOKEN) String accessTokenString,
			@RequestBody Category category) {

		if(LOG.isTraceEnabled()){
			LOG.info("IMSController : createCategory : Start");
		}
		AccessToken accessToken = (AccessToken) request.getAttribute(IMSConstant.ACCESS_TOKEN);
		return new ResponseEntity(categoryService.saveCategory(category, accessToken),HttpStatus.CREATED);
	}

	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
	private ResponseEntity<CategoryResponse> getCategory(HttpServletRequest request,
			@RequestHeader(required = true, value = IMSConstant.UUID) String uuid,
			@RequestHeader(required = true, value = IMSConstant.ACCESS_TOKEN) String accessTokenString,
			@PathVariable(value = "id") int id) {
		if(LOG.isTraceEnabled()){
			LOG.info("IMSController : getCategory : Start");
		}
		AccessToken accessToken = (AccessToken) request.getAttribute(IMSConstant.ACCESS_TOKEN);
		return new ResponseEntity(categoryService.getCategory(id, accessToken), HttpStatus.OK);
	}

	@RequestMapping(value = "/category", method = RequestMethod.GET)
	private ResponseEntity<List<CategoryResponse>> getAllCategories(HttpServletRequest request,
			@RequestHeader(required = true, value = IMSConstant.UUID) String uuid,
			@RequestHeader(required = true, value = IMSConstant.ACCESS_TOKEN) String accessTokenString) {
		
		if(LOG.isTraceEnabled()){
			LOG.info("IMSController : getAllCategories : Start");
		}
		AccessToken accessToken = (AccessToken) request.getAttribute(IMSConstant.ACCESS_TOKEN);
		return new ResponseEntity(categoryService.getCategories(accessToken), HttpStatus.OK);
	}

	@RequestMapping(value = "/category/{id}", method = RequestMethod.PUT)
	private ResponseEntity<com.biraj.inventory.entity.Category> updateCategory(HttpServletRequest request,
			@RequestHeader(required = true, value = IMSConstant.UUID) String uuid,
			@RequestHeader(required = true, value = IMSConstant.ACCESS_TOKEN) String accessTokenString,
			@PathVariable(value = "id") int id, @RequestBody Category category) throws InvalidDataException {
		
		if(LOG.isTraceEnabled()){
			LOG.info("IMSController : updateCategory : Start");
		}
		
		AccessToken accessToken = (AccessToken) request.getAttribute(IMSConstant.ACCESS_TOKEN);
		return new ResponseEntity(categoryService.updateCategory(category, accessToken, id),HttpStatus.OK);
	}

	@RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
	private ResponseEntity removeCategory(HttpServletRequest request,
			@RequestHeader(required = true, value = IMSConstant.UUID) String uuid,
			@RequestHeader(required = true, value = IMSConstant.ACCESS_TOKEN) String accessTokenString,
			@PathVariable(value = "id") int id) throws InvalidDataException {
		
		if(LOG.isTraceEnabled()){
			LOG.info("IMSController : removeCategory : Start");
		}
		
		AccessToken accessToken = (AccessToken) request.getAttribute(IMSConstant.ACCESS_TOKEN);
		categoryService.removeCategory(id, accessToken);
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/category/{cid}/product", method = RequestMethod.POST)
	private ResponseEntity<ProductResponse> createProduct(HttpServletRequest request,
			@RequestHeader(required = true, value = IMSConstant.UUID) String uuid,
			@RequestHeader(required = true, value = IMSConstant.ACCESS_TOKEN) String accessTokenString,
			@PathVariable(value = "cid") int cid, @RequestBody ProductInfo product) {
		
		if(LOG.isTraceEnabled()){
			LOG.info("IMSController : createProduct : Start");
		}

		AccessToken accessToken = (AccessToken) request.getAttribute(IMSConstant.ACCESS_TOKEN);
		return new ResponseEntity(productService.saveProduct(product, cid, accessToken),HttpStatus.CREATED);
	}

	@RequestMapping(value = "/category/{cid}/product/{pid}", method = RequestMethod.GET)
	private ResponseEntity<ProductResponse> getProduct(HttpServletRequest request,
			@RequestHeader(required = true, value = IMSConstant.UUID) String uuid,
			@RequestHeader(required = true, value = IMSConstant.ACCESS_TOKEN) String accessTokenString,
			@PathVariable(value = "cid") int cid, @PathVariable(value = "pid") int pid) {
		
		if(LOG.isTraceEnabled()){
			LOG.info("IMSController : getProduct : Start");
		}
		
		AccessToken accessToken = (AccessToken) request.getAttribute(IMSConstant.ACCESS_TOKEN);
		return new ResponseEntity(productService.getProduct(cid, pid, accessToken), HttpStatus.OK);
	}

	@RequestMapping(value = "/category/{cid}/product", method = RequestMethod.GET)
	private ResponseEntity<List<ProductResponse>> getAllProducts(HttpServletRequest request,
			@RequestHeader(required = true, value = IMSConstant.UUID) String uuid,
			@RequestHeader(required = true, value = IMSConstant.ACCESS_TOKEN) String accessTokenString,
			@PathVariable(value = "cid") int cid) {
		
		if(LOG.isTraceEnabled()){
			LOG.info("IMSController : getAllProducts : start");
		}
		
		AccessToken accessToken = (AccessToken) request.getAttribute(IMSConstant.ACCESS_TOKEN);
		return new ResponseEntity(productService.getProducts(cid,accessToken), HttpStatus.OK);
	}

	@RequestMapping(value = "/category/{cid}/product/{pid}", method = RequestMethod.PUT)
	private ResponseEntity<ProductResponse> updateProduct(HttpServletRequest request,
			@RequestHeader(required = true, value = IMSConstant.UUID) String uuid,
			@RequestHeader(required = true, value = IMSConstant.ACCESS_TOKEN) String accessTokenString,
			@PathVariable(value = "cid") int cid, @PathVariable(value = "pid") int pid,
			@RequestBody ProductInfo product) throws InvalidDataException {
		
		if(LOG.isTraceEnabled()){
			LOG.info("IMSController : updateProduct : start");
		}

		AccessToken accessToken = (AccessToken) request.getAttribute(IMSConstant.ACCESS_TOKEN);
		return new ResponseEntity(productService.updateProduct(product, accessToken, cid, pid),HttpStatus.OK);
	}

	@RequestMapping(value = "/ingredient", method = RequestMethod.GET)
	private ResponseEntity<List<IngredientResponse>> getAllIngredients(HttpServletRequest request,
			@RequestHeader(required = true, value = IMSConstant.UUID) String uuid,
			@RequestHeader(required = true, value = IMSConstant.ACCESS_TOKEN) String accessTokenString) {
		
		if(LOG.isTraceEnabled()){
			LOG.info("IMSController : getAllIngredients : start");
		}
		
		AccessToken accessToken = (AccessToken) request.getAttribute(IMSConstant.ACCESS_TOKEN);
		return new ResponseEntity(ingredientService.getAllIngridents(accessToken), HttpStatus.OK);
	}

	@RequestMapping(value = "/ingredient/{id}", method = RequestMethod.GET)
	private ResponseEntity<IngredientResponse> getIngredientsbyId(HttpServletRequest request,
			@RequestHeader(required = true, value = IMSConstant.UUID) String uuid,
			@RequestHeader(required = true, value = IMSConstant.ACCESS_TOKEN) String accessTokenString,
			@PathVariable(value = "id") int id) {
		
		if(LOG.isTraceEnabled()){
			LOG.info("IMSController : getIngredientsbyId : start");
		}
		
		AccessToken accessToken = (AccessToken) request.getAttribute(IMSConstant.ACCESS_TOKEN);
		return new ResponseEntity(ingredientService.getById(id,accessToken), HttpStatus.OK);
	}

	@RequestMapping(value = "/ingredient/{id}", method = RequestMethod.PUT)
	private ResponseEntity UpdateIngredient(HttpServletRequest request,
			@RequestHeader(required = true, value = IMSConstant.UUID) String uuid,
			@RequestHeader(required = true, value = IMSConstant.ACCESS_TOKEN) String accessTokenString,
			@PathVariable(value = "id") int id, @RequestBody IngredientRequest ingredient) {
		
		if(LOG.isTraceEnabled()){
			LOG.info("IMSController : UpdateIngredient : start");
		}
		
		AccessToken accessToken = (AccessToken) request.getAttribute(IMSConstant.ACCESS_TOKEN);
		ingredientService.updateById(ingredient, id, accessToken);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ingredient", method = RequestMethod.POST)
	private ResponseEntity<IngredientResponse> createIngredient(HttpServletRequest request,
			@RequestHeader(required = true, value = IMSConstant.UUID) String uuid,
			@RequestHeader(required = true, value = IMSConstant.ACCESS_TOKEN) String accessTokenString,
			@RequestBody IngredientRequest ingredient) {
		
		if(LOG.isTraceEnabled()){
			LOG.info("IMSController : createIngredient : start");
		}
		
		AccessToken accessToken = (AccessToken) request.getAttribute(IMSConstant.ACCESS_TOKEN);
		return new ResponseEntity(ingredientService.save(ingredient,accessToken),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ingredient/{id}", method = RequestMethod.DELETE)
	private ResponseEntity deleteIngredient(HttpServletRequest request,
			@RequestHeader(required = true, value = IMSConstant.UUID) String uuid,
			@RequestHeader(required = true, value = IMSConstant.ACCESS_TOKEN) String accessTokenString,
			@PathVariable(value = "id") int id) {
		
		if(LOG.isTraceEnabled()){
			LOG.info("IMSController : deleteIngredient : start");
		}
		
		AccessToken accessToken = (AccessToken) request.getAttribute(IMSConstant.ACCESS_TOKEN);
		ingredientService.delete(id,accessToken);
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}
	

	@RequestMapping(value = "/category/{cid}/product/{pid}/order", method = RequestMethod.PUT)
	private ResponseEntity placeOrder(HttpServletRequest request,
			@RequestHeader(required = true, value = IMSConstant.UUID) String uuid,
			@RequestHeader(required = true, value = IMSConstant.ACCESS_TOKEN) String accessTokenString,
			@PathVariable(value = "cid") int cid, @PathVariable(value = "pid") int pid,
			@RequestParam(value = "qty", defaultValue = "1") int qty) throws InvalidDataException {
		
		if(LOG.isTraceEnabled()){
			LOG.info("IMSController : placeOrder : start");
		}
		
		AccessToken accessToken = (AccessToken) request.getAttribute(IMSConstant.ACCESS_TOKEN);
		productService.orderProduct(qty, cid, pid, accessToken);
		return new ResponseEntity(HttpStatus.OK);
	}

	/**
	 * 
	 * @param userToken
	 * @return
	 */
	private LoginResponse createResponse(UserTokens userToken) {
		
		if(LOG.isTraceEnabled()){
			LOG.info("IMSController : createResponse : start");
		}
		
		LoginResponse response = new LoginResponse();
		response.setAccessToken(userToken.getAccessToken());
		return response;
	}

}