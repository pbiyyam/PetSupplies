/**
 * 
 */
package com.petstore.web.serviceimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.petstore.web.beans.Product;
import com.petstore.web.dao.ProductDao;
import com.petstore.web.service.ProductService;

/**
 * @author Praveena BiYYam
 *
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	private static final Logger LOGGER = Logger.getLogger(ProductServiceImpl.class);

	@Autowired
	ProductDao productDao;
	
	@Override
	public List<Product> getAllProducts() {
		LOGGER.debug("In ProductServiceImpl:getAllProducts()");
		List<Product> p = productDao.findAllProducts();
		System.out.println("plist size"+p.size());
		return p;
	}

	@Override
	public Product getProductById(int id) {
		LOGGER.debug("In ProductServiceImpl:getProductById()");
		return productDao.findProductById(id);
	}

	@Override
	public void addProduct(Product product) {
		LOGGER.debug("In ProductServiceImpl:addProduct()");
		productDao.saveProduct(product);
	}

	@Override
	public void deleteProductById(int id) {
		LOGGER.debug("In ProductServiceImpl:deleteProductById() begin");
		Product foundProduct = getProductById(id);
        if (foundProduct != null) {
        	productDao.deleteProduct(foundProduct);
        }
        LOGGER.debug("In ProductServiceImpl:deleteProductById() end");
	}

	@Override
	public void deleteAll() {
		
	}

	@Override
	public void updateProduct(Product product) {
		LOGGER.debug("In ProductServiceImpl:updateProduct()");
		productDao.updateProduct(product);
	}

}
