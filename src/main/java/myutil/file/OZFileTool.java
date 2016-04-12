package myutil.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 操作文件的自定义工具类
 * 
 * @author Europe
 * @date 2014-3-31
 */
public class OZFileTool {

	private File file;

	/**
	 * 创建一个文件，需要传入完整的路径和文件名<br>
	 * 例如键值对的文件：startconfig.properties
	 * 
	 * @param fileName 路径/文件名
	 * @throws IOException
	 */
	public void ozCreatFile(String fileParthName) throws IOException {

		file = new File(fileParthName);
		file.createNewFile();

	}

	/**
	 * 创建一个文件，并向这个文件中写入内容。<br>
	 * 追加内容会换行<br>
	 * 
	 * @param fileParthName 文件及路径：/Users/apple/gaga.txt
	 * @param data 要存入的数据
	 * @param isAfter 是否追加到原来内容后面。true 追加，false 不追加。
	 * @throws Exception
	 * <br>
	 * <br>
	 *             例如要把一个数组要写入到文件中：
	 * 
	 *             <pre>
	 * int[] a = new int[] { 11112, 222, 333, 444, 555, 666 };
	 * for (int i = 0; i &lt; a.length; i++) {
	 * 	fileWriter.write(String.valueOf(a[i]) + &quot; &quot;);
	 * }
	 * </pre>
	 */
	public void ozWriteToFile(String fileParthName, String data, boolean isAfter)
			throws Exception {
		
		// FileWriter fileWriter=new FileWriter(fileParthName, true); //
		// true代表追加
		FileWriter fileWriter = new FileWriter(fileParthName, isAfter);
		
//		fileWriter.write(data + "\n");
		fileWriter.write(data );
		fileWriter.flush();
		fileWriter.close();

		System.out.println("文件内容写入完成。位置："+fileParthName+" pos"+data);

	}

	/**
	 * 读取文件的内容，编码格式为GBK
	 * 
	 * @param filePath
	 */
	public void ozReadFile(String filePath) {

		String contentStr = "";
		try {
			String encoding = "GBK";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				int count = 0;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					// System.out.println(lineTxt);
					if (ozFindContent(lineTxt)) {
						count++;
					}
				}
				System.out.println(count);
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}

	}

	/**
	 * 匹配所需要的内容
	 * 
	 * @param resorceStr
	 * @return
	 */
	public boolean ozFindContent(String resorceStr) {
		Pattern pattern = Pattern.compile("wgdata_taobao\\.product_info");
		Matcher m = pattern.matcher(resorceStr);
		boolean isFind = false;
		if (m.find()) {
			isFind = true;
		}

		return isFind;
	}

	/**
	 * 对文件进行重命名。
	 * @param oldPathName 旧的文件名及路径
	 * @param newPathName  新的文件名及路径
	 * <br>
	 * <pre>
	 * 逻辑流程：
	 *   1、获取旧文件路径。
	 *   2、如果旧文件存在，对其进行重命名。
	 * </pre>
	 */
	public void ozRenameFile(String oldPathName, String newPathName) {

		File file = new File(oldPathName);
		if (file.exists()) {
			file.renameTo(new File(newPathName));
		}
		
	}

	/**
	 * 传入一个文件，返回一个byte[] 数组
	 * 
	 * @param file 传入的文件，例如：new File("/tmp/urlData.txt")
	 * @return
	 * @throws Exception
	 */
	public byte[] ozGetBytesFromFile(File file) throws Exception {
		InputStream is = new FileInputStream(file);

		// 获取文件大小
		long length = file.length();

		if (length > Integer.MAX_VALUE) {
			// 文件太大，无法读取
			throw new Exception("File is to large " + file.getName());
		}

		// 创建一个数据来保存文件数据
		byte[] bytes = new byte[(int) length];

		// 读取数据到byte数组中
		int offset = 0;
		int numRead = 0;

		while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {

			offset += numRead;

		}

		// 确保所有数据均被读取
		if (offset < bytes.length) {

			throw new Exception("Could not completely read file "+ file.getName());

		}
		
		// Close the input stream and return bytes

		is.close();
		return bytes;

	}

	public static void main(String[] args) throws Exception {
		// readFile("C:\\My Work\\cache_data\\2014-05-06\\wgdata_taobao.product_info_2014050606_0.sql");

		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = df.format(new Date());
		
		OZFileTool ozft = new OZFileTool();
		ozft.ozWriteToFile("/Users/apple/gaga_"+dateStr+".txt", "asdfkjlsajkdflaksf", true);
		
		//file转为byte[]
//		byte[] fileByte = ozft.ozGetBytesFromFile(new File("/Users/apple/tmp/urlData.txt"));

	}

}
