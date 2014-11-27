package myutil.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 连接数据库用的类。    
 * <pre>
 * 使用方法：
 * 1、用setUserPar设置连接数据库的参数。
 * 2、用ozGetConn()连接数据库。
 * 3、用查询语句进行数据库操作：
 * 	mquery查询；
 * 	mupdate修改；
 * 4、ozClose()关闭连接。
 * 5、为了使用方便，所有方法都以oz开头
 * </pre>
 * <pre>
 * 例子:
 *     OZJDBC myJDBC = new OZJDBC();
 *     myJDBC.ozSetUserPar("127.0.0.1:3306", "wgdata_143", "root", "123456");
 *     Connection connection = myJDBC.ozGetConn();
 *     ResultSet rs = myJDBC.ozQuery("select * from `wgdata_143`.`check_rows_list`  limit 0,100");
 *     rs.close();
 *     connection.close();
 *     myJDBC.ozClose();
 * </pre>
 * @author Europe
 *
 */
public class OZJDBC {
	public static String ip;
	public static String dbName;
	public static String username;
	public static String password;
	public static Connection conn;
	
	/**
	 * 设置连接数据库时的基本信息
	 * @param dbName	数据库名，localhost:3306，比如work_data
	 * @param username	用户名，比如root
	 * @param password	数据库密码，比如123456
	 */
	public void ozSetUserPar(String ip,String dbName,String username,String password){
		this.ip = ip;
		this.dbName = dbName;
		this.username = username;
		this.password = password;
	}
	
	/**
	 * 连接数据库
	 * @return
	 */
	public Connection ozGetConn(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
//			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/my_wgdata","root","123456");
			//防止中文入库乱码
			conn=DriverManager.getConnection("jdbc:mysql://"+ip+"/"+dbName+"?useUnicode=true&characterEncoding=utf8",username,password);
			System.out.println("数据库连接成功。。。");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
	
	
	
	
	/**
	 * 由于返回的ResultSet和PreparedStatement不能在返回之前被关闭<br>
	 * 所以这个方法仅提供参考，不能直接使用！！！
	 * 查询功能，例如：SELECT * FROM `email` WHERE statu = 0。<br>
	 * 返回查询结果供遍历使用。<br>
	 * PreparedStatement会在此方法中关闭。
	 * <pre>
	 * 例如：while(resultSet.next()){
	 *         resultSet.getString("字段名")//获得字段所对应的值
	 *         resultSet.getString("下标")//获得字段所对应的值
	 *         
	 *         if(resultSet.getInt("字段名") == 0){
	 *           System.out.println("xxxx");
	 *         }
	 *       }
	 * </pre>
	 * @param sqlStr	sql语句
	 * @param dbName	数据库名
	 * @param username	用户名
	 * @param password	密码
	 * @return	ResultSet类型的数据
	 */
	public ResultSet ozQuery(String sqlStr){
		ResultSet rs = null;
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sqlStr);//准备查询语句
			rs = preparedStatement.executeQuery();//执行查询语句
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * 数据的修改，例如：UPDATE  `email` SET statu =？ WHERE id = ？
	 * @param sqlStr  传入的SQL语句
	 * @return resultSet  修改的条数
	 */
	public int ozUpdate(String sqlStr){
		int resultSet = 0 ;
		String sql = sqlStr;
		
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			resultSet = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	
	/**
	 * 插入方法，修改方法
	 * @param sqlStr	SQL插入语句 ，或者修改语句
	 * 例如：<h1>1、</h1>String sql = "INSERT INTO `email_log`(`content`,`receiver`,`get_date`)VALUES ('邮件发送失败','"+receiver+"','"+dateTime+"');";<br>
	 * 	   2、INSERT IGNORE INTO 要插入的表B SELECT * FROM 获得插入内容的表A; 也就是说从A插入到B
	 */
	public void ozInsert(String sqlStr){
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sqlStr);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 批处理
	 * @date 2014-11-27
	 */
	public void ozBatch(){
		
		try {
			Connection connection = this.ozGetConn();
			//自动提交设为false
			connection.setAutoCommit(false);
			//在循环中添加批处理
			for(int i = 0 ; i<10; i++){
				PreparedStatement preparedStatement = null;
				preparedStatement.addBatch();
				preparedStatement.executeBatch();
			}
			//最后将批处理提交（一般1万条数据提交一次）
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
	
	
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
		OZJDBC myJDBC = new OZJDBC();
//		myJDBC.ozSetUserPar("192.168.0.179:3306", "sku", "root", "mysql");
//		myJDBC.ozSetUserPar("192.168.0.161", "wgdata", "root", "isu123456");
		myJDBC.ozSetUserPar("127.0.0.1:3306", "wgdata_143", "root", "123456");//本地
		myJDBC.ozGetConn();
		ResultSet rs = myJDBC.ozQuery("select * from `wgdata_143`.`check_rows_list`  limit 0,100");
//		ozClose();
//		System.out.println(rs);
//		myJDBC.ozClose(rs);
//		System.out.println(rs);
		
		System.out.println("连接测试结束");
//		int a =myJDBC.mupdate("UPDATE `email` SET statu =1 WHERE statu = 0");
//		System.out.println(a);
	}
	
	
	
	/**
	 * 关闭连接
	 */
//	public static void ozClose(){
//		if(rs!=null){
//			try {
//				rs.close();
//				rs=null;
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		if(preparedStatement!=null){
//			try {
//				preparedStatement.close();
//				preparedStatement=null;
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		if(conn!=null){
//			try {
//				conn.close();
//				conn=null;
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//	}
	
}
