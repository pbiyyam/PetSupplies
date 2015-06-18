package com.petstore.web.util;

/**
 * @author Praveena BiYYam
 *
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

public class EncryptMD5 {

	private static final Logger LOGGER = Logger.getLogger(EncryptMD5.class);

	private static MessageDigest digester;
	static {
		try {
			digester = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("Exception while encrypting data ", e);
		}
	}

	public String encryptMD5(String str) {
		if (str == null || str.length() == 0) {
			throw new IllegalArgumentException(
					"String to encript cannot be null or zero length");
		}

		digester.update(str.getBytes());
		byte[] hash = digester.digest();
		StringBuilder sb = new StringBuilder(hash.length * 2);
		for (int i = 0; i < hash.length; i++) {
			int v = hash[i] & 0xff;
			if (v < 16) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(v));
		}
		return sb.toString();
	}
}
