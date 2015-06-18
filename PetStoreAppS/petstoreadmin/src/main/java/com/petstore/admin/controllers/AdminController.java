package com.petstore.admin.controllers;

/**
 * @author Praveena BiYYam
 *
 */

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.petstore.web.beans.UserDetails;
import com.petstore.web.service.RegistrationService;
import com.petstore.web.util.EncryptMD5;
import com.petstore.web.util.ValidationUtil;

/**
 * This class acts as rest conrtoller for processing the frontend request during admin login
 * @author pbiyyam
 *
 */
@RestController
public class AdminController {

	private static final Logger LOGGER = Logger
			.getLogger(AdminController.class);
	EncryptMD5 encrypt = new EncryptMD5();

	@Autowired
	private RegistrationService registrationService;

	/**
	 * This method displays home page in admin login
	 * @return String
	 */
	@RequestMapping(value = { "/home.htm" })
	public @ResponseBody String displayHome() {
		LOGGER.info("In AdminController:displayHome() ");
		return "";
	}

	/**
	 * This method processes admin login, checks whether admin is authorised or not
	 * @param user UserDetails
	 * @return String
	 */
	@RequestMapping(value = "/adminLogin.htm", method = RequestMethod.POST)
	public @ResponseBody String processUserLogin(@RequestBody UserDetails user) {
		LOGGER.info("processUserLogin method begins");
		UserDetails fetchedAdmin = registrationService.validateAdmin(user
				.getEmailId());
		if (fetchedAdmin != null) {
			if (ValidationUtil.compareUserDetails(user, fetchedAdmin)
					&& (fetchedAdmin.getRoles().getRoleId() == 2)) {
				return "Success";
			} else {
				return "User Not Authorised";
			}
		}
		LOGGER.info("processNewRegistration method ends");
		return "User Has Not Registered";
	}

}