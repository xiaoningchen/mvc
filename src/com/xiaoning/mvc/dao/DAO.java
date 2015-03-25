package com.xiaoning.mvc.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.xiaoning.mvc.db.JdbcUtils;

/**
 * 封装了基本的CRUD方法,以供子类继承使用
 * 整个DAO采用DBUtils的解决方案
 * 当前DAO直接在方法中获取数据库链接
 * @param <T>:当前DAO的具体类型是什么
 */
public class DAO<T> {
	
	private Class<T> clazz;
	private QueryRunner queryRunner = new QueryRunner();
	
	public DAO() {
		Type superClass = getClass().getGenericSuperclass();
		if(superClass instanceof ParameterizedType){
			ParameterizedType parameterizedType = (ParameterizedType) superClass;
			Type [] typeArgs = parameterizedType.getActualTypeArguments();
			if(typeArgs[0] instanceof Class){
				clazz = (Class<T>) typeArgs[0];
			}
		}
	}
	
	/**
	 * 返回某一个字段的值:例如返回某一条记录的empName,或返回数据表中有多少条记录等.
	 * @param sql
	 * @param args
	 * @return
	 */
	public <E> E getForValue(String sql,Object ... args){
		Connection conn = null;
	
		try {
			conn = JdbcUtils.getConnection();
			return (E) queryRunner.query(conn, sql, new ScalarHandler(), args);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.releaseConnection(conn);
		}
		return null;
	}
	/**
	 * 返回T所对应的list
	 * @param sql
	 * @param args
	 * @return
	 */
	public List<T> getForList(String sql,Object ... args){
		Connection conn = null;
		try {
			conn = JdbcUtils.getConnection();
			return queryRunner.query(conn, sql, new BeanListHandler<T>(clazz), args);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.releaseConnection(conn);
		}
	
		return null;
	}
	 /**
	  * 返回一个T的一个实例类的对象
	  * @param sql
	  * @param args
	  * @return
	  */
	public T get(String sql,Object ... args){
		Connection conn = null;
		try {
			conn = JdbcUtils.getConnection();
			return queryRunner.query(conn, sql, new BeanHandler<T>(clazz), args);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.releaseConnection(conn);
		}
		return null;
	}
	/**
	 * 该方法封装了insert、delete、update操作.
	 * @param sql:sql语句
	 * @param args:填充sql语句的占位符
	 */
	public void update(String sql,Object ... args){
		Connection conn = null;
		try {
			conn = JdbcUtils.getConnection();
			queryRunner.update(conn, sql, args);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.releaseConnection(conn);
		}
	}
}
