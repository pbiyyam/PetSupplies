/**
 * 
 */
package com.petstore.web.service;

import java.util.List;
import com.petstore.web.beans.Product;

/**
 * @author Praveena BiYYam
 *
 */
public interface ProductService {

public List<Product> getAllProducts();
	
	public Product getProductById(int id);
	
	public void addProduct(Product product);
	
	public void deleteProductById(int id);

    public void deleteAll();

    public void updateProduct(Product product);
}
