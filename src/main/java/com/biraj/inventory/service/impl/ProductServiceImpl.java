/**
 * 
 */
package com.biraj.inventory.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biraj.inventory.bean.AccessToken;
import com.biraj.inventory.bean.ProductInfo;
import com.biraj.inventory.constant.IMSConstant;
import com.biraj.inventory.constant.IMSErrorCodeConstant;
import com.biraj.inventory.entity.Ingredient;
import com.biraj.inventory.entity.Product;
import com.biraj.inventory.exception.InsufficientStockException;
import com.biraj.inventory.exception.InvalidDataException;
import com.biraj.inventory.repository.ProductRepository;
import com.biraj.inventory.service.IngredientService;
import com.biraj.inventory.service.ProductService;
import com.biraj.inventory.utils.DateUtil;

/**
 * @author birajmishra
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	IngredientService ingredientService;

	/**
	 * Logger.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(IngredientServiceImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public Product saveProduct(ProductInfo product, int cid, AccessToken accessToken) {

		List<Integer> ingredientsId = product.getIngredents();
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		for (int id : ingredientsId) {
			Ingredient in = ingredientService.getById(id, accessToken);
			if (null == in) {
				throw new InvalidDataException(IMSErrorCodeConstant.FORBIDDEN,
						IMSConstant.INVALID_REQUEST_FOR_INGREDIENT);
			}
			ingredients.add(in);
		}

		Product entity = new Product();
		entity.setCategoryId(cid);
		entity.setName(product.getName());
		entity.setDescription(product.getDescription());
		entity.setPrice(product.getPrice());
		// entity.setAvailQty(product.getAvailQty());
		entity.setIngredents(ingredients);
		entity.setOutletId(accessToken.getPayload().getOutletId());
		entity.setCreatedBy(accessToken.getPayload().getPartyId());
		entity.setCreatedAt(DateUtil.getTimestamp());
		entity.setUpdatedAt(DateUtil.getTimestamp());
		entity.setUpdatedBy(accessToken.getPayload().getPartyId());
		return productRepository.save(entity);
	}

	@Override
	public Product getProduct(int cid, int pid, AccessToken accessToken) {
		Product p = productRepository.findByIdInAndCategoryIdInAndOutletId(pid, cid,
				accessToken.getPayload().getOutletId());
		LOG.info("product " + p);
		return p;
	}

	@Override
	public List<Product> getProducts(int cid, AccessToken accessToken) {
		List<Product> products = productRepository.findByCategoryIdInAndOutletId(cid,
				accessToken.getPayload().getOutletId());
		LOG.info("products " + products);
		return products;
	}

	@Override
	public Product updateProduct(ProductInfo product, AccessToken accessToken, int cid, int pid)
			throws InvalidDataException {
		Product entity = productRepository.findByIdInAndCategoryIdInAndOutletId(pid, cid,
				accessToken.getPayload().getOutletId());
		if (null == entity) {
			throw new InvalidDataException(IMSErrorCodeConstant.FORBIDDEN, IMSConstant.INVALID_REQUEST);
		}
		if (null != product.getName())
			entity.setName(product.getName());
		if (null != product.getDescription())
			entity.setDescription(product.getDescription());
		if (0 != product.getPrice())
			entity.setPrice(product.getPrice());
		entity.setUpdatedAt(DateUtil.getTimestamp());
		entity.setUpdatedBy(accessToken.getPayload().getPartyId());

		List<Integer> ingredientsId = product.getIngredents();
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		for (int id : ingredientsId) {
			ingredients.add(ingredientService.getById(id, accessToken));
		}
		entity.setIngredents(ingredients);

		return productRepository.save(entity);
	}

	@Override
	public void orderProduct(int qty, int cid, int pid, AccessToken accessToken) {
		Product entity = productRepository.findByIdInAndCategoryIdInAndOutletId(pid, cid,
				accessToken.getPayload().getOutletId());

		LOG.info("product " + entity);
		if (null == entity) {
			throw new InvalidDataException(IMSErrorCodeConstant.FORBIDDEN, IMSConstant.INVALID_REQUEST_FOR_PRODUCT);
		}

		List<Ingredient> ingredents = getAllIngredientsByProductId(pid, cid, accessToken);

		for (Ingredient i : ingredents) {
			if (i.getAvailQty() < qty) {
				throw new InsufficientStockException(IMSErrorCodeConstant.OUT_OF_STOCK,
						i.getName() + " is " + IMSConstant.OUT_OF_STOCK);
			}
		}

		for (Ingredient i : ingredents) {
			ingredientService.updateAvalableQuantity(i.getAvailQty() - qty, i.getId());
		}

		// Stream<Ingredient> sequentialStream = ingredents.stream();
		//
		// sequentialStream.forEach((t)-> {
		// if(t.getAvailQty()< qty){
		// throw new
		// InsufficientStockException(IMSErrorCodeConstant.OUT_OF_STOCK,t.getName()+"
		// is "+IMSConstant.OUT_OF_STOCK);
		// }
		// });

		entity.setUpdatedAt(DateUtil.getTimestamp());
		entity.setUpdatedBy(accessToken.getPayload().getPartyId());
		productRepository.save(entity);
	}

	@Override
	public List<Ingredient> getAllIngredientsByProductId(int pid, int cid, AccessToken accessToken) {
		Product p = productRepository.findByIdInAndCategoryIdInAndOutletId(pid, cid,
				accessToken.getPayload().getOutletId());
		LOG.info("product " + p);
		LOG.info("ingredients of the product " + p.getIngredents());
		return p.getIngredents();
	}

}
