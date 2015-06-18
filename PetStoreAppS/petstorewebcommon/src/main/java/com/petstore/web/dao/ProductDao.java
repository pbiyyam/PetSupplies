/**
 * 
 */
package com.petstore.web.dao;

import java.util.List;

import com.petstore.web.beans.Category;
import com.petstore.web.beans.Product;

/**
 * @author Praveena BiYYam
 *
 */
public interface ProductDao {

	/**
	 * This method is used for finding product depending on id
	 * 
	 * @param id
	 *            int
	 * @return product
	 */
	Product findProductById(int id);

	/**
	 * This method is used for saving a new product
	 * 
	 * @param product
	 */
	void saveProduct(Product product);

	/**
	 * This method is used for updating existing product
	 * 
	 * @param product
	 */
	void updateProduct(Product product);

	/**
	 * This method is used for deleting selected product
	 * 
	 * @param product
	 */
	void deleteProduct(Product product);

	/**
	 * This method is used for finding all available products
	 * 
	 * @return List<Product>
	 */
	List<Product> findAllProducts();

	/**
	 * This method fetches all available products based on provided category
	 * 
	 * @param cat
	 * @return List<Product>
	 */
	List<Product> fetchProdByCat(Category cat);
}
