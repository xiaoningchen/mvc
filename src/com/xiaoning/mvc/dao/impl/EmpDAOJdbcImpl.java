package com.xiaoning.mvc.dao.impl;

import java.util.List;

import com.xiaoning.mvc.bean.Employee;
import com.xiaoning.mvc.dao.DAO;
import com.xiaoning.mvc.dao.EmpDAO;

public class EmpDAOJdbcImpl extends DAO<Employee> implements EmpDAO {

	@Override
	public List<Employee> getAll() {
		String sql = "select * from employee";
		return getForList(sql);
	}

	@Override
	public void add(Employee emp) {
		String sql = "insert into employee(id,name,phone) values (?,?,?)";
		update(sql,emp.getId(),emp.getName(),emp.getPhone());
	}

	@Override
	public Employee get(Integer id) {
		String sql = "select * from employee where id=?";
		return get(sql, id);
	}

	@Override
	public void delete(Integer id) {
		String sql = "delete from employee where id = ?";
		update(sql, id);
	}

	@Override
	public long getCountWithName(String name) {
		String sql = "select count(id) from employee where name = ?";
		return getForValue(sql, name);
	}

}
