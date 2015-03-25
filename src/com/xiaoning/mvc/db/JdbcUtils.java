package com.xiaoning.mvc.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Jdbc�����Ĺ�����
 * @author xiaoning
 *
 */
public class JdbcUtils {
	private static DataSource dataSource = null;
	static{
		dataSource = new ComboPooledDataSource("mvcapp");
	}
	
	/**
	 * �ر�Connection����
	 * @param con
	 */
	public static void releaseConnection(Connection con){
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
  				e.printStackTrace();
			}
		}
	}
	/**
	 * ��������Դ��һ��Connection����
	 * @return
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
}
