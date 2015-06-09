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
 * @author Praveena BiYYam
 *
 */
@RestController
public class ProductController {

	private static final Logger LOGGER = Logger.getLogger(ProductController.class);
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(value = { "/product.json" })
	public @ResponseBody List<Product> getProducts(){
	      LOGGER.debug("In ProductController:getProducts() begin");
	      List<Product> pList = productService.getAllProducts();
	      List<Product> nList = new ArrayList<Product>();
	      for(Product p : pList){
	    	  p.setCategory(null);
	    	  nList.add(p);
	      }
	      return nList;
	      //return productService.getAllProducts();
	   }
	
	@RequestMapping(value = "/addProduct.htm", method = RequestMethod.POST)
    public @ResponseBody void addProduct(@RequestBody Product product) {
		LOGGER.debug("In ProductController:addProduct() ");
		System.out.println("checking category id"+product.getCategory().getCategoryId());
		product.setCategory(categoryService.getCategoryById(product.getCategory().getCategoryId()));
        productService.addProduct(product);
    }
	
	@RequestMapping(value = "/updateProduct.htm", method = RequestMethod.PUT)
    public @ResponseBody void updateProduct(@RequestBody Product product) {
		LOGGER.debug("In ProductController:updateProduct() ");
		product.setCategory(categoryService.getCategoryById(product.getCategory().getCategoryId()));
		productService.updateProduct(product);
    }
	
	@RequestMapping(value = "/removeProduct.htm", method = RequestMethod.POST)
    public @ResponseBody void removeProduct(@RequestBody Product product) {
		LOGGER.debug("In ProductController:removeProduct() ");
		System.out.println("categoryId value "+product.getProductId());
		productService.deleteProductById(product.getProductId());
    }
}
