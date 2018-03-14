/**
 * 
 */
package com.biraj.inventory.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biraj.inventory.bean.AccessToken;
import com.biraj.inventory.bean.IngredientRequest;
import com.biraj.inventory.constant.IMSConstant;
import com.biraj.inventory.constant.IMSErrorCodeConstant;
import com.biraj.inventory.entity.Ingredient;
import com.biraj.inventory.exception.InvalidDataException;
import com.biraj.inventory.repository.IngredientRepository;
import com.biraj.inventory.service.IngredientService;

/**
 * @author birajmishra
 *
 */
@Service
public class IngredientServiceImpl implements IngredientService {

	@Autowired
	IngredientRepository ingredientRepository;

	/**
	 * Logger.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(IngredientServiceImpl.class);

	@Override
	public Ingredient getById(int id,AccessToken accessToken) {
		return ingredientRepository.findByIdInAndOutletId(id,accessToken.getPayload().getOutletId());
	}

	@Override
	public void saveIngredient(Ingredient entity) {
		ingredientRepository.save(entity);
	}

	@Override
	public List<Ingredient> getAllIngridents(AccessToken accessToken) {
		return (List<Ingredient>) ingredientRepository.findByOutletId(accessToken.getPayload().getOutletId());
	}

	@Override
	public void updateById(IngredientRequest ingredient, int id, AccessToken accessToken) {
		Ingredient entity = ingredientRepository.findByIdInAndOutletId(id,accessToken.getPayload().getOutletId());
		LOG.info("Ingredient " + entity);
		if (null == entity) {
			throw new InvalidDataException(IMSErrorCodeConstant.FORBIDDEN, IMSConstant.INVALID_REQUEST);
		}
		entity.setAvailQty(ingredient.getAvailQty());
		ingredientRepository.save(entity);
	}

	@Override
	public Ingredient save(IngredientRequest ingredient,AccessToken accessToken) {
		Ingredient entity = new Ingredient();
		entity.setName(ingredient.getName());
		entity.setAvailQty(ingredient.getAvailQty());
		entity.setOutletId(accessToken.getPayload().getOutletId());
		return ingredientRepository.save(entity);
	}

	@Override
	@Transactional
	public void updateAvalableQuantity(int qty, int id) {
		ingredientRepository.updateAvalableQuantity(qty, id);
		
	}

	@Override
	public void delete(int id, AccessToken accessToken) {
		Ingredient ingredient = getById(id,accessToken);
		if (null == ingredient) {
			throw new InvalidDataException(IMSErrorCodeConstant.FORBIDDEN, IMSConstant.INVALID_REQUEST);
		}
		ingredientRepository.delete(ingredient);
		
	}


}
