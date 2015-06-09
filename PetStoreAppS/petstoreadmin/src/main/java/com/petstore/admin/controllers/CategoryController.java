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

import com.petstore.web.beans.Category;
import com.petstore.web.beans.Product;
import com.petstore.web.service.CategoryService;

/**
 * @author Praveena BiYYam
 *
 */
@RestController
public class CategoryController {

	private static final Logger LOGGER = Logger.getLogger(CategoryController.class);
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(value = { "/category.json" })
	public @ResponseBody List<Category> getCategories(){
	      LOGGER.debug("In CategoryController:getCategories() ");
	      //return categoryService.getAllCategories();
	      List<Category> cList = categoryService.getAllCategories();
	      List<Category> nList = new ArrayList<Category>();
	      for(Category c : cList){
	    	  c.setProductSet(null);
	    	  nList.add(c);
	      }
	      return nList;
	   }
	
	@RequestMapping(value = "/addCategory.htm", method = RequestMethod.POST)
    public @ResponseBody void addCategory(@RequestBody Category category) {
        categoryService.addCategory(category);
    }
	
	@RequestMapping(value = "/updateCategory.htm", method = RequestMethod.PUT)
    public @ResponseBody void updateCategory(@RequestBody Category category) {
		categoryService.updateCategory(category);
    }
	
	@RequestMapping(value = "/removeCategory.htm", method = RequestMethod.POST)
    public @ResponseBody void removeCategory(@RequestBody Category category) {
		System.out.println("categoryId value "+category.getCategoryId());
        categoryService.deleteCategoryById(category.getCategoryId());
    }
	
	/*@RequestMapping(value = "/removeAllCategories.htm", method = RequestMethod.POST)
    public @ResponseBody void removeAllTrains() {
        categoryService.deleteAll();
    }*/
}
