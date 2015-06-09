/**
 * 
 */
package com.petstore.web.util;

import com.petstore.web.beans.UserDetails;
import com.petstore.web.common.EncryptMD5;

/**
 * @author pbiyyam
 *
 */
public class ValidationUtil {
	
	static EncryptMD5 encrypt = new EncryptMD5();

	public static boolean compareUserDetails(UserDetails user1, UserDetails user2){
	      if ((user1.getEmailId().equalsIgnoreCase(user2.getEmailId())) && (encrypt.encryptMD5(user1.getPassword()).equalsIgnoreCase(user2.getPassword()))){
	         return true;
	      }
	      return false;
	   }
}
