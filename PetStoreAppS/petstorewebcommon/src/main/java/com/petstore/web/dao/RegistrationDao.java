/**
 * 
 */
package com.petstore.web.dao;

import java.util.List;

import com.petstore.web.beans.Category;
import com.petstore.web.beans.Product;
import com.petstore.web.beans.RoleDetails;
import com.petstore.web.beans.UserDetails;


/**
 * @author Praveena BiYYam
 *
 */

public interface RegistrationDao {
	
	/**
	 * This method fetches all the products available from db
	 * @return List<Product>
	 */
	List<Product> getProducts();
	
	/**
	 * This method fetches all the categories available from db
    * @return List<Category>
	 */
	List<Category> getCategories();

	/**
	 * This method checks whether the emailId entered already exits in db or not
	 * @param emailId
	 * @return integer value
	 */
	int findUser(String emailId);
	
	/**
	 * This method inserts new user details into userdetail table
	 * @param user UserDetails
	 * @return int value
	 */
	int persistNewUser(UserDetails user);
	
	/**
	 * This method fetches user details from db based on registered emailid
	 * @param emailId String
	 * @return List<UserDetaild>
	 */
	UserDetails fetchLoginDetails(String emailId);
	
	RoleDetails getRoleDetails(int roleId);
	
	UserDetails validateAdmin(String emailId);
}
