package others;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Key;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class EncryptAndDecryptFile {

	// private static String key = "Bright.Bi";
	private static String algorithmPBE = "PBEWithMD5AndDES";
	private static String encoding = "UTF-8";
	private static String originalFile = "/Users/mingliangbi/Downloads/pl-PL/test.txt";
	private static String encryptedFile = "/Users/mingliangbi/Downloads/pl-PL/encrypted-test.txt";
	private static String decryptedFile = "/Users/mingliangbi/Downloads/pl-PL/decrypted-test.txt";

	public static void main(String[] args) {
		// readDesignatedLengthContent(15);
		// readAllContent();
		// encryptByFileInputStream(originalFile, encryptedFile);
		decryptByFileInputStream(encryptedFile, decryptedFile);
		// encryptByFileOutputStream(originalFile, encryptedFile);
		// decryptByFileOutputStream(encryptedFile, decryptedFile);
	}

	public static void decryptByFileOutputStream(String inPath, String outPath) {
		FileInputStream in = null;
		FileOutputStream out = null;
		CipherOutputStream cout = null;
		byte[] cache = new byte[1024];

		try {
			Cipher cipher = Cipher.getInstance(algorithmPBE);
			cipher.init(Cipher.DECRYPT_MODE, getPBEKey(), getParamSpec());
			in = new FileInputStream(inPath);
			out = new FileOutputStream(outPath);
			cout = new CipherOutputStream(out, cipher);
			int length = 0;
			while ((length = in.read(cache)) != -1) {
				cout.write(cache, 0, length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (cout != null) {
				try {
					cout.flush();
					cout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Finish !");
	}

	public static void encryptByFileOutputStream(String inPath, String outPath) {
		FileInputStream in = null;
		FileOutputStream out = null;
		CipherOutputStream cout = null;
		byte[] cache = new byte[1024];

		try {
			Cipher cipher = Cipher.getInstance(algorithmPBE);
			cipher.init(Cipher.ENCRYPT_MODE, getPBEKey(), getParamSpec());
			in = new FileInputStream(inPath);
			// 创建要加密的输出流
			out = new FileOutputStream(outPath);
			// 再由密码器输出流把输出流和密码器包装
			cout = new CipherOutputStream(out, cipher);
			int length = 0;
			// 读取输入流
			while ((length = in.read(cache)) != -1) {
				cout.write(cache, 0, length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					cout.flush();
					cout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Finish !");
	}

	public static void decryptByFileInputStream(String inPath, String outPath) {
		FileInputStream in = null;
		CipherInputStream cin = null;
		FileOutputStream out = null;
		byte[] cache = new byte[1024];

		try {
			Cipher cipher = Cipher.getInstance(algorithmPBE);
			cipher.init(Cipher.DECRYPT_MODE, getPBEKey(), getParamSpec());
			// 创建要加密的输入流
			in = new FileInputStream(inPath);
			// 再由密码器输入流把输入流和密码器包装
			cin = new CipherInputStream(in, cipher);
			out = new FileOutputStream(outPath);
			int length = 0;
			// 读取输入流
			while ((length = cin.read(cache)) != -1) {
				out.write(cache, 0, length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Finish !");
	}

	public static void encryptByFileInputStream(String inPath, String outPath) {
		FileInputStream in = null;
		CipherInputStream cin = null;
		FileOutputStream out = null;
		byte[] cache = new byte[1024];

		try {
			Cipher cipher = Cipher.getInstance(algorithmPBE);
			cipher.init(Cipher.ENCRYPT_MODE, getPBEKey(), getParamSpec());
			// 创建要加密的输入流
			in = new FileInputStream(inPath);
			// 再由密码器输入流把输入流和密码器包装
			cin = new CipherInputStream(in, cipher);
			out = new FileOutputStream(outPath);
			int length = 0;
			// 读取输入流
			while ((length = cin.read(cache)) != -1) {
				out.write(cache, 0, length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Finish !");
	}

	public static void readAllContent() {
		File file = new File(originalFile);
		Long fileLength = file.length();
		FileInputStream in = null;
		byte[] fileContent = new byte[fileLength.intValue()];

		try {
			System.out.println("File Length:" + fileLength);
			in = new FileInputStream(file);
			in.read(fileContent);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		usePBE(fileContent);
	}

	/*
	 * 如果文本内容是： 123 aaa
	 * 
	 * 789 你好 0 参数 length 设置为 15 会出现乱码
	 */
	public static void readDesignatedLengthContent(int length) {
		File file = new File(originalFile);
		FileInputStream in = null;
		byte[] fileContent = new byte[length];

		try {
			in = new FileInputStream(file);
			int len;
			while ((len = in.read(fileContent)) != -1) {
				String str = new String(fileContent, 0, len);
				System.out.print(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void usePBE(byte[] fileContent) {
		try {
			Key key = getPBEKey();
			Cipher cipher = Cipher.getInstance(algorithmPBE);
			System.out.println("================Original data================");
			System.out.println(new String(fileContent, encoding));

			cipher.init(Cipher.ENCRYPT_MODE, key, getParamSpec());
			byte[] result = cipher.doFinal(fileContent);
			System.out.println("================Encrypted data================");
			System.out.println(new String(result, encoding));

			cipher.init(Cipher.DECRYPT_MODE, key, getParamSpec());
			byte[] original = cipher.doFinal(result);
			System.out.println("================Decrypted data================");
			System.out.println(new String(original, encoding));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Key getPBEKey() {
		char[] pwd = { '%', '_', 'A', 's', '9', 'K' };
		SecretKey pbeKey = null;
		PBEKeySpec pbeKeySpec = new PBEKeySpec(pwd);
		try {
			SecretKeyFactory keyFac = SecretKeyFactory.getInstance(algorithmPBE);
			pbeKey = keyFac.generateSecret(pbeKeySpec);
			return pbeKey;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			Arrays.fill(pwd, ' ');
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
