package test;

import java.io.IOException;

import myutil.configutil.OZConfigTool;
import myutil.file.OZFileTool;

/**
 * ������
 * @author apple
 */
public class MainTest {

	public static void main(String[] args) throws IOException {
		//���������ļ�
		OZFileTool mft = new OZFileTool();
		mft.ozCreatFile("G:\\a.properties");
		
		//��ȡ�����ļ�
//		MyConfigTool mct = new MyConfigTool("");
//		mct.ozGetValue(key)
		OZConfigTool mct2= new OZConfigTool();
		mct2.ozWriteProperties("G:\\a.properties", "a", "b");
	}
	
	
}
