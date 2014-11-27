package myutil.proxy;

/**
 * 代理，获取url的实例
 * @author Europe
 */
public class OZProxyServer {

	/**
	 * url监听的线程
	 * 
	 * @author Europe
	 */
	public class UrlThreed implements Runnable {

		public void run() {
			// 启动http监听
			System.out.println("在端口8888启动代理服务器\n");
			HttpProxy.log = System.out;
			HttpProxy.logging = false;
			HttpProxy.startProxy(8888, HttpProxy.class);
		}

	}
	
	
}
