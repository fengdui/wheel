package com.fengdui.wheel.encrypt;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;

public class EncryptUtil {

	public static final String MD2 = "MD2";
	public static final String MD5 = "MD5";
	public static final String SHA1 = "SHA-1";
	public static final String SHA256 = "SHA-256";
	public static final String SHA384 = "SHA-384";
	public static final String SHA512 = "SHA-512";


	public static String encryptBySHA1(String origin) {
		return encrypt(origin, SHA1);
	}

	public static String encrypt(String origin, String algorithm) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.reset();
			messageDigest.update(origin.getBytes());
			byte[] res = messageDigest.digest();
			return byte2Hex(res);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将byte数组转换为16进制表示
	 */
	private static String byte2Hex(byte[] byteArray) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
				sb.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			} else {
				sb.append(Integer.toHexString(0xFF & byteArray[i]));
			}

		}
		return sb.toString();
	}

	public static String encodeFileId(String fileId) {
		return new String(Base64.encodeBase64(fileId.getBytes()));
	}

	public static String decodeFileId(String fileId) {
		return new String(Base64.decodeBase64(fileId));
	}

	public static void main(String[] args) {
		System.out.println(encryptBySHA1("123456"));
	}

}
