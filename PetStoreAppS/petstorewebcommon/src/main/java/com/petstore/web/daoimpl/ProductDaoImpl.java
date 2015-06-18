/**
 * 
 */
package com.petstore.web.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.petstore.web.beans.Category;
import com.petstore.web.beans.Product;
import com.petstore.web.dao.ProductDao;
import com.petstore.web.util.Commonconstants;

/**
 * This class serves as a repository for all product related db operations
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

	/**
	 * This method is used for finding product depending on id
	 * 
	 * @param id
	 *            int
	 * @return product
	 */
	@Override
	public Product findProductById(int id) {
		LOGGER.debug("In ProductDaoImpl:findProductById() begin");
		TypedQuery<Product> query = em.createNamedQuery(
				Commonconstants.GET_PROD_BYID, Product.class).setParameter(
				Commonconstants.PRODUCT_ID, id);
		productList = (List<Product>) query.getResultList();
		LOGGER.debug("In ProductDaoImpl:findProductById() end");
		return productList.get(0);
	}

	/**
	 * This method is used for saving a new product
	 * 
	 * @param product
	 */
	@Override
	public void saveProduct(Product product) {
		LOGGER.debug("In ProductDaoImpl:saveProduct() begin");
		em.persist(product);
		em.flush();
		LOGGER.debug("In ProductDaoImpl:saveProduct() end");
	}

	/**
	 * This method is used for updating existing product
	 * 
	 * @param product
	 */
	@Override
	public void updateProduct(Product product) {
		LOGGER.debug("In ProductDaoImpl:updateProduct() begin");
		em.merge(product);
		LOGGER.debug("In ProductDaoImpl:updateProduct() end");
	}

	/**
	 * This method is used for deleting selected product
	 * 
	 * @param product
	 */
	@Override
	public void deleteProduct(Product product) {
		LOGGER.debug("In ProductDaoImpl:deleteProduct() begin");
		em.remove(product);
		LOGGER.debug("In ProductDaoImpl:deleteProduct() end");
	}

	/**
	 * This method is used for finding all available products
	 * 
	 * @return List<Product>
	 */
	@Override
	public List<Product> findAllProducts() {
		LOGGER.debug("In ProductDaoImpl:findAllProducts() begin");
		TypedQuery<Product> query = em.createNamedQuery(
				Commonconstants.GET_ALL_PRODUCTS, Product.class);
		productList = (List<Product>) query.getResultList();
		LOGGER.debug("In ProductDaoImpl:findAllProducts() end");
		return productList;
	}

	/**
	 * This method fetches all available products based on provided category
	 * 
	 * @param cat
	 * @return List<Product>
	 */
	@Override
	public List<Product> fetchProdByCat(Category cat) {
		LOGGER.debug("In ProductDaoImpl:findAllProducts() begin");
		TypedQuery<Product> query = em.createNamedQuery(
				Commonconstants.GET_PROD_BY_CAT, Product.class).setParameter(
				Commonconstants.CATEGORY, cat);
		productList = (List<Product>) query.getResultList();
		LOGGER.debug("In ProductDaoImpl:findAllProducts() end");
		return productList;
	}

}
