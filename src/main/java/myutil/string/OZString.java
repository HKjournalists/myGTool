package myutil.string;

/**
 * 对String用法的一些总结
 * @author Europe
 */
public class OZString {

	/**
	 * int indexOf(int ch)   返回指定字符在此字符串中第一次出现处的索引。 
	 * int indexOf(int ch, int fromIndex) 从指定的索引开始搜索，返回在此字符串中第一次出现指定字符处的索引。 
	 * int indexOf(String str) 返回第一次出现的指定子字符串在此字符串中的索引。 
	 * int indexOf(String str, int fromIndex) 从指定的索引处开始，返回第一次出现的指定子字符串在此字符串中的索引。 
	 */
	public void ozStrigIndexOf(){
		
		String str = "wgdata_360buy_product_info";
		int a = str.indexOf("promotion_info");
		System.out.println(a);
		
	}
	
	/*
	 * String s = "qqqq=wwww=eee=rrrrrrrr"
	 * s.split("=",2);以＝为分割，分成两部分
	 * 
	 */
	
	
	
	public static void main(String[] args) {
		new OZString().ozStrigIndexOf();
	}
	
}
