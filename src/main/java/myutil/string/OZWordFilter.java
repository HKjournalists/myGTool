package myutil.string;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;



/**
 * 文字过滤：<br>
 * 把不和谐的文字替换成*。<br>
 * 需要用到servlet-api.jar（现已加入pom中）<br>
 * @author apple
 */
public class OZWordFilter {

	/**
	 * 用来储存需要被过滤的词
	 */
	private static List<String> unString;
	
	/**
	 * 过滤句子
	 * @param badWord	需要被过滤的不和谐词，词与词之间用逗号隔开。
	 * @param sourceStr	有不和谐词的数据源。
	 */
	public static void ozReplace(String badWord,String sourceStr){
		/*
		 * 	StringTokenizer是一个用来分隔String的应用类，相当于VB的split函数。
			1.构造函数
			public StringTokenizer(String str)
			public StringTokenizer(String str, String delim)
			public StringTokenizer(String str, String delim, boolean returnDelims)
			第一个参数就是要分隔的String，第二个是分隔字符集合，第三个参数表示分隔符号是否作为标记返回，如果不指定分隔字符，默认的是：”\t\n\r\f”
		 */
		unString = new ArrayList<String>();
//		String aa = "日,tmd,TMD,滚蛋,系统";
		StringTokenizer st = new StringTokenizer(badWord, ",");
		//对st进行遍历
		while (st.hasMoreElements()) {
			unString.add(st.nextElement().toString());
		}
		
		System.out.println("敏感词："+unString);
		
		String content = sourceStr;//需要过滤的参数
		if(content != null){
			for(int i = 0; i<unString.size(); i++){
				String strIllegal = unString.get(i);
				if(content.indexOf(strIllegal) >= 0){
					content = content.replaceAll(strIllegal, "*");//非法字符替换成*
				}
			}
		}
		
		System.out.println("过滤后的结果："+content);
		
	}
	
	
	

	public static void main(String[] args) {
		
		OZWordFilter filter = new OZWordFilter();
		filter.ozReplace("日,tmd,TMD,滚蛋,系统","asdlkf就离开tmd谁都会日说");
		
	}


	
	
	
	
	
	
	
	
	
	
	
	
}
