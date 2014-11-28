package myutil.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 获取网页内容
 * @author Europe
 */
public class PlayWithWeb {

	

	/**
	 * 获取 html 源码
	 * @param pageURL
	 * @param encoding
	 * @return
	 */
	public static String ozGetHTML(String pageURL, String encoding) {

		StringBuilder pageHTML = new StringBuilder();

		try {

			URL url = new URL(pageURL);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			connection.setRequestProperty("User-Agent", "MSIE 7.0");

			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), encoding));

			String line = null;

			while ((line = br.readLine()) != null) {

				pageHTML.append(line);
				pageHTML.append("\r\n");

			}

			connection.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pageHTML.toString();

	}
	
	
	//测试
	public static void main(String[] a) throws IOException {

		String url = "http://www.baidu.com";

		System.out.println(ozGetHTML(url, "utf-8")); // 使用原网页里声明的gb2312反而会出现乱码

	}
	

}
