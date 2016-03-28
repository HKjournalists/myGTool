package myutil.linux;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class ExcLinuxCommand {
	public static void main(String[] args) {
//		System.out.println(DateUtil3.getBeforeNDayofThisDate(1));
		
		System.out.println("aaa");
		Process ps = null;
		String[] cmd = {"/bin/sh", "-c","java -jar /Users/apple/贾维斯/jarvis2.0-otherdayData.jar"};
		try {
			ps = Runtime.getRuntime().exec(cmd);
//			System.out.println(ps);
			InputStreamReader ir = new InputStreamReader(ps.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
 
            String line;
            while ((line = input.readLine()) != null) {//输出结果
                System.out.println(line);
            }
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("bbbbbbbb");
		
		
		
		
		
		
		
	}
}
