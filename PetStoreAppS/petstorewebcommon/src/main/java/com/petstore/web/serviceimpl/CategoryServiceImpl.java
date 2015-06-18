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
 * This class serves as a service class to communicate with category related repository classes
 * @author Praveena BiYYam
 *
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao categoryDao;

	/**
	 * This method fetches all available categories from database
	 * 
	 * @return List<Category>
	 */
	@Override
	public List<Category> getAllCategories() {
		return categoryDao.findAllCategories();
	}

	/**
	 * this method fetches all categories based on id
	 * 
	 * @param id
	 *            int
	 * @return category
	 */
	@Override
	public Category getCategoryById(int id) {
		return categoryDao.findCategoryById(id);
	}

	/**
	 * this method adds new category into database
	 * 
	 * @param category
	 */
	@Override
	public void addCategory(Category category) {
		// categoryList.add(category);
		categoryDao.saveCategory(category);
	}

	/**
	 * this method deletes selected category from database tables
	 * 
	 * @param id
	 */
	@Override
	public void deleteCategoryById(int id) {
		Category foundCategory = getCategoryById(id);
		if (foundCategory != null) {
			categoryDao.deleteCategory(foundCategory);
		}
	}

	/**
	 * this method updates existing category details
	 * 
	 * @param category
	 */
	@Override
	public void updateCategory(Category category) {
		categoryDao.updateCategory(category);
	}

}
