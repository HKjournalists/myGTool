package rabbit_mq.exchange;

import java.io.IOException;  
import java.util.Date;  
  



import com.rabbitmq.client.Channel;  
import com.rabbitmq.client.Connection;  
import com.rabbitmq.client.ConnectionFactory;  
import com.rabbitmq.client.MessageProperties;
  
public class EmitLog  
{  
    private final static String EXCHANGE_NAME = "ex_log";  
    public static String QUEUE_IP = "192.168.0.118";
  
    public static void main(String[] args) throws IOException  
    {  
        // 创建连接和频道  
        ConnectionFactory factory = new ConnectionFactory();  
        factory.setHost(EmitLog.QUEUE_IP);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        // 声明转发器和类型
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");  
          
        String message = new Date().toLocaleString()+" : log something";  
        // 往转发器上发送消息  
        for(int i = 0; i<10; i++){
        	System.out.println("q");
//        	try {
//				Thread.sleep(1*1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
        	channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());  
//        	channel.basicPublish(EXCHANGE_NAME, "", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());  
        	System.out.println(" [x] Sent '" + message + "'");  
        }
  
//        System.out.println(" [x] Sent '" + message + "'");  
  
        channel.close();  
        connection.close();  
  
    }  
  
}  