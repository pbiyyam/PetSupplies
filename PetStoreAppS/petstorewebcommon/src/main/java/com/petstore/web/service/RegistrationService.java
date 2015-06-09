/**
 * 
 */
package com.petstore.web.service;

import java.util.List;

import com.petstore.web.beans.Category;
import com.petstore.web.beans.Product;
import com.petstore.web.beans.RoleDetails;
import com.petstore.web.beans.UserDetails;

/**
 * @author Praveena BiYYam
 *
 */
public interface RegistrationService {
	
	/**
	 * This method fetches all the products available from product details table
	 * @return List<Product>
	 */
	List<Product> getProducts();
	
	/**
	 * This method fetches all the categories available from Category table
	 * @return List<Category>
	 */
	List<Category> getCategories();

	/**
	 * This checks for availability of user emailId whether it has already registered or not
	 * 
	 * @param emailId String
	 * @return int value
	 */
	int findUser(String emailId);
	
	/**
	 * This method insert new user details into db
	 * @param user UserDetails
	 * @return int value
	 */
	int newUserCreation(UserDetails user);
	
	/**
	 * This method fetches user details based on registered emailId
	 * @param emailId
	 * @return UserDetails
	 */
	UserDetails fetchLoginDetails(String emailId);
	
	RoleDetails getRoleDetails(int roleId);
	
	UserDetails validateAdmin(String emailId);
}
