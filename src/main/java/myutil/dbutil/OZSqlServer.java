package myutil.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 操作SqlServer
 * @author Europe
 */
public class OZSqlServer {

	/**
	 * SqlServer的查询操作
	 * @date 2014-11-27
	 */
	public void ozQuery(){
		
		try {
			Connection conn = getConnection();
			Statement st = conn.createStatement();
			ResultSet set = null;
			
			String sql = "select * from tablename ";
    		set = st.executeQuery(sql);
    		while(set.next()){
    			
    		}
    		
    		//关闭连接
    		set.close();
        	st.close();
        	conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	/**
	 * 连接 sqlserver
	 * @return 一个连接
	 * @date 2014-11-27
	 */
    public Connection getConnection() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String url = "jdbc:jtds:sqlserver://192.168.0.198:1433;DatabaseName=bodi_temp";
            String username = "qc";
            String password = "qc_2014";
            Connection conn = DriverManager.getConnection(url, username,
            password);
            return conn;
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
	
	
	
}
