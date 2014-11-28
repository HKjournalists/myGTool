package myutil.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则
 * @author Europe
 */
public class OZPattern {

	//获得所要匹配的内容
	public static void getPattern(String sourceStr, String patternStr){
		
//		Pattern p=Pattern.compile("(.*?)(促销对照表)");
//		String u = "QQ网购促销对照表";
		Pattern p=Pattern.compile(patternStr);
	    Matcher a = p.matcher(sourceStr);
	    String sst = "----";
	    while(a.find()){
//	    	System.out.println("???");
		    sst = a.group(1);
	    }
	    System.out.println(sst);
	}
	
	
	//测试
	public static void main(String[] args) {
		
		getPattern("waga哈哈12.11sefs", "(\\d+\\.?\\d+).*?");
		
	}
	
}
