package com.ideaclicks.liferay.spring.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class IClicksEncriptionDecription {
	private static final String AES_ALGORITHM = "AES";
	private static  final int ENCRYPT_MODE = 1;
	private  static final int DECRYPT_MODE = 2;
	private  static final byte[] PRIVATE_KEY = "Gnt@k*1&D0(|)0%k".getBytes();

	public static String doCypher(final int mode, final String data) {
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(AES_ALGORITHM);
			cipher.init(mode, generateKey());
			switch (mode) {
			case ENCRYPT_MODE: {
				return new Base64().encodeBase64String(cipher.doFinal(data
						.getBytes()));
			}
			case DECRYPT_MODE: {
				return new String(cipher.doFinal(new Base64()
						.decodeBase64(data)));
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Key generateKey() throws Exception {
		return new SecretKeySpec(PRIVATE_KEY, AES_ALGORITHM);
	}

	public static void main(String[] args) {
		String password="C204LaValle#";
		String encrPwd= IClicksEncriptionDecription.doCypher(IClicksEncriptionDecription.ENCRYPT_MODE, password);
		System.out.println(encrPwd);
		
		System.out.println(IClicksEncriptionDecription.doCypher(IClicksEncriptionDecription.DECRYPT_MODE, encrPwd));
	}
	
	public static String encryptPassword(String password){
		String encrPwd= IClicksEncriptionDecription.doCypher(IClicksEncriptionDecription.ENCRYPT_MODE, password);
		return encrPwd;
	}
	
	public static String decryptPassword(String password){
		String decrPwd= IClicksEncriptionDecription.doCypher(IClicksEncriptionDecription.DECRYPT_MODE, password);
		return decrPwd;
	}
	
}

