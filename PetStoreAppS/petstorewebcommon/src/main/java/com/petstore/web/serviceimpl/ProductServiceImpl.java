/**
 * 
 */
package com.petstore.web.serviceimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petstore.web.beans.Category;
import com.petstore.web.beans.Product;
import com.petstore.web.dao.ProductDao;
import com.petstore.web.service.ProductService;

/**
 * This class serves as a service class to communicate with product related
 * repository classes
 * 
 * @author Praveena BiYYam
 *
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	private static final Logger LOGGER = Logger
			.getLogger(ProductServiceImpl.class);

	@Autowired
	ProductDao productDao;

	/**
	 * This method fetches all available products from database
	 * 
	 * @return List<Product>
	 */
	@Override
	public List<Product> getAllProducts() {
		LOGGER.debug("In ProductServiceImpl:getAllProducts()");
		List<Product> p = productDao.findAllProducts();
		return p;
	}

	/**
	 * this method fetches all product based on id
	 * 
	 * @param id
	 *            int
	 * @return product
	 */
	@Override
	public Product getProductById(int id) {
		LOGGER.debug("In ProductServiceImpl:getProductById()");
		return productDao.findProductById(id);
	}

	/**
	 * this method adds new product into database
	 * 
	 * @param product
	 */
	@Override
	public void addProduct(Product product) {
		LOGGER.debug("In ProductServiceImpl:addProduct()");
		productDao.saveProduct(product);
	}

	/**
	 * this method deletes selected product from database tables
	 * 
	 * @param id
	 */
	@Override
	public void deleteProductById(int id) {
		LOGGER.debug("In ProductServiceImpl:deleteProductById() begin");
		Product foundProduct = getProductById(id);
		if (foundProduct != null) {
			productDao.deleteProduct(foundProduct);
		}
		LOGGER.debug("In ProductServiceImpl:deleteProductById() end");
	}

	/**
	 * this method updates existing product details
	 * 
	 * @param product
	 */
	@Override
	public void updateProduct(Product product) {
		LOGGER.debug("In ProductServiceImpl:updateProduct()");
		productDao.updateProduct(product);
	}

	/**
	 * This method fetches all product based on categroy
	 * 
	 * @param cat
	 * @return List<Product>
	 */
	@Override
	public List<Product> getProdByCategory(Category cat) {
		LOGGER.debug("In ProductServiceImpl:updateProduct()");
		return productDao.fetchProdByCat(cat);
	}
}
