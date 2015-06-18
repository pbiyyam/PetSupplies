/**
 * 
 */
package com.petstore.web.dao;

import java.util.List;

import com.petstore.web.beans.Category;

/**
 * @author Praveena BiYYam
 *
 */
public interface CategoryDao {

	/**
	 * This method is used for finding category depending on id
	 * @param id int
	 * @return category
	 */
	Category findCategoryById(int id);
	
	/**
	 * This method is used for saving a new category
	 * @param category
	 */
	void saveCategory(Category category);
	
	/**
	 * This method is used for updating existing category
	 * @param category
	 */
	void updateCategory(Category category);
	
	/**
	 * This method is used for deleting selected category
	 * @param category
	 */
	void deleteCategory(Category category);
	
	/**
	 * This method is used for finding all available categories
	 * @return List<Category>
	 */
	List<Category> findAllCategories();
}
