package com.collabera.jump.ems.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

	private static Connection connection;

	public static Properties properties;

	static {

		if (properties == null) {

			try {
				properties = new Properties();

				properties.load(new FileInputStream("config.properties"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(" config.properties - Properties file not found!");

			}
		}

	}
	

	public static Connection getConnection() throws SQLException {

		if (connection == null) {

			try {
				connection = DriverManager.getConnection(properties.getProperty("url"),
						properties.getProperty("username"), properties.getProperty("password"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				throw new SQLException("Unable to connect to the DB");
			}
		}
		return connection;
	}
	
//	public static int executeUpdate(String  sql)
//	{
//		 try {
//			 PreparedStatement prepstatement = connection.prepareStatement(sql);
//			 
//			 prepstatement.set
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		 
//	}
}
