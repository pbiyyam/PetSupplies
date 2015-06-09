/**
 * 
 */
package com.petstore.web.dao;

import java.util.List;

import com.petstore.web.beans.Product;

/**
 * @author Praveena BiYYam
 *
 */
public interface ProductDao {

	public Product findProductById(int id);
	
	public void saveProduct(Product product);
	
	public void updateProduct(Product product);
	
	public void deleteProduct(Product product);
	
	public List<Product> findAllProducts();
}
