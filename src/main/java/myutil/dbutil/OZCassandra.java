package myutil.dbutil;

import java.util.Iterator;

import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;

/**
 * 操作 Cassandra
 * @author apple
 */
public class OZCassandra {

	/**
	 * 包含了增删查改的方法，可以在开发过程中作为参考。
	 * @param cadraIp cassandra服务器的ip地址
	 */
	public void ozActionWithCassandra(String cadraIp){
		
		//连接cassandra
		Session session = getConnection(cadraIp).connect();
		
		//插入方法
		session.execute(QueryBuilder.insertInto("mykeyspace", "tablename")
				        .values(new String[]{"a","b"}, new Object[]{1,2}));
		
		//删除方法
		session.execute(QueryBuilder.delete()
				   .from("mykeyspace", "tablename")
				   .where(QueryBuilder.eq("a", 1)));
		
		//修改方法
		//update 记录String cql = “update mykeyspace.tablename set b=2 where a=1″
		session.execute(QueryBuilder.update("mykeyspace", "tablename")
			       .with(QueryBuilder.set("b", 2))
			       .where(QueryBuilder.eq("a", 1)));
		
		//查询方法 一
		//select 记录String cql = “select a, b from mykeyspace.tablename where a>1 and b<0″
		ResultSet result = session.execute(QueryBuilder.select("a","b")
				.from("mykeyspace", "tablename")
				.where(QueryBuilder.gt("a", 1))
				.and(QueryBuilder.lt("a", 1)));
		Iterator<Row> iterator = result.iterator();
		while(iterator.hasNext())
		{
			Row row = iterator.next();
			row.getInt("a");
			row.getInt("b");
		}
		
		//查询方法 二
		String cql = "select * from mykeyspace.tablename;";
		session.execute(cql);
		
		
		
		
		//预编译占位符的凡事
		BoundStatement bindStatement = 
				session.prepare(
				"select * from mykeyspace.tablename where a=? and b=?")
				.bind("1","2");
				session.execute(bindStatement);
		//或者
		Insert insert = 
				QueryBuilder.insertInto("mykeyspace", "tablename")
				.values(new String[]{"a","b"}, 
				new Object[]{QueryBuilder.bindMarker(),QueryBuilder.bindMarker()});
		BoundStatement bindStatement2 = 
			    new BoundStatement(session.prepare(insert)).bind("1","2");
				session.execute(bindStatement2);
		
		//批处理方式
		BatchStatement batchStatement = new BatchStatement();
		batchStatement.add(insert);
		batchStatement.add(bindStatement);
		session.execute(batchStatement);
		
	}
	
	/**
	 * 连接 Cassandra 用
	 * @return
	 */
	public Cluster getConnection(String cadraIp) {
		
		PoolingOptions poolingOptions = new PoolingOptions();
		poolingOptions.setMaxSimultaneousRequestsPerConnectionThreshold(
				HostDistance.LOCAL, 32);
		poolingOptions.setCoreConnectionsPerHost(HostDistance.LOCAL, 2);
		poolingOptions.setMaxConnectionsPerHost(HostDistance.LOCAL, 4);

		Cluster cluster = Cluster.builder().addContactPoints(cadraIp).build();
		return cluster;
	}
	
}
