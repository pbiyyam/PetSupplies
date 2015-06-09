package com.petstore.user.controllers;


/**
 * @author Praveena BiYYam
 *
 */

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.petstore.web.common.EncryptMD5;
import com.petstore.web.beans.Category;
import com.petstore.web.beans.Product;
import com.petstore.web.beans.UserDetails;
import com.petstore.web.service.CategoryService;
import com.petstore.web.service.ProductService;
import com.petstore.web.service.RegistrationService;
import com.petstore.web.util.ValidationUtil;

@RestController
public class HomeController{

   private static final Logger LOGGER = Logger.getLogger(HomeController.class);
   EncryptMD5 encrypt = new EncryptMD5();

   @Autowired
   RegistrationService registrationService;
   
   @Autowired
   ProductService productService;
   
   @Autowired
   CategoryService categoryService;

   @RequestMapping(value = { "/productDetails.json" })
   public @ResponseBody List<Product> displayHome(){
      LOGGER.info("In HomeController:displayHome() ");
      List<Product> pList = productService.getAllProducts();
      List<Product> nList = new ArrayList<Product>();
      for(Product p : pList){
    	  p.setCategory(null);
    	  nList.add(p);
      }
      return nList;
      //return registrationService.getProducts();
   }
   
   @RequestMapping(value = { "/categoryDetails.json" })
   public @ResponseBody List<Category> fetchCategories(){
      LOGGER.info("In HomeController:fetchCategories() ");
      List<Category> cList = categoryService.getAllCategories();
      List<Category> nList = new ArrayList<Category>();
      for(Category c : cList){
    	  c.setProductSet(null);
    	  nList.add(c);
      }
      return nList;
   }

   @RequestMapping(value = "/register.htm", method = RequestMethod.POST)
   public @ResponseBody String processNewRegistration(@RequestBody UserDetails user){
      LOGGER.info("processNewRegistration method begins");
      user.setPassword(encrypt.encryptMD5(user.getPassword()));
      user.setRoles(registrationService.getRoleDetails(1));
      int count = registrationService.findUser(user.getEmailId());
      if (count == 0){
         registrationService.newUserCreation(user);
         return "Success";
      }else{
         return "Error";
      }
   }

   @RequestMapping(value = "/login.htm", method = RequestMethod.POST)
   public @ResponseBody String processUserLogin(@RequestBody UserDetails user){
      LOGGER.info("processUserLogin method begins");
      UserDetails fetchedUser = registrationService.fetchLoginDetails(user.getEmailId());
      if (fetchedUser != null){
         if (ValidationUtil.compareUserDetails(user, fetchedUser) && (fetchedUser.getRoles().getRoleId() == 1)){
            return "Success";
         }else{
        	 return "User Not Authorised";
         }
      }
      LOGGER.info("processNewRegistration method ends");
      return "User Has Not Registered";
   }

}