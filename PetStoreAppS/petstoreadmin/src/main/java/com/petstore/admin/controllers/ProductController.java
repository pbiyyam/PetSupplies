/**
 * 
 */
package com.petstore.admin.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.petstore.web.beans.Product;
import com.petstore.web.service.CategoryService;
import com.petstore.web.service.ProductService;

/**
 * This class acts as a rest controller for processing product management requests
 * @author Praveena BiYYam
 *
 */
@RestController
public class ProductController {

	private static final Logger LOGGER = Logger
			.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	/**
	 * This method fetches all available products for further management
	 * @return nList List<Product>
	 */
	@RequestMapping(value = { "/product.json" })
	public @ResponseBody List<Product> getProducts() {
		LOGGER.debug("In ProductController:getProducts() begin");
		List<Product> pList = productService.getAllProducts();
		List<Product> nList = new ArrayList<Product>();
		for (Product p : pList) {
			p.setCategory(null);
			nList.add(p);
		}
		return nList;
	}

	/**
	 * This method is used for adding a new product
	 * @param product
	 */
	@RequestMapping(value = "/addProduct.htm", method = RequestMethod.POST)
	public @ResponseBody void addProduct(@RequestBody Product product) {
		LOGGER.debug("In ProductController:addProduct() ");
		product.setCategory(categoryService.getCategoryById(product
				.getCategory().getCategoryId()));
		productService.addProduct(product);
	}

	/**
	 * This method is used for updating all the existing products
	 * @param product
	 */
	@RequestMapping(value = "/updateProduct.htm", method = RequestMethod.PUT)
	public @ResponseBody void updateProduct(@RequestBody Product product) {
		LOGGER.debug("In ProductController:updateProduct() ");
		product.setCategory(categoryService.getCategoryById(product
				.getCategory().getCategoryId()));
		productService.updateProduct(product);
	}

	/**
	 * This method is used for removing a selected product
	 * @param product
	 */
	@RequestMapping(value = "/removeProduct.htm", method = RequestMethod.POST)
	public @ResponseBody void removeProduct(@RequestBody Product product) {
		LOGGER.debug("In ProductController:removeProduct() ");
		productService.deleteProductById(product.getProductId());
	}
}
