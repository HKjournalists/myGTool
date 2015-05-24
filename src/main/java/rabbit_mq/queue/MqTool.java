package rabbit_mq.queue;

import java.io.IOException;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;

/**
 * 将消息直接发送给消息队列
 * @author Europe
 */
public class MqTool {

//	static Logger loggerSdk = Logger.getLogger("sdk");
	/**
	 * 往 mq 发送一条消息
	 * @throws IOException 
	 */
	public void sendToMq(String jsonData,String mqName) {
		
		try {
			/**
			 * 创建连接连接到MabbitMQ
			 */
			ConnectionFactory factory = new ConnectionFactory();
			// 设置MabbitMQ所在主机ip或者主机名
			factory.setHost("192.168.1.47");
//			factory.setHost(ConstantTool.QUEUE_IP);
//			factory.setPort(port);
			// 创建一个连接
			Connection connection;
			connection = factory.newConnection();//如果mq服务器有问题，程序运行到这里会无法继续下去。
			// 创建一个频道
			Channel channel = connection.createChannel();
			// 指定一个队列
			channel.queueDeclare(mqName, false, false, false, null);
			//将字符串发送到队列
			channel.basicPublish("", mqName, null, jsonData.getBytes());
			
//			loggerSdk.info("[X] Sent "+jsonData+" success !!!");
			
			channel.close();
			connection.close();
			
		} catch (IOException e) {
//			loggerSdk.info("MQ exc : "+e.getMessage()+"\n"+"MQ exc : "+e);
			e.printStackTrace();
		}
		
		
	}
	
	

	
	public static void main(String[] args) {
//		MqTool mt = new MqTool();
//		try {
//			mt.sendToMq("adsfas");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
}
