package com.xiaoning.mvc.test;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.xiaoning.mvc.db.JdbcUtils;

public class TestJdbcUtil {

	@Test
	public void testGetConnection() throws SQLException {
		Connection conn = JdbcUtils.getConnection();
		System.out.println(conn);
	}

}
