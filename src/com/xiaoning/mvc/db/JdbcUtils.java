package com.xiaoning.mvc.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Jdbc操作的工具类
 * @author xiaoning
 *
 */
public class JdbcUtils {
	private static DataSource dataSource = null;
	static{
		dataSource = new ComboPooledDataSource("mvcapp");
	}
	
	/**
	 * 关闭Connection连接
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
	 * 返回数据源的一个Connection对象
	 * @return
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
}
