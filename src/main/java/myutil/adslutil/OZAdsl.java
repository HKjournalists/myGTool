package myutil.adslutil;

import java.io.IOException;

/**
 * adls 的工具类<br>
 * 方法都是以oz开头
 * @author apple
 */
public class OZAdsl {

	/**
	 * 连接ADSL
	 * @param adsl_name ADSL的用户名
	 * @param adsl_pwd ADSL的密码
	 */
	public void ozStartAdsl(String adsl_name, String adsl_pwd ){
		
		//开始重启ad
        try {
        	Process pr;
			pr = Runtime.getRuntime().exec("rasdial.exe ADSL "+adsl_name+" "+adsl_pwd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
	}
	
	
	
}
