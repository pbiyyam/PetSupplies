/**
 * 
 */
package com.petstore.web.service;

import java.util.List;

import com.petstore.web.beans.Category;
import com.petstore.web.beans.Product;

/**
 * @author Praveena BiYYam
 *
 */
public interface ProductService {

	/**
	 * This method fetches all available products from database
	 * 
	 * @return List<Product>
	 */
	List<Product> getAllProducts();

	/**
	 * this method fetches all product based on id
	 * 
	 * @param id
	 *            int
	 * @return product
	 */
	Product getProductById(int id);

	/**
	 * this method adds new product into database
	 * 
	 * @param product
	 */
	void addProduct(Product product);

	/**
	 * this method deletes selected product from database tables
	 * 
	 * @param id
	 */
	void deleteProductById(int id);

	/**
	 * this method updates existing product details
	 * 
	 * @param product
	 */
	void updateProduct(Product product);

	/**
	 * This method fetches all product based on categroy
	 * 
	 * @param cat
	 * @return List<Product>
	 */
	List<Product> getProdByCategory(Category cat);
}
