package com.ddn.ddnsell.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5MsUtil {

	public static String md5(String plainText) {
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(
					plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("");
		}
		String md5code = new BigInteger(1, secretBytes).toString(16);// 16????????

		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}


	public static String md5a(String src) {
		return DigestUtils.md5Hex(src);
	}

	
	public static String inputPassToFormPass(String inputPass,String saltInput) {
		//第一次计算md5
		String str = ""+saltInput.charAt(0)+saltInput.charAt(2) + inputPass +saltInput.charAt(5) + saltInput.charAt(4);
		System.out.println(str);
		return md5(str);
	}
	
	public static String formPassToDBPass(String formPass, String saltDB) {
		//第二次次计算md5
		String str = ""+saltDB.charAt(0)+saltDB.charAt(2) + formPass +saltDB.charAt(5) + saltDB.charAt(4);
		return md5(str);
	}
	
	public static String inputPassToDbPass(String inputPass, String saltinput, String saltDB) {
		//第一次次计算md5
		//第二次次计算md5
		String formPass = inputPassToFormPass(inputPass, saltinput);
		String dbPass = formPassToDBPass(formPass, saltDB);
		return dbPass;
	}
	
	public static void main(String[] args) {
//		System.out.println(inputPassToFormPass("123456"));//d3b1294a61a07da9b49b6e22b2cbd7f9
//
//		System.out.println("md5:" + md5("123456"));
//		System.out.println("md5a:"+ md5a("123456"));
		//System.out.println(formPassToDBPass(inputPassToFormPass("123456"), "op9876e"));
//		System.out.println(inputPassToDbPass("123456", "1a2b3c4d"));//b7797cce01b4b131b433b6acf4add449
	}
	
}
