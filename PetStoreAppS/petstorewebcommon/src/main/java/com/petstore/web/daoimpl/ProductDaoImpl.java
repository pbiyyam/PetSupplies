/**
 * 
 */
package com.petstore.web.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.petstore.web.beans.Product;
import com.petstore.web.dao.ProductDao;

/**
 * @author Praveena BiYYam
 *
 */
@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {
	
	private static final Logger LOGGER = Logger.getLogger(ProductDaoImpl.class);

	@PersistenceContext
	private EntityManager em;
	
	private static List<Product> productList = new ArrayList<Product>();
	
	@SuppressWarnings("unchecked")
	@Override
	public Product findProductById(int id) {
		LOGGER.debug("In ProductDaoImpl:findProductById() begin");
		Query query = em.createNamedQuery("getProductById", Product.class).setParameter("productId", id);
		productList = (List<Product>)query.getResultList();
		LOGGER.debug("In ProductDaoImpl:findProductById() end");
		return productList.get(0);   
	}

	@Override
	public void saveProduct(Product product) {
		LOGGER.debug("In ProductDaoImpl:saveProduct() begin");
		em.persist(product);
		em.flush();
		LOGGER.debug("In ProductDaoImpl:saveProduct() end");
	}

	@Override
	public void updateProduct(Product product) {
		LOGGER.debug("In ProductDaoImpl:updateProduct() begin");
		em.merge(product);
		LOGGER.debug("In ProductDaoImpl:updateProduct() end");
	}

	@Override
	public void deleteProduct(Product product) {
		LOGGER.debug("In ProductDaoImpl:deleteProduct() begin");
		em.remove(product);
		LOGGER.debug("In ProductDaoImpl:deleteProduct() end");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findAllProducts() {
		LOGGER.debug("In ProductDaoImpl:findAllProducts() begin");
		Query query = em.createNamedQuery("getAllProducts", Product.class);
		productList = (List<Product>)query.getResultList();
		LOGGER.debug("In ProductDaoImpl:findAllProducts() end");
		return productList;
	}

}
