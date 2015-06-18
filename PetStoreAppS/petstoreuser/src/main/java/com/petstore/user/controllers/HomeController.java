package com.petstore.user.controllers;

/**
 * @author Praveena BiYYam
 *
 */

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.petstore.web.beans.Category;
import com.petstore.web.beans.Product;
import com.petstore.web.beans.ShipmentDetails;
import com.petstore.web.beans.UserDetails;
import com.petstore.web.service.CategoryService;
import com.petstore.web.service.ProductService;
import com.petstore.web.service.RegistrationService;
import com.petstore.web.util.EncryptMD5;
import com.petstore.web.util.ValidationUtil;

/**
 * This class acts as rest conrtoller for processing the frontend request during customer login
 * @author pbiyyam
 *
 */
@RestController
public class HomeController {

	private static final Logger LOGGER = Logger.getLogger(HomeController.class);
	EncryptMD5 encrypt = new EncryptMD5();

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	/**
	 * This method fetches all available products and display in user home page
	 * @return nList List<Product>
	 */
	@RequestMapping(value = { "/productDetails.json" })
	public @ResponseBody List<Product> displayHome() {
		LOGGER.info("In HomeController:displayHome() ");
		List<Product> pList = productService.getAllProducts();
		List<Product> nList = new ArrayList<Product>();
		for (Product p : pList) {
			p.setCategory(null);
			p.setProdOrders(null);
			nList.add(p);
		}
		return nList;
	}

	/**
	 * This method fetches all available categories for user display
	 * @return nList List<Category>
	 */
	@RequestMapping(value = { "/categoryDetails.json" })
	public @ResponseBody List<Category> fetchCategories() {
		LOGGER.info("In HomeController:fetchCategories() ");
		List<Category> cList = categoryService.getAllCategories();
		List<Category> nList = new ArrayList<Category>();
		for (Category c : cList) {
			c.setProductSet(null);
			nList.add(c);
		}
		return nList;
	}

	/**
	 * This method process the orders placed by users
	 * @param order ShipmentDetails
	 * @return String
	 */
	@RequestMapping(value = "/placeOrder.htm", method = RequestMethod.POST)
	public @ResponseBody String processCheckOut(
			@RequestBody ShipmentDetails order) {
		LOGGER.info("processCheckOut method begins");
		/*
		 * UserDetails user = registrationService.fetchLoginDetails(order.getUserId()); 
		 * Product prod = productService.getProductById(order.getProductId());
		 */
		return "Success";
	}

	/**
	 * This method fetches all products based on selected category
	 * @param catId
	 * @return nList List<Product>
	 */
	@RequestMapping(value = { "/getProductsByCategory.json" }, method = RequestMethod.POST)
	public @ResponseBody List<Product> getProductsByCategory(
			@RequestBody int catId) {
		LOGGER.info("In HomeController:getProductsByCategory() ");
		Category cat = categoryService.getCategoryById(catId);
		List<Product> pList = productService.getProdByCategory(cat);
		List<Product> nList = new ArrayList<Product>();
		for (Product p : pList) {
			p.setCategory(null);
			nList.add(p);
		}
		return nList;
	}

	/**
	 * This method processes new user registration
	 * @param user UserDetails
	 * @return String
	 */
	@RequestMapping(value = "/register.htm", method = RequestMethod.POST)
	public @ResponseBody String processNewRegistration(
			@RequestBody UserDetails user) {
		LOGGER.info("processNewRegistration method begins");
		user.setPassword(encrypt.encryptMD5(user.getPassword()));
		user.setRoles(registrationService.getRoleDetails(1));
		int count = registrationService.findUser(user.getEmailId());
		if (count == 0) {
			registrationService.newUserCreation(user);
			return "Success";
		} else {
			return "Error";
		}
	}

	/**
	 * This method processes user login, checks whether user already registered or not, compare login details
	 * and then proceed further
	 * @param user UserDetails
	 * @return String
	 */
	@RequestMapping(value = "/login.htm", method = RequestMethod.POST)
	public @ResponseBody String processUserLogin(@RequestBody UserDetails user) {
		LOGGER.info("processUserLogin method begins");
		UserDetails fetchedUser = registrationService.fetchLoginDetails(user
				.getEmailId());
		if (fetchedUser != null) {
			if (ValidationUtil.compareUserDetails(user, fetchedUser)
					&& (fetchedUser.getRoles().getRoleId() == 1)) {
				return "Success";
			} else {
				return "User Not Authorised";
			}
		}
		LOGGER.info("processNewRegistration method ends");
		return "User Has Not Registered";
	}

}