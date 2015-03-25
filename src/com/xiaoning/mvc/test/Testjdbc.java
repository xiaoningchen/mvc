package com.xiaoning.mvc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Testjdbc {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hr", "root", "");
		System.out.println(conn);
	}
}
