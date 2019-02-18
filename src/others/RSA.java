package others;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
/*
 * RSA加解密:
 * RSA加密明文和解密密文，对明文和密文长度是有要求的，如果超过长度就需要分块进行，使用Java RSA 分段加解密。
 * 
 * Java RSA 分段加解密:
 * 1024位的证书，加密时最大支持117个字节，解密时为128；
 * 2048位的证书，加密时最大支持245个字节，解密时为256。
 * 加密时支持的最大字节数：证书位数/8 -11（比如：2048位的证书，支持的最大加密字节数：2048/8 - 11 = 245）
 */
public class RSA {

	public static final String KEY_ALGORITHM = "RSA";
	private static final String PUBLIC_KEY = "RSAPublicKey";
	private static final String PRIVATE_KEY = "RSAPrivateKey";
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
	private static final String UTF_8 = "UTF-8";
	/**
	 * RSA最大加密明文大小
	 */
	private static final int MAX_ENCRYPT_BLOCK = 117;

	/**
	 * RSA最大解密密文大小
	 */
	private static final int MAX_DECRYPT_BLOCK = 128;

	public static void main(String[] args) {
		Map<String, Object> keyMap;
		byte[] cipherText;
		String input = "你好 World!";
		try {
			keyMap = initKey();
			String publicKey = getPublicKeyStr(keyMap);
			System.out.println("公钥------------------");
			System.out.println(publicKey);
			String privateKey = getPrivateKeyStr(keyMap);
			System.out.println("私钥------------------");
			System.out.println(privateKey);

			System.out.println("测试可行性-------------------");
			System.out.println("明文=======" + input);

			cipherText = encrypt(input.getBytes(), publicKey);
			// 加密后的东西
			System.out.println("密文=======" + new String(cipherText));
			// 开始解密
			byte[] plainText = decrypt(cipherText, privateKey);
			System.out.println("解密后明文===== " + new String(plainText));

			System.out.println("验证签名-----------");
			String str = "被签名的内容";
			System.out.println("\n原文:" + str);
			byte[] signature = sign(str.getBytes(), privateKey);
			boolean status = verify(str.getBytes(), signature, publicKey);
			System.out.println("验证情况：" + status);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// RSA 加密
	public static byte[] encrypt(byte[] plainText, String publicKeyStr) throws Exception {
		PublicKey publicKey = getPublicKey(publicKeyStr);
		Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		int inputLen = plainText.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		int i = 0;
		byte[] cache;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(plainText, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(plainText, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptText = out.toByteArray();
		out.close();
		return encryptText;
	}

	// RSA 解密
	public static byte[] decrypt(byte[] encryptText, String privateKeyStr) throws Exception {
		PrivateKey privateKey = getPrivateKey(privateKeyStr);
		Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		int inputLen = encryptText.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(encryptText, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(encryptText, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] plainText = out.toByteArray();
		out.close();
		return plainText;
	}

	// 获取公钥
	public static PublicKey getPublicKey(String key) throws Exception {
		byte[] keyBytes;
		keyBytes = Base64.decodeBase64(key);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	// 获取私钥
	public static PrivateKey getPrivateKey(String key) throws Exception {
		byte[] keyBytes;
		keyBytes = Base64.decodeBase64(key);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}

	// 获得私钥字符串
	public static String getPrivateKeyStr(Map<String, Object> keyMap) throws Exception {
		// 获得map中的私钥对象 转为key对象
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		// 编码返回字符串
		return new String(Base64.encodeBase64(key.getEncoded()), UTF_8);
	}

	// 获得公钥字符串
	public static String getPublicKeyStr(Map<String, Object> keyMap) throws Exception {
		// 获得map中的公钥对象 转为key对象
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		// 编码返回字符串
		return new String(Base64.encodeBase64(key.getEncoded()), UTF_8);
	}

	public static Map<String, Object> initKey() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(1024);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;
	}

	public static byte[] sign(byte[] data, String privateKeyStr) throws Exception {
		PrivateKey priK = getPrivateKey(privateKeyStr);
		Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
		sig.initSign(priK);
		sig.update(data);
		return sig.sign();
	}

	public static boolean verify(byte[] data, byte[] sign, String publicKeyStr) throws Exception {
		PublicKey pubK = getPublicKey(publicKeyStr);
		Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
		sig.initVerify(pubK);
		sig.update(data);
		return sig.verify(sign);
	}

}
