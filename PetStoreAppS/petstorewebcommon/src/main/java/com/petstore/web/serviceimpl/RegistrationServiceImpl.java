/**
 * 
 */
package com.petstore.web.serviceimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petstore.web.beans.Category;
import com.petstore.web.beans.Product;
import com.petstore.web.beans.RoleDetails;
import com.petstore.web.beans.UserDetails;
import com.petstore.web.dao.RegistrationDao;
import com.petstore.web.service.RegistrationService;

/**
 * @author Praveena BiYYam
 *
 */
@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService{

   
   private static final Logger LOGGER = Logger.getLogger(RegistrationServiceImpl.class);
   
	@Autowired
	RegistrationDao registrationDaoImpl;
	
	/**
	 * This method fetches all the products available from product details table
	 * @return List<Product>
	 */
	@Override
	public List<Product> getProducts() {
	   LOGGER.info("Inside RegistrationServiceImpl:getProducts()");
		return registrationDaoImpl.getProducts();
	}
	
	/**
    * This method fetches all the categories available from category table
    * @return List<Category>
    */
   @Override
   public List<Category> getCategories(){
      LOGGER.info("Inside RegistrationServiceImpl:getCategories()");
      return registrationDaoImpl.getCategories();
   }
	
	/**
	 * This checks for availability of user emailId whether it has already registered or not
	 * 
	 * @param emailId String
	 * @return int value
	 */
	@Override
	public int findUser(String emailId) {
	   LOGGER.info("Inside RegistrationServiceImpl:findUser()");
		return registrationDaoImpl.findUser(emailId);
	}

	/**
	 * This method insert new user details into db
	 * @param user UserDetails
	 * @return int value
	 */
	@Override
	public int newUserCreation(UserDetails user) {
	   LOGGER.info("Inside RegistrationServiceImpl:newUserCreation()");
		return registrationDaoImpl.persistNewUser(user);
	}

	/**
	 * This method fetches user details based on registered emailId
	 * @param emailId
	 * @return List<UserDetails>
	 */
	@Override
	public UserDetails fetchLoginDetails(String emailId) {
	   LOGGER.info("Inside RegistrationServiceImpl:fetchLoginDetails()");
		return registrationDaoImpl.fetchLoginDetails(emailId);
	}

	@Override
	public RoleDetails getRoleDetails(int roleId) {
		return registrationDaoImpl.getRoleDetails(roleId);
	}

	@Override
	public UserDetails validateAdmin(String emailId) {
		return registrationDaoImpl.validateAdmin(emailId);
	}

}
