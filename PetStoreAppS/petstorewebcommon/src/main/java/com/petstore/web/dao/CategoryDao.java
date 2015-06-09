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

	public Category findCategoryById(int id);
	
	public void saveCategory(Category category);
	
	public void updateCategory(Category category);
	
	public void deleteCategory(Category category);
	
	public List<Category> findAllCategories();
}
