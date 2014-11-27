package myutil.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * 一个建立socket服务端的实例
 * @author Europe
 */
public class OZSocketTool {

	public static void main(String[] args) {
		//启动方式
		new Thread(new OZSocketTool().new StartSocketThreed()).start();
	}
	
	
	
	/**
	 * 用来启动 socket 线程的线程
	 * 
	 * @author Europe
	 */
	class StartSocketThreed implements Runnable {

		public void run() {
			// 启动socket
			try {
				System.out.println("启动socket监听");
				ServerSocket s;
				s = new ServerSocket(8000);
				OZSocketTool mt = new OZSocketTool();
				while (true) {
					Socket incoming = s.accept();
					new Thread(mt.new socketThreed(incoming)).start();// 启动socket线程
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	/**
	 * 1、启动socket线程服务<br>
	 * 2、接到请求开始截图
	 * 
	 * @author Europe
	 */
	class socketThreed implements Runnable {

		private Socket incoming;

		/**
		 * 1、启动socket线程服务<br>
		 * 2、接到请求开始截图
		 * 
		 * @param i
		 */
		public socketThreed(Socket i) {
			incoming = i;
		}

		public void run() {

			try {
				System.out.println("已接收一个客户端请求");
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
	
	
	
}
