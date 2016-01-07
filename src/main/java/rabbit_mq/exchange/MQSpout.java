package rabbit_mq.exchange;

import java.io.IOException;
import java.util.Map;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

/**
 * Storm 中用来获取消息的 spout 对 rabbit mq 的使用
 * @author Europe
 */
public class MQSpout implements IRichSpout {

	private SpoutOutputCollector collector;
	private final static String EXCHANGE_NAME = "ex_log";
	ConnectionFactory factory;
	Connection connection;
	Channel channel;
	QueueingConsumer consumer;
	
	public void open( Map conf, TopologyContext context,
			SpoutOutputCollector collector) {
		
		factory = new ConnectionFactory();
		factory.setHost("192.168.0.118");
		factory.setPort(5672);
		
		try {
			connection = factory.newConnection();
			channel = connection.createChannel();
//			channel.basicQos(10);//设置一次接收的消息数
			
			
			channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
			// 创建一个非持久的、唯一的且自动删除的队列
//			String queueName = channel.queueDeclare().getQueue();
			String queueName = "tttttttttttt";
			channel.queueDeclare(queueName, false, false, false, null);
			// 为转发器指定队列，设置binding
			channel.queueBind(queueName, EXCHANGE_NAME, "");

			System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
			
			consumer = new QueueingConsumer(channel);
			// 指定接收者，第二个参数为自动应答，无需手动应答
			channel.basicConsume(queueName, true, consumer);
			
			this.collector = collector;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("----------");
			e.printStackTrace();
		}
		
//		final int prefetchCount = this.prefetchCount;
	}

	
	public void close() {
		
	}

	
	public void activate() {
		// TODO Auto-generated method stub
		
	}

	
	public void deactivate() {
		// TODO Auto-generated method stub
		
	}

	
	public void nextTuple() {
		try {

			
			
			
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			
			collector.emit(new Values(message));
			
		} catch (Exception e) {
			
		}
		
	}

	
	public void ack(Object msgId) {
		// TODO Auto-generated method stub
		
	}

	
	public void fail(Object msgId) {
		// TODO Auto-generated method stub
		
	}

	
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("spoutdata"));
	}

	
	public Map<String, Object> getComponentConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
     * Returns the value of this {@code Long} as an
     * {@code int}.
     */
//    public int intValue() {
//        return (int)value;
//    }
	
    
}
