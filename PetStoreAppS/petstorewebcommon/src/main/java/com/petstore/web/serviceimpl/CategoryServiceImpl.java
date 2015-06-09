/**
 * 
 */
package com.petstore.web.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petstore.web.beans.Category;
import com.petstore.web.dao.CategoryDao;
import com.petstore.web.service.CategoryService;

/**
 * @author Praveena BiYYam
 *
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryDao categoryDao;
	
	
	@Override
	public List<Category> getAllCategories() {
		return categoryDao.findAllCategories();
	}

	@Override
	public Category getCategoryById(int id) {
		return categoryDao.findCategoryById(id);
	}

	@Override
	public void addCategory(Category category) {
		//categoryList.add(category);
		categoryDao.saveCategory(category);
	}

	@Override
	public void deleteCategoryById(int id) {
		Category foundCategory = getCategoryById(id);
        if (foundCategory != null) {
        	categoryDao.deleteCategory(foundCategory);
        }		
	}

	@Override
	public void deleteAll() {
		
	}

	@Override
	public void updateCategory(Category category) {
		categoryDao.updateCategory(category);
	}

}
