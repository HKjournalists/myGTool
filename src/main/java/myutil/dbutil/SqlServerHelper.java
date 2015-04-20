package myutil.dbutil;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;



public class SqlServerHelper {

	public Connection getConnection(){
		
//		OZConfigTool myConfigTool = new OZConfigTool("./sqlserver.properties");
		
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // 加载JDBC驱动
		String dbURL = "jdbc:sqlserver://192.168.0.162:1433; DatabaseName=ITdata"; // 连接服务器和数据库test
		String userName = "ouzhou"; // 默认用户名
		String userPwd = "ouzhou"; // 密码
		Connection dbConn;
		System.out.println("开始连接");
		try {
			Class.forName(driverName);
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			System.out.println("连接成功");
			System.out.println("Connection Successful!"); // 如果连接成功
															// 控制台输出Connection
															// Successful!
			return dbConn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	public static void main(String[] args) {
		SqlServerHelper sql = new SqlServerHelper();
		sql.getConnection();
	}

}
