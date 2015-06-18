/**
 * 
 */
package com.petstore.web.dao;

import com.petstore.web.beans.RoleDetails;
import com.petstore.web.beans.UserDetails;


/**
 * @author Praveena BiYYam
 *
 */

public interface RegistrationDao {

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
	
	/**
	 * this method fetches all available roles from database
	 * @param roleId int
	 * @return roles
	 */
	RoleDetails getRoleDetails(int roleId);
	
	/**
	 * This method validates whether the provided emailid is of admin or not
	 * @param emailId String
	 * @return users
	 */
	UserDetails validateAdmin(String emailId);
}
