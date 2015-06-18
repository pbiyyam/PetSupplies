/**
 * 
 */
package com.petstore.web.serviceimpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petstore.web.beans.RoleDetails;
import com.petstore.web.beans.UserDetails;
import com.petstore.web.dao.RegistrationDao;
import com.petstore.web.service.RegistrationService;

/**
 * This class serves as a service class to communicate with user login related
 * repository classes
 * 
 * @author Praveena BiYYam
 *
 */
@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {

	private static final Logger LOGGER = Logger
			.getLogger(RegistrationServiceImpl.class);

	@Autowired
	RegistrationDao registrationDaoImpl;

	/**
	 * This checks for availability of user emailId whether it has already
	 * registered or not
	 * 
	 * @param emailId
	 *            String
	 * @return int value
	 */
	@Override
	public int findUser(String emailId) {
		LOGGER.info("Inside RegistrationServiceImpl:findUser()");
		return registrationDaoImpl.findUser(emailId);
	}

	/**
	 * This method insert new user details into db
	 * 
	 * @param user
	 *            UserDetails
	 * @return int value
	 */
	@Override
	public int newUserCreation(UserDetails user) {
		LOGGER.info("Inside RegistrationServiceImpl:newUserCreation()");
		return registrationDaoImpl.persistNewUser(user);
	}

	/**
	 * This method fetches user details based on registered emailId
	 * 
	 * @param emailId
	 * @return List<UserDetails>
	 */
	@Override
	public UserDetails fetchLoginDetails(String emailId) {
		LOGGER.info("Inside RegistrationServiceImpl:fetchLoginDetails()");
		return registrationDaoImpl.fetchLoginDetails(emailId);
	}

	/**
	 * this method fetches all role details based on role id
	 * 
	 * @param roleId
	 *            int
	 * @return roles
	 */
	@Override
	public RoleDetails getRoleDetails(int roleId) {
		return registrationDaoImpl.getRoleDetails(roleId);
	}

	/**
	 * this method validated admin login details
	 * 
	 * @param emailId
	 *            String
	 * @return users
	 */
	@Override
	public UserDetails validateAdmin(String emailId) {
		return registrationDaoImpl.validateAdmin(emailId);
	}

}
