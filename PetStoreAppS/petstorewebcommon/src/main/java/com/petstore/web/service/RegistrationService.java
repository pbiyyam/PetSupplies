/**
 * 
 */
package com.petstore.web.service;

import com.petstore.web.beans.RoleDetails;
import com.petstore.web.beans.UserDetails;

/**
 * @author Praveena BiYYam
 *
 */
public interface RegistrationService {

	/**
	 * This checks for availability of user emailId whether it has already
	 * registered or not
	 * 
	 * @param emailId
	 *            String
	 * @return int value
	 */
	int findUser(String emailId);

	/**
	 * This method insert new user details into db
	 * 
	 * @param user
	 *            UserDetails
	 * @return int value
	 */
	int newUserCreation(UserDetails user);

	/**
	 * This method fetches user details based on registered emailId
	 * 
	 * @param emailId
	 * @return UserDetails
	 */
	UserDetails fetchLoginDetails(String emailId);

	/**
	 * this method fetches all role details based on role id
	 * 
	 * @param roleId
	 *            int
	 * @return roles
	 */
	RoleDetails getRoleDetails(int roleId);

	/**
	 * this method validated admin login details
	 * 
	 * @param emailId
	 *            String
	 * @return users
	 */
	UserDetails validateAdmin(String emailId);
}
