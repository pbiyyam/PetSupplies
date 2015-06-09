/**
 * 
 */
package com.petstore.web.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.petstore.web.beans.Category;
import com.petstore.web.beans.Product;
import com.petstore.web.beans.RoleDetails;
import com.petstore.web.beans.UserDetails;
import com.petstore.web.dao.RegistrationDao;

/**
 * @author Praveena BiYYam
 * @version 1.0 This class access data source to communicate with database
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
	 * This method fetches all the products available from db
	 * 
	 * @return List<Product>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProducts() {
		LOGGER.info("Inside RegistrationDaoImpl:getProducts()");
		Query productQuery = null;
		try {
			// persistProductsData();
			productQuery = entityManager.createNamedQuery("getProducts",
					Product.class);
			return productQuery.getResultList();
		} catch (Exception e) {
			LOGGER.error("Exception occured in while fetching Products "
					+ e.getMessage());
			return null;
		}
	}

	/**
	 * This method fetches all the categories available from db
	 * 
	 * @return List<Category>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategories() {
		LOGGER.info("Inside RegistrationDaoImpl:getCategories()");
		Query categoryQuery = null;
		try {
			categoryQuery = entityManager.createNamedQuery("getCategories",
					Category.class);
			return categoryQuery.getResultList();
		} catch (Exception e) {
			LOGGER.error("Exception occured in while fetching categories "
					+ e.getMessage());
			return null;
		}
	}

	/**
	 * This method checks whether the emailId entered already exits in db or not
	 * 
	 * @param emailId
	 * @return integer value
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int findUser(String emailId) {
		LOGGER.info("Inside RegistrationDaoImpl:findUser()");
		try {
			Query queryUserByEmail = entityManager.createNamedQuery(
					"getEmailIdCount", UserDetails.class).setParameter(
					"emailId", emailId);
			List<UserDetails> userEmailList = (List<UserDetails>) queryUserByEmail
					.getResultList();
			if (!userEmailList.isEmpty()) {
				return userEmailList.size();
			} else {
				return 0;
			}
		} catch (Exception e) {
			LOGGER.error("Exception occured while fetching user details in RegistrationDaoImpl "
					+ e.getMessage());
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
	@SuppressWarnings("unchecked")
	@Override
	public UserDetails fetchLoginDetails(String emailId) {
		LOGGER.info("Inside RegistrationDaoImpl:fetchLoginDetails()");
		Query queryUserByEmail = entityManager.createNamedQuery(
				"getUserEmailIdPwd", UserDetails.class).setParameter("emailId",
				emailId);
		List<UserDetails> userList = (List<UserDetails>) queryUserByEmail
				.getResultList();
		if (!userList.isEmpty()) {
			return (UserDetails) userList.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public RoleDetails getRoleDetails(int roleId) {
		LOGGER.info("Inside RegistrationDaoImpl:fetchLoginDetails()");
		Query queryUserByEmail = entityManager.createNamedQuery("getRoles",
				RoleDetails.class).setParameter("roleId", roleId);
		List<RoleDetails> roleList = (List<RoleDetails>) queryUserByEmail
				.getResultList();
		if (!roleList.isEmpty()) {
			return (RoleDetails) roleList.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserDetails validateAdmin(String emailId) {
		LOGGER.info("Inside RegistrationDaoImpl:validateAdmin()");
		Query queryUserByEmail = entityManager.createNamedQuery(
				"getAdminEmailIdPwd", UserDetails.class).setParameter("emailId",
				emailId);
		List<UserDetails> userList = (List<UserDetails>) queryUserByEmail
				.getResultList();
		if (!userList.isEmpty()) {
			
			return (UserDetails) userList.get(0);
		}
		return null;
	}

}
