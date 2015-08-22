package com.ideaclicks.liferay.spring.util;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncriptionToDecription
{

	public static final String DES_ENCRYPTION_SCHEME_PADDING = "DES/ECB/PKCS7Padding";
	public static final String DES_ENCRYPTION_SCHEME = "DES";
	public static final String			DEFAULT_ENCRYPTION_KEY	= "This is a fairly long phrase used to encrypt";
	public static final byte[] keyAsBytes =   new byte[] { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef };
	private Cipher				cipher;
	private static final String	UNICODE_FORMAT			= "UTF8";

	byte[] keyBytes = new byte[] { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd,
			(byte) 0xef };
	SecretKeySpec key  = null;

	public EncriptionToDecription( String encryptionScheme )
			throws EncryptionException
			{
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());        
		try
		{
			key = new SecretKeySpec(keyBytes, DES_ENCRYPTION_SCHEME);
			cipher = Cipher.getInstance(DES_ENCRYPTION_SCHEME_PADDING, "BC");
		}
		catch (NoSuchAlgorithmException e)
		{
			throw new EncryptionException( e );
		}
		catch (NoSuchPaddingException e)
		{
			throw new EncryptionException( e );
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
			}

	public String encrypt1( String unencryptedString ) throws EncryptionException
	{
		String outputString = "";
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] cleartext = unencryptedString.getBytes( UNICODE_FORMAT );
			byte[] ciphertext = cipher.doFinal( cleartext );

			BASE64Encoder base64encoder = new BASE64Encoder();
			return base64encoder.encode( ciphertext );
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return outputString;
	}

	public String decrypt1( String encryptedString ) throws Exception
	{
		try {
			cipher.init(Cipher.DECRYPT_MODE, key);
			BASE64Decoder base64decoder = new BASE64Decoder();
			byte[] cleartext = base64decoder.decodeBuffer( encryptedString );
			byte[] ciphertext = cipher.doFinal( cleartext );
			return new String( ciphertext );
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();throw e;
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();throw e;
		} catch (BadPaddingException e) {
			e.printStackTrace();throw e;
		}

	}
	public static  String decrypt( String encryptedString ) throws Exception
	{
		EncriptionToDecription encriptionToDecription = new EncriptionToDecription(EncriptionToDecription.DES_ENCRYPTION_SCHEME);
		return encriptionToDecription.decrypt1(encryptedString);

	}
	public static String encrypt( String unencryptedString ) throws EncryptionException
	{
		EncriptionToDecription encriptionToDecription = new EncriptionToDecription(EncriptionToDecription.DES_ENCRYPTION_SCHEME);
		return encriptionToDecription.encrypt1(unencryptedString);

	}
	public static class EncryptionException extends Exception
	{
		public EncryptionException( Throwable t )
		{
			super( t );
		}
	}
}

