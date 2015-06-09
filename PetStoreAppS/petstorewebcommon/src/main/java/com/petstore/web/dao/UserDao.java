/**
 * 
 */
package com.petstore.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.petstore.web.beans.UserDetails;

/**
 * @author Praveena BiYYam
 *
 */
@Repository
@Transactional
public class UserDao{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/*@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}
	
	private EntityManager getEntityManager(){
		return getEntityManagerFactory().createEntityManager();
	}
	
	public void close() {
		getEntityManagerFactory().close();
	}*/
	
	/*public void createUserDetails(){
		System.out.println("in dao create table class before method ::: ");
		int count = entityManager.createQuery("CREATE TABLE User_Details ("
				+ "UserId varchar2(20)  NOT NULL,"
				+ "Password varchar2(10)  NOT NULL,"
				+ " FirstName varchar2(30)  NOT NULL,"
				+ " LastName varchar2(30)  NOT NULL,"
				+ " UserAddress varchar2(50)  NOT NULL,"
				+ " Contactnum integer  NOT NULL,"
				+ " EmailId varchar2(30)  NOT NULL,"
				+ " RoleId integer  NOT NULL,"
				+ " CONSTRAINT User_Details_pk PRIMARY KEY (UserId)) ;").executeUpdate();
		System.out.println("count value is ::: "+count);
		}*/
	
	/*@Transactional
	public void insert(UserDetails user) {
		System.out.println("inside dao class checking for entityManagerfactory --> "+entityManagerFactory);
		System.out.println("check tran is alive or nt"+getEntityManager().getTransaction().isActive());
		getEntityManager().persist(user);
		getEntityManager().getTransaction().commit();
		//entityManager.flush();
	}*/
	
	@Transactional
	public void insert(UserDetails user) {
		System.out.println("inside dao class checking for entityManagerfactory --> "+entityManager);
		//System.out.println("check tran is alive or nt"+entityManager.getTransaction().isActive());
		entityManager.persist(user);
		//entityManager.getTransaction().commit();
		entityManager.flush();
	}
	
	/*public UserDetails getUserInfo(){
		System.out.println("checking session factory obje --> "+entityManagerFactory);
		TypedQuery<UserDetails> query = getEntityManager().createNamedQuery("getUserInfo",UserDetails.class);
		List<UserDetails> userList = query.getResultList();
		return userList.get(0);
	}*/
	
	public UserDetails getUserInfo(){
	System.out.println("checking session factory obje --> "+entityManager);
	TypedQuery<UserDetails> query = entityManager.createNamedQuery("getUserInfo",UserDetails.class);
	List<UserDetails> userList = query.getResultList();
	System.out.println("user email is --> "+userList.get(0).getEmailId());
	return userList.get(0);
	}
}
