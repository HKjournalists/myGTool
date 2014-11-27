package myutil.date;

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
	 * 													  2、yyyyMMddHHmmss
	 * @return 字符串类型
	 */
	public String ozGetDate(String dateForm){
		SimpleDateFormat df=new SimpleDateFormat(dateForm);
		String dateStr = df.format(new Date());
		return dateStr;
	}
	
}
