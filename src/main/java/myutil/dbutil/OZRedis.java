package myutil.dbutil;

import redis.clients.jedis.Jedis;

/**
 * 对 redis 数据库的操作
 * @author Europe
 */
public class OZRedis {

	/**
	 * 插入到 redis 数据库
	 * @param redisIp
	 * @param dataBase
	 */
	public void intoRedis(String redisIp,int dataBase){
		//创建redis连接池
		Jedis j = OZRedisPool.getPool(redisIp, 6379).getResource();
		//选择数据库
		j.select(dataBase);
		//插入到 redis 中
		j.hset("key", "key", "value");
		j.hget("key", "key");
		//关闭连接池
		OZRedisPool.returnResource(j);
	}
	
	
}
