package com.biraj.inventory.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biraj.inventory.bean.AccessToken;
import com.biraj.inventory.bean.Category;
import com.biraj.inventory.constant.IMSConstant;
import com.biraj.inventory.constant.IMSErrorCodeConstant;
import com.biraj.inventory.controller.IMSController;
import com.biraj.inventory.exception.InvalidDataException;
import com.biraj.inventory.repository.CategoryRepository;
import com.biraj.inventory.service.CategoryService;
import com.biraj.inventory.utils.DateUtil;

/**
 * 
 * @author birajmishra
 *
 */
@Service
public class CategoryServiceImpl implements CategoryService{
	
	
	@Autowired
	CategoryRepository categoryRepository;
	
	/**
	 * Logger.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(IMSController.class);


	@Override
	public com.biraj.inventory.entity.Category saveCategory(Category c,AccessToken accessToken) throws InvalidDataException{
		
		com.biraj.inventory.entity.Category entity = new com.biraj.inventory.entity.Category();
		entity.setName(c.getName());
		entity.setDescription(c.getDescription());
		entity.setEnabled(c.getEnabled());
		entity.setOutletId(accessToken.getPayload().getOutletId());
		entity.setCreatedBy(accessToken.getPayload().getPartyId());
		entity.setCreatedAt(DateUtil.getTimestamp());
		entity.setUpdatedAt(DateUtil.getTimestamp());
		entity.setUpdatedBy(accessToken.getPayload().getPartyId());
		LOG.info("Saving category "+entity);
		return categoryRepository.save(entity);
		
		
	}

	@Override
	public List<com.biraj.inventory.entity.Category> getCategories(AccessToken accessToken) throws InvalidDataException{
		return (List<com.biraj.inventory.entity.Category>) categoryRepository.findAllByOutletId(accessToken.getPayload().getOutletId());		
	}
	

	@Override
	public com.biraj.inventory.entity.Category getCategory(int id, AccessToken accessToken) throws InvalidDataException{
		return (com.biraj.inventory.entity.Category) categoryRepository.findByIdInAndOutletId(id,accessToken.getPayload().getOutletId());
	}

	@Override
	
	public com.biraj.inventory.entity.Category updateCategory(Category c, AccessToken accessToken, int id) throws InvalidDataException{
		com.biraj.inventory.entity.Category entity = (com.biraj.inventory.entity.Category) categoryRepository.findByIdInAndOutletId(id,accessToken.getPayload().getOutletId());
		if(null == entity){
			throw new InvalidDataException(IMSErrorCodeConstant.FORBIDDEN,
					IMSConstant.INVALID_REQUEST);
		}
		
		if(null != c.getName()) entity.setName(c.getName());
		if(null != c.getDescription()) entity.setDescription(c.getDescription());
		if(' '!=c.getEnabled()) entity.setEnabled(c.getEnabled());
		entity.setOutletId(accessToken.getPayload().getOutletId());
		entity.setUpdatedAt(DateUtil.getTimestamp());
		entity.setUpdatedBy(accessToken.getPayload().getPartyId());
		
		return categoryRepository.save(entity);
	}

	@Override
	@Transactional 
	public void removeCategory(int id, AccessToken accessToken) throws InvalidDataException {
		com.biraj.inventory.entity.Category category = (com.biraj.inventory.entity.Category) categoryRepository.findByIdInAndOutletId(id,accessToken.getPayload().getOutletId());
		if(null == category){
			throw new InvalidDataException(IMSErrorCodeConstant.FORBIDDEN,
					IMSConstant.INVALID_REQUEST);
		}
		LOG.info("Undating  category with is "+category.getId());
		categoryRepository.updateCategory('N',category.getId());
	}

}
