package myutil.log;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogIntoFile {

	/**
	 * 
	 * @param data
	 * @param path /home/storm/skuNormalizeStorm_Log/stormLog_
	 * @throws IOException
	 */
	public static void logMQMessage(String data,String path){
		try {
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateStr = df.format(new Date());
			SimpleDateFormat df2=new SimpleDateFormat("yyyy_MM_dd");
			String dateStr2 = df2.format(new Date());
			
			String logPath = path+dateStr2+".txt";
			
			FileWriter fileWriter = new FileWriter(logPath, true);
			fileWriter.write("["+dateStr+ "]\n"+data + "\n");
			fileWriter.flush();
			fileWriter.close();
			System.out.println(data);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
}
