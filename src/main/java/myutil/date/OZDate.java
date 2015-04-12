package myutil.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 和时间有关的类
 * @author Europe
 */
public class OZDate {

	/**
	 * 获得时间的方法
	 * @param dateForm 时间格式。例如1、yyyy-MM-dd HH:mm:ss<br>
	 * 													  2、yyyyMMddHHmmssSSSS 大S表示毫秒
	 * @return 字符串类型
	 */
	public static String ozGetDate(String dateForm){
		SimpleDateFormat df=new SimpleDateFormat(dateForm);
		String dateStr = df.format(new Date());
		return dateStr;
	}
	
	/**
	 * 将 String 类型的时间转化为 Date 类型<br>
	 * ps:参考地址  http://liulinxia02.blog.163.com/blog/static/2686877201142322741461/
	 * @date 2014-12-4
	 */
	public static void ozStrToDate(){
		try {
			String sDt = "12/28/2014";
			SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy");
			Date dt2 = sdf.parse(sDt);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 将date转为long类型
	 * @date 2014-12-4
	 */
	public static void ozDateToLong(){
		Date today = new Date();
		long time = today.getTime();
	}
	
	public static void ozGetHour(){
		Date d = new Date();
		int hours = d.getHours();
		System.out.println(hours);
	}
	
	public static void main(String[] args) {
		System.out.println(ozGetDate("yyyy-MM-dd HH:mm:ss"));
//		ozGetHour();
	}
}
