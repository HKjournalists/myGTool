package myutil.configutil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * 读取配置文件个工具。<br>
 * 1、初始化加载配置文件的路径<br>
 * 2、调用getValue()根据key获得value<br>
 * ps:所以方法都以oz开头
 * 
 * @author Europe
 * @date 2014-3-27
 */
public class OZConfigTool {

	private Properties properties;
	private FileInputStream inputFile;

	/**
	 * 加载本地文件
	 * 
	 * @param filePath
	 *            文件存储路径
	 */
	public OZConfigTool(String filePath) {
		properties = new Properties();
		try {
			inputFile = new FileInputStream(filePath);
			properties.load(inputFile);
			inputFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("读取属性文件--->失败！- 原因：文件路径错误或者文件不存在");
			e.printStackTrace();
		}

	}
	
	/**
	 * 无参构造器
	 */
	public OZConfigTool() {
		
	}

	/**
	 * 根据key获得value
	 * 
	 * @param key
	 * @return
	 */
	public String ozGetValue(String key) {
		if (properties.containsKey(key)) {
			String value = properties.getProperty(key);
			return value;
		} else {
			return "null";
		}
	}

	/**
	 *  写入properties信息
	 * @param filePath
	 * @param parameterName
	 * @param parameterValue
	 */
	public void ozWriteProperties(String filePath, String parameterName,
			String parameterValue) {
		Properties prop = new Properties();
		try {
			InputStream fis = new FileInputStream(filePath);
			// 从输入流中读取属性列表（键和元素对）
			prop.load(fis);
			// 调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
			// 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
			OutputStream fos = new FileOutputStream(filePath);
			prop.setProperty(parameterName, parameterValue);
			// 以适合使用 load 方法加载到 Properties 表中的格式，
			// 将此 Properties 表中的属性列表（键和元素对）写入输出流
			prop.store(fos, "Update '" + parameterName + "' value");
			
			fis.close();
			fos.close();
			
		} catch (IOException e) {
			System.err.println("Visit " + filePath + " for updating "
					+ parameterName + " value error");
		}
	}

	public static void main(String[] args) {
		OZConfigTool myConfigTool = new OZConfigTool("./mmconfig.properties");

		System.out.println(myConfigTool.ozGetValue("enterdataips"));
	}

}
