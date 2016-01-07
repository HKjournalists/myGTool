package myutil.string;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.*;
import javax.crypto.spec.*;

/**
 * 字符串加密，解密工具
 * @author Europe
 *
 */
public class AESUtils {

	static final String KEY_ALGORITHM = "AES";
	static final String CIPHER_ALGORITHM_ECB = "AES/ECB/PKCS5Padding";  
	
	
	public static String checkKey(String rawpassword) {
		
		int strLen=rawpassword.length();
		if (strLen>16) {
			rawpassword = rawpassword.substring(0, 16);
		} else {
			while (strLen<16) {
				StringBuffer  buffer=new StringBuffer();
				buffer.append(rawpassword).append("0");
				rawpassword=buffer.toString();
				strLen=rawpassword.length();
			}
		}
		return rawpassword;
	}


	/**
	 * 提供外部使用的解密方法
	 * @param cipherStirng
	 * @param rawpassword
	 * @return
	 */
	public static String decryptData(String cipherString,String rawpassword){
//		byte[] cipherBytes = toBytes(cipherString);
		byte[] cipherBytes = null;
		try {
			cipherBytes = cipherString.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		cipherBytes = Base64.decode(cipherBytes, Base64.);
		byte[] contentBytes = null;
		try {
			contentBytes = decrypt(cipherBytes,checkKey(rawpassword).getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String result = new String(contentBytes);
		return result;
	}
	
	
	
	
	/**
	 * 内部使用
	 * 解密
	 * @param content 待解密内容
	 * @param rawpassword  解密密钥
	 * @return
	 */
	public static byte[] decrypt(byte[] content, byte[] rawpassword) {
		try {
			//应该使用rawkey;
			SecretKeySpec secretKeySpec = new SecretKeySpec(rawpassword, "AES");
			
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_ECB);
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
			
			byte [] result = cipher.doFinal(content);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}




	/**
	 * 与 toBytes 成对使用  byte[] 转 String 
     * @param contentBytes 需要转换的byte数组
     * @return 返回contentBytes转换成的16进制字符串
     */
	private static String toHex(byte[] contentBytes) {
	    String HEX = "0123456789ABCDEF";
	    if (contentBytes == null)
	        return "";
	    StringBuffer result = new StringBuffer(contentBytes.length * 2);
	    for (int i = 0; i < contentBytes.length; i++) {
	        //byte数组的每个元素为8位，前四位right shift 4 后与 00001111与运算 ，后四位 直接与00001111与运算
	        result.append(HEX.charAt((contentBytes[i]>>4) & 0x0f)).append(HEX.charAt(contentBytes[i] & 0x0f));
	    }
	    return result.toString();
	}
	
	
	public static void main(String[] args) {
		AESUtils aesUtils = new AESUtils();
		byte[] decodeData = "asdfsd".getBytes();
		byte[] decryptData = aesUtils.decrypt(decodeData, "analysys00000000".getBytes());
	}
	
	
}
