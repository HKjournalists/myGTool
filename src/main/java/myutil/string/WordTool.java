//package myutil.string;
//
//public class WordTool {
//
//	/**
//	 * ����������ʾ
//	 * @param str ��������
//	 * @param speed �ٶ�
//	 */
//	public static void wordShow(String str,int speed){
//		for(int i = 0; i<str.length(); i++){
//			System.out.print(str.substring(i,i+1));
//			try {
//				Thread.sleep(speed);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			if(i+2>str.length()){
//				System.out.println();
//				try {
//					Thread.sleep(StaticTool.WORDSHOW_ONESTRING_SPEED);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//			
//		}
//	}
//	
//	/**
//	 * ����������ʾ,Ĭ���ٶ�Ϊ 30mm
//	 * @param str ��������
//	 */
//	public static void wordShow(String str){
//		for(int i = 0; i<str.length(); i++){
//			System.out.print(str.substring(i,i+1));
//			try {
//				Thread.sleep(StaticTool.WORDSHOW_WORD_SPEED);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			if(i+2>str.length()){
//				System.out.println();
//				try {
////					Thread.sleep(StaticTool.WORDSHOW_ONESTRING_SPEED);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//			
//		}
//	}
//	
//}
