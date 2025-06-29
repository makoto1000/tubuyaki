package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnecter {
	private static String URL = "jdbc:mysql://localhost:3306/sns_db";
	private static String USER = "root";
	private static String PASS = "root";
	
	   static {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace(); // ログに出力しておくとデバッグ時に便利
	        }
	    }
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(URL, USER, PASS);
	}
}
