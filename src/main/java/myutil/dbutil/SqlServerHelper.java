package myutil.dbutil;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	/**
	 * 插入语句
	 */
	public void insertSqlServer(){
		Connection conn = this.getConnection();
		Statement stamt = null;
		try {
			stamt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = ".......";
		try {
			stamt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * sql server 查询, 得到返回结果。
	 */
	public static void testCall(){
		Connection conn = null;
		Statement stmt = null;
//		Map<String, String> map = new HashMap<String, String>();
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver") ;
			conn = DriverManager.getConnection("jdbc:sqlserver://192.168.0.162:1433;DatabaseName=eCDC_analysis" , "sa" , "Isu123456") ;
//			stmt = conn.createStatement();
//			//检验邮箱
//			ResultSet rs = stmt.executeQuery("SELECT * FROM eru_app_cate_item_ref where item_id=");
			CallableStatement callStmt = null;
			callStmt = conn.prepareCall("{call pr_UserData_info(?)}");
			  
           // 参数index从1开始，依次 1,2,3...  
           callStmt.setString(1, "2837831");
           callStmt.execute();
           ResultSet rs = callStmt.executeQuery();
           int colunmCount = rs.getMetaData().getColumnCount();
           String[] colNameArr = new String[colunmCount];  
           for (int i = 0; i < colunmCount; i++) {
               colNameArr[i] = rs.getMetaData().getColumnName(i + 1);  
           } 
//           System.out.println("----" + JSON.toJSONString(colNameArr));
           
           while (rs.next()) {  
               StringBuffer sb = new StringBuffer();  
               for (int i = 0; i < colunmCount; i++) {  
                   sb.append(rs.getString(i + 1) + " | ");  
               }  
               System.out.println(sb);  
           }  
           System.out.println("-------------------");
           System.out.println(rs);
           System.out.println("-------------------");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(stmt != null ) stmt.close();
				if(conn != null ) conn.close();
			} catch (SQLException e) {
			}
		}
	}
	
	
	
	
	
	public static void main(String[] args) {
		SqlServerHelper sql = new SqlServerHelper();
		sql.getConnection();
	}

}
