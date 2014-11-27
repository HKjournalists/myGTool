package myutil.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则
 * @author Europe
 */
public class OZPattern {

	//获得所要匹配的内容
	public void getPattern(){
		
		Pattern p=Pattern.compile("(.*?)(促销对照表)");
	    String u = "QQ网购促销对照表";
	    Matcher a = p.matcher(u);
	    String sst = "";
	    while(a.find()){
		    sst = a.group(1);
	    }
		
	}
	
	
	
}
