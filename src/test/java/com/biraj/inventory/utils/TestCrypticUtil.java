package com.biraj.inventory.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.biraj.inventory.utils.CrypticUtil;

/**
 * 
 * @author birajmishra
 *
 */
public class TestCrypticUtil {
   
	private static String PWRD = "1234567";

	@Test
	public void testEncrypt() {
		String encrypted = CrypticUtil.encrypt(PWRD);
		assertNotNull(encrypted);
	}

	@Test
	public void testDecrypt() {
		String encrypted = CrypticUtil.encrypt(PWRD);
		assertEquals(PWRD, CrypticUtil.decrypt(encrypted));
	}

}
