package others;

import java.security.Key;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class JCE {

	private static final String ALGORITHM_AES = "AES";
	private static final String ALGORITHM_PBE = "PBEWithMD5AndDES";
	private static final String UTF_8         = "UTF-8";

	public static void main(String[] args) {
		normal();
		System.out.println("==================================");
		usePBE();
		System.out.println("==================================");
		useKeyByte();
		System.out.println("==================================");
		useBase64KeyByte();
		System.out.println("==================================");
		useBase64();
	}

	private static void normal() {
		// 此为动态加载的办法；
		// 静态加载方法为：编辑文件<java-home>\jre\lib\security\java.security
		// JDK1.4及以上版本已带JCE
		// Security.addProvider(new com.sun.crypto.provider.SunJCE());
		try {
			Key key = getKey();
			System.out.println("Key format: " + key.getFormat());
			System.out.println("Key algorithm: " + key.getAlgorithm());
			/*
			 * 产生加密器（Cipher） Cipher 在使用时需以参数方式指定 transformation transformation 的格式为
			 * algorithm/mode/padding，其中 algorithm 为必输项，如: DES/CBC/PKCS5Padding 缺省的 mode 为
			 * ECB，缺省的 padding 为 PKCS5Padding
			 */
			Cipher cipher = Cipher.getInstance(ALGORITHM_AES);

			byte[] data = "Bright".getBytes(UTF_8);
			System.out.println("Original data : " + new String(data, UTF_8));

			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] result = cipher.doFinal(data);
			System.out.println("Encrypted data: " + new String(result, UTF_8));

			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] original = cipher.doFinal(result);
			System.out.println("Decrypted data: " + new String(original, UTF_8));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void usePBE() {
		try {
			Key key = getPBEKey();
			System.out.println("Key format: " + key.getFormat());
			System.out.println("Key algorithm: " + key.getAlgorithm());

			Cipher cipher = Cipher.getInstance(ALGORITHM_PBE);

			byte[] data = "Bright".getBytes(UTF_8);
			System.out.println("Original data : " + new String(data, UTF_8));

			cipher.init(Cipher.ENCRYPT_MODE, key, getParamSpec());
			byte[] result = cipher.doFinal(data);
			System.out.println("Encrypted data: " + new String(result, UTF_8));

			cipher.init(Cipher.DECRYPT_MODE, key, getParamSpec());
			byte[] original = cipher.doFinal(result);
			System.out.println("Decrypted data: " + new String(original, UTF_8));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void useKeyByte() {
		try {
			byte[] keyByte  = getKeyByte();
			System.out.println(new String(keyByte, UTF_8));
			Key key = new SecretKeySpec(keyByte, ALGORITHM_AES);
			System.out.println("Key format: " + key.getFormat());
			System.out.println("Key algorithm: " + key.getAlgorithm());

			Cipher cipher = Cipher.getInstance(ALGORITHM_AES);

			byte[] data = "Bright".getBytes("UTF-8");
			System.out.println("Original data : " + new String(data, UTF_8));

			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] result = cipher.doFinal(data);
			System.out.println("Encrypted data: " + new String(result, UTF_8));

			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] original = cipher.doFinal(result);
			System.out.println("Decrypted data: " + new String(original, UTF_8));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void useBase64KeyByte() {
		try {
			byte[] keyByte  = getBase64KeyByte();
			System.out.println(new String(keyByte, "UTF-8"));
			Key key = new SecretKeySpec(keyByte, ALGORITHM_AES);
			System.out.println("Key format: " + key.getFormat());
			System.out.println("Key algorithm: " + key.getAlgorithm());

			Cipher cipher = Cipher.getInstance(ALGORITHM_AES);

			byte[] data = "Bright".getBytes("UTF-8");
			System.out.println("Original data : " + new String(data, UTF_8));

			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] result = cipher.doFinal(data);
			System.out.println("Encrypted data: " + new String(result, UTF_8));

			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] original = cipher.doFinal(result);
			System.out.println("Decrypted data: " + new String(original, UTF_8));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void useBase64() {
		try {
			byte[] data = "Bright".getBytes("UTF-8");
			System.out.println("Original data : " + new String(data, UTF_8));

			byte[] result = Base64.encodeBase64(data);
			System.out.println("Encrypted data: " + new String(result, UTF_8));

			byte[] original = Base64.decodeBase64(result);
			System.out.println("Decrypted data: " + new String(original, UTF_8));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static byte[] getBase64KeyByte() {
		byte[] key = null;
		try {
			return Base64.encodeBase64(KeyGenerator.getInstance(ALGORITHM_AES).generateKey().getEncoded());
		} catch (Exception e) {
			e.printStackTrace();
	}
		return key;
	}
	
	private static byte[] getKeyByte() {
		byte[] key = null;
		try {
			key =  KeyGenerator.getInstance(ALGORITHM_AES).generateKey().getEncoded();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return key;
	}

	private static Key getPBEKey() {
		char[] pwd = { '%', '_', 'A', 's', '9', 'K' };
		SecretKey pbeKey = null;
		PBEKeySpec pbeKeySpec = new PBEKeySpec(pwd);
		try {
			SecretKeyFactory keyFac = SecretKeyFactory.getInstance(ALGORITHM_PBE);
			pbeKey = keyFac.generateSecret(pbeKeySpec);
			return pbeKey;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			Arrays.fill(pwd, ' ');
		}
	}

	private static Key getKey() {
		try {
			/*
			 * 产生密钥(key)生成器生成密钥
			 * 算法名称	密钥长			块长		速度		说明
			 * DES		56				64		慢		不安全, 不要使用
			 * 3DES		112/168			64		很慢		中等安全, 适合加密较小的数据
			 * AES		128,192,256		128		快		安全
			 * Blowfish	4至56*8			64		快		应该安全, 在安全界尚未被充分分析、论证
			 * RC4		40-1024			64		很快		安全性不明确
			 * 一般情况下，不要选择DES算法，推荐使用AES算法。一般认为128bits的密钥已足够安全，如果可以请选择256bits的密钥
			 */
			KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM_AES);
			// 密钥长度是在生成密钥时指定的
			kg.init(256);
			Key key = kg.generateKey();
			return key;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static PBEParameterSpec getParamSpec() {
		byte[] salt = { (byte) 0xab, (byte) 0x58, (byte) 0xa1, (byte) 0x8c, (byte) 0x3e, (byte) 0xc8, (byte) 0x9d,
				(byte) 0x7a };
		int count = 20;
		PBEParameterSpec paramSpec = new PBEParameterSpec(salt, count);
		return paramSpec;
	}
}
