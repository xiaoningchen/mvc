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
 * ��װ�˻�����CRUD����,�Թ�����̳�ʹ��
 * ����DAO����DBUtils�Ľ������
 * ��ǰDAOֱ���ڷ����л�ȡ���ݿ�����
 * @param <T>:��ǰDAO�ľ���������ʲô
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
	 * ����ĳһ���ֶε�ֵ:���緵��ĳһ����¼��empName,�򷵻����ݱ����ж�������¼��.
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
	 * ����T����Ӧ��list
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
	  * ����һ��T��һ��ʵ����Ķ���
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
	 * �÷�����װ��insert��delete��update����.
	 * @param sql:sql���
	 * @param args:���sql����ռλ��
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
