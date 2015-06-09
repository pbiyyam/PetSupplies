/**
 * 
 */
package com.petstore.web.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petstore.web.beans.UserDetails;
import com.petstore.web.dao.UserDao;


/**
 * @author pbiyyam
 *
 */
@Service
@Transactional
public class UserService {

	@Autowired
	UserDao userDao;
	
	public boolean insertUserDetails(UserDetails user){
		boolean flag = true;
		userDao.insert(user);
		return flag;
	}
	
	public UserDetails getUserInfo(){
		System.out.println("checking if dao object exists --> "+userDao);
		UserDetails user = userDao.getUserInfo();
		System.out.println("checking if person exists in service class "+user);
		System.out.println("checkign user obj valuess -->>> "+user.getId()+"-->> "+user.getPassword());
		return user;
	}
}
