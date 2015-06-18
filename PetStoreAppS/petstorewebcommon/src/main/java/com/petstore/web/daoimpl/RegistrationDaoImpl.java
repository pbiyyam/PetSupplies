/**
 * 
 */
package com.petstore.web.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.petstore.web.beans.RoleDetails;
import com.petstore.web.beans.UserDetails;
import com.petstore.web.dao.RegistrationDao;
import com.petstore.web.util.Commonconstants;

/**
 * This class serves as a repository for all user login related db operations
 * @author Praveena BiYYam
 * @version 1.0 
 */
@Repository
@Transactional
public class RegistrationDaoImpl implements RegistrationDao {

	private static final Logger LOGGER = Logger
			.getLogger(RegistrationDaoImpl.class);

	/**
	 * EntityManage Instance to communicate with DS
	 */
	@PersistenceContext
	private EntityManager entityManager;


	/**
	 * This method checks whether the emailId entered already exits in db or not
	 * 
	 * @param emailId
	 * @return integer value
	 */
	@Override
	public int findUser(String emailId) {
		LOGGER.info("Inside RegistrationDaoImpl:findUser()");
		try {
			TypedQuery<UserDetails> queryUserByEmail = entityManager.createNamedQuery(
					Commonconstants.GET_EMAILID_COUNT, UserDetails.class).setParameter(
					Commonconstants.EMAIL_ID, emailId);
			List<UserDetails> userEmailList = (List<UserDetails>) queryUserByEmail
					.getResultList();
			if (!userEmailList.isEmpty()) {
				return userEmailList.size();
			} else {
				return 0;
			}
		} catch (Exception e) {
			LOGGER.error("Exception occured while fetching user details in RegistrationDaoImpl ", e);
			return 0;
		}
	}

	/**
	 * This method inserts new user details into userdetail table
	 * 
	 * @param user
	 *            UserDetails
	 * @return int value
	 */
	@Override
	public int persistNewUser(UserDetails user) {
		LOGGER.info("Inside RegistrationDaoImpl:persistNewUser() begin");
		entityManager.persist(user);
		LOGGER.info("Inside RegistrationDaoImpl:persistNewUser() end");
		return 1;
	}

	/**
	 * This method fetches user details from db based on registered emailid
	 * 
	 * @param emailId
	 *            String
	 * @return List<UserDetaild>
	 */
	@Override
	public UserDetails fetchLoginDetails(String emailId) {
		LOGGER.info("Inside RegistrationDaoImpl:fetchLoginDetails()");
		TypedQuery<UserDetails> queryUserByEmail = entityManager.createNamedQuery(
				Commonconstants.GET_USER_EMAILID_PWD, UserDetails.class).setParameter(Commonconstants.EMAIL_ID,
				emailId);
		List<UserDetails> userList = (List<UserDetails>) queryUserByEmail
				.getResultList();
		if (!userList.isEmpty()) {
			return (UserDetails) userList.get(0);
		}
		return null;
	}

	/**
	 * this method fetches all available roles from database
	 * @param roleId int
	 * @return roles
	 */
	@Override
	public RoleDetails getRoleDetails(int roleId) {
		LOGGER.info("Inside RegistrationDaoImpl:fetchLoginDetails()");
		TypedQuery<RoleDetails> queryUserByEmail = entityManager.createNamedQuery(Commonconstants.GET_ROLES,
				RoleDetails.class).setParameter(Commonconstants.ROLE_ID, roleId);
		List<RoleDetails> roleList = (List<RoleDetails>) queryUserByEmail
				.getResultList();
		if (!roleList.isEmpty()) {
			return (RoleDetails) roleList.get(0);
		}
		return null;
	}

	/**
	 * This method validates whether the provided emailid is of admin or not
	 * @param emailId String
	 * @return users
	 */
	@Override
	public UserDetails validateAdmin(String emailId) {
		LOGGER.info("Inside RegistrationDaoImpl:validateAdmin()");
		TypedQuery<UserDetails> queryUserByEmail = entityManager.createNamedQuery(
				Commonconstants.GET_ADMIN_EMAIL_PWD, UserDetails.class).setParameter(Commonconstants.EMAIL_ID,
				emailId);
		List<UserDetails> userList = (List<UserDetails>) queryUserByEmail
				.getResultList();
		if (!userList.isEmpty()) {
			
			return (UserDetails) userList.get(0);
		}
		return null;
	}

}
