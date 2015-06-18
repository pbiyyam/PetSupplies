/**
 * 
 */
package com.petstore.web.util;

import com.petstore.web.beans.UserDetails;

/**
 * @author pbiyyam
 *
 */
public class ValidationUtil {

	static EncryptMD5 encrypt = new EncryptMD5();

	/**
	 * This method compares the provided user credentails and the db fetched credentails 
	 * @param user1 UserDetails
	 * @param user2 UserDetails
	 * @return boolean
	 */
	public static boolean compareUserDetails(UserDetails user1,
			UserDetails user2) {
		if ((user1.getEmailId().equalsIgnoreCase(user2.getEmailId()))
				&& (encrypt.encryptMD5(user1.getPassword())
						.equalsIgnoreCase(user2.getPassword()))) {
			return true;
		}
		return false;
	}
}
