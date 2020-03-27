package com.collabera.jump.employeems;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class SearchFunction {
	public static void main(String[] args) throws Exception {
		getConnection();
		get();
	}

	public static Connection getConnection() throws Exception {
		try {
			// final String DRIVER = "com.mysql.jdbc.Driver";
			final String URL = "jdbc:mysql://database-8.ctiembqzvsd8.us-east-1.rds.amazonaws.com/empdb";
			final String USERNAME = "admin";
			final String PASSWORD = "admin123";
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			return conn;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public static ArrayList<String> get() throws Exception {
		try {
			Connection conn = getConnection(); // establish connection to DB
			Scanner sc = new Scanner(System.in);
			System.out.print("Please enter the ID number of the employee you want to find: ");
			String empId = sc.nextLine();
			String query = "SELECT * FROM employee WHERE emp_id = " + empId;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query); // execute query and assign to rs
			while (rs.next()) {
				int id = rs.getInt("emp_id");
				String name = rs.getString("emp_name");
				String email = rs.getString("emp_email_id");
				System.out.println("ID: " + id);
				System.out.println("NAME: " + name);
				System.out.println("EMAIL: " + email);
				Thread.sleep(5000);
			}
			stmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
