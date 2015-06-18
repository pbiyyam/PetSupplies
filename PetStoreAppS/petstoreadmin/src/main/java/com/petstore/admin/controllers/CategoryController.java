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
import com.petstore.web.service.CategoryService;

/**
 * This class acts as a rest controller for processing category management requests
 * @author Praveena BiYYam
 *
 */
@RestController
public class CategoryController {

	private static final Logger LOGGER = Logger
			.getLogger(CategoryController.class);

	@Autowired
	private CategoryService categoryService;

	/**
	 * This method fetches all available categories for further management
	 * @return nList List<Category>
	 */
	@RequestMapping(value = { "/category.json" })
	public @ResponseBody List<Category> getCategories() {
		LOGGER.debug("In CategoryController:getCategories() ");
		List<Category> cList = categoryService.getAllCategories();
		List<Category> nList = new ArrayList<Category>();
		for (Category c : cList) {
			c.setProductSet(null);
			nList.add(c);
		}
		return nList;
	}

	/**
	 * This method is used for adding a new category
	 * @param category
	 */
	@RequestMapping(value = "/addCategory.htm", method = RequestMethod.POST)
	public @ResponseBody void addCategory(@RequestBody Category category) {
		categoryService.addCategory(category);
	}

	/**
	 * This method is used for updating all the existing categories
	 * @param category
	 */
	@RequestMapping(value = "/updateCategory.htm", method = RequestMethod.PUT)
	public @ResponseBody void updateCategory(@RequestBody Category category) {
		categoryService.updateCategory(category);
	}

	/**
	 * This method is used for removing a selected category
	 * @param category
	 */
	@RequestMapping(value = "/removeCategory.htm", method = RequestMethod.POST)
	public @ResponseBody void removeCategory(@RequestBody Category category) {
		categoryService.deleteCategoryById(category.getCategoryId());
	}
}
