package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class JDBCUtil {
	public static Connection getConnection() {
		Connection c = null;
		Driver driver;
		try {
			driver = new Driver();
			DriverManager.registerDriver(driver);
			String URL = "jdbc:mySQL://localhost:3306/chungcumini";
			String User = "root";
			String password = "123456789";
			//tao ket noi
			c = DriverManager.getConnection(URL, User, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	public static void closeConnection(Connection c) {
		try {
			if(c!=null) {
				c.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
