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

	public List<Category> getAllCategories();
	
	public Category getCategoryById(int id);
	
	public void addCategory(Category category);
	
	public void deleteCategoryById(int id);

    public void deleteAll();

    public void updateCategory(Category category);
}
