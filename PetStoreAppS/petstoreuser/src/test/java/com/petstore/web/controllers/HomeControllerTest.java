package com.petstore.web.controllers;

/**
 * @author Praveena BiYYam
 *
 */

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.petstore.user.controllers.HomeController;
import com.petstore.web.common.EncryptMD5;
import com.petstore.web.beans.Product;
import com.petstore.web.beans.UserDetails;
import com.petstore.web.service.RegistrationService;
 
public class HomeControllerTest {
	
	public static final Logger log = Logger.getLogger(HomeControllerTest.class);
	EncryptMD5 encrypt=new EncryptMD5();
	
		
	public void testDisplayHome(){
		HomeController homeCtrl = new HomeController();
		//List<Product> list = homeCtrl.displayHome();
		//System.out.println("list values"+list);
	}

}