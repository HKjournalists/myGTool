package myutil.string;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class OZGzip {

	/**
	 * 
	 * 使用gzip进行压缩
	 */
	public static String gzip(String primStr) {
		if (primStr == null || primStr.length() == 0) {
			return primStr;
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		GZIPOutputStream gzip = null;
		try {
			gzip = new GZIPOutputStream(out);
			gzip.write(primStr.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (gzip != null) {
				try {
					gzip.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return new sun.misc.BASE64Encoder().encode(out.toByteArray());
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:使用gzip进行解压缩
	 * </p>
	 * 
	 * @param compressedStr
	 * @return
	 */
	public static String gunzip(String compressedStr) {
		if (compressedStr == null) {
			return null;
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = null;
		GZIPInputStream ginzip = null;
		byte[] compressed = null;
		String decompressed = null;
		try {
			compressed = new sun.misc.BASE64Decoder()
					.decodeBuffer(compressedStr);
			in = new ByteArrayInputStream(compressed);
			ginzip = new GZIPInputStream(in);

			byte[] buffer = new byte[1024];
			int offset = -1;
			while ((offset = ginzip.read(buffer)) != -1) {
				out.write(buffer, 0, offset);
			}
			decompressed = out.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ginzip != null) {
				try {
					ginzip.close();
				} catch (IOException e) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}

		return decompressed;
	}
	
	public static void main(String[] args) {
		
		String str = "{\"DevInfo\": {\"DI\": \"860157010327119-ewrfsdfsdfsdfsdf-b42e03d9cc43ff4c\",\"DB\": \"联想\",\"DM\": \"Lenovo S760\",\"SV\": \"2.3.4\",\"AVC\": \"1.3.4|89\",\"AK\": \"sdfsdfsdfgsdfg\",\"AC\": \"360\",\"AUI\": \"657893\",\"SDKV\": \"150208\",\"APILevel\": \"8\",\"MP\": \"联通\",\"PN\": \"13661190997\"},\"NPLInfo\": {\"NT\":\"2142343-3G|2142343-4G\",\"GL\": \"2142343-123-456|2142389-127-456\",\"PL\":\"1296035800-1296035890|1296035890-1296035900\"},\"IUUInfo\": [{\"APN\": \"com.qzone\",\"AN\": \"QQ空间\",\"AVC\": \"5.4.1|89\",\"AT\": \"1\",\"AHT\": \"1296035800\"}],\"OCInfo\": [{\"AOT\": \"1296035591\",\"ACT\": \"1296035599\",\"APN\": \"com.qzone\",\"AN\": \"QQ空间\",\"AVC\": \"5.4.1|89\"}],\"WebInfo\": [{\"URL\": \"http://www.baidu.com\",\"WOT\": \"1296035591\"}],\"InstalledAppInfo\": [{\"APN\": \"com.qzone\",\"AN\": \"QQ空间\",\"AVC\": \"5.4.1|89\",\"IN\": \"1\"}]}";
		String zip = OZGzip.gzip(str);
		System.out.println(zip);
		System.out.println("str length : "+str.length());
		System.out.println("zip length : "+zip.length());
//		System.out.println(OZGzip.gunzip(zip));
	}
	
}
