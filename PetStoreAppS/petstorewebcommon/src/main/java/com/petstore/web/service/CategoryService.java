/**
 * 
 */
package com.petstore.web.service;

import java.util.List;

import com.petstore.web.beans.Category;

/**
 * @author Praveena BiYYam
 *
 */
public interface CategoryService {

	/**
	 * This method fetches all available categories from database
	 * 
	 * @return List<Category>
	 */
	List<Category> getAllCategories();

	/**
	 * this method fetches all categories based on id
	 * 
	 * @param id
	 *            int
	 * @return category
	 */
	Category getCategoryById(int id);

	/**
	 * this method adds new category into database
	 * 
	 * @param category
	 */
	void addCategory(Category category);

	/**
	 * this method deletes selected category from database tables
	 * 
	 * @param id
	 */
	void deleteCategoryById(int id);

	/**
	 * this method updates existing category details
	 * 
	 * @param category
	 */
	void updateCategory(Category category);
}
