package Sql.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Driver class
public class DBconnection {
	
	private static Connection conn = null;
	
	// method to getConnection
	public static Connection getConnection() {
		if (conn == null) {
			try {
				String url = "jdbc:mysql://localhost:3306/Hospital_jdbc";
				String uname = "root";
				String pw = "Root";
				conn = DriverManager.getConnection(url, uname, pw);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return conn;
	}
}
