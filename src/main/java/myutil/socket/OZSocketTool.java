package myutil.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 一个建立socket服务端的实例
 * 
 * @author Europe
 */
public class OZSocketTool {

	/*
	 * ********* Socket 精要所在 **********
	 * 
	 * Socket socket = new Socket("192.168.0.59", 3000);
	 * 
	 * PrintWriter pw = new PrintWriter(new
	 * BufferedWriter(newOutputStreamWriter(
	 * socket.getOutputStream())),true);//构建向服务端请求对象 pw.println(“str”);//向服务端发送
	 * 请求 str
	 * 
	 * BufferedReader br = new
	 * BufferedReader(newInputStreamReader(socket.getInputStream
	 * (),"GBK"));//读入对象
	 * 
	 * String data = br.readLine();//获得服务端返回的字符串
	 */

	public static void main(String[] args) {
		// 启动方式
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
