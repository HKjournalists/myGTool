package jarvis.util;

public class WordTool {

	/**
	 * 文字逐字显示
	 * @param str 文字内容
	 * @param speed 速度
	 */
	public static void wordShow(String str,int speed){
		for(int i = 0; i<str.length(); i++){
			System.out.print(str.substring(i,i+1));
			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(i+2>str.length()){
				System.out.println();
				try {
					Thread.sleep(StaticTool.WORDSHOW_ONESTRING_SPEED);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	/**
	 * 文字逐字显示,默认速度为 30mm
	 * @param str 文字内容
	 */
	public static void wordShow(String str){
		for(int i = 0; i<str.length(); i++){
			System.out.print(str.substring(i,i+1));
			try {
				Thread.sleep(StaticTool.WORDSHOW_WORD_SPEED);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(i+2>str.length()){
				System.out.println();
				try {
					Thread.sleep(StaticTool.WORDSHOW_ONESTRING_SPEED);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
}
