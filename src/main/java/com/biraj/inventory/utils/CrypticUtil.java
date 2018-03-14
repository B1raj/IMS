package com.biraj.inventory.utils;

/**
 * @author birajmishra
 */

import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

@Component
public class CrypticUtil {

	public static String encrypt(String plainText) {

		return Base64Utils.encodeToString(plainText.getBytes());
	}

	public static String decrypt(String cipherText) {

		return new String(Base64Utils.decode(cipherText.getBytes()));
	}

}
