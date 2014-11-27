package test;

import java.io.IOException;

import myutil.configutil.OZConfigTool;
import myutil.file.OZFileTool;

/**
 * 测试类
 * @author apple
 */
public class MainTest {

	public static void main(String[] args) throws IOException {
		//测试生成文件
		OZFileTool mft = new OZFileTool();
		mft.ozCreatFile("G:\\a.properties");
		
		//读取配置文件
//		MyConfigTool mct = new MyConfigTool("");
//		mct.ozGetValue(key)
		OZConfigTool mct2= new OZConfigTool();
		mct2.ozWriteProperties("G:\\a.properties", "a", "b");
	}
	
	
}
