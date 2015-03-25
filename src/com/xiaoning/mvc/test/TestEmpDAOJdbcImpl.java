package com.xiaoning.mvc.test;

import java.util.List;

import org.junit.Test;

import com.xiaoning.mvc.bean.Employee;
import com.xiaoning.mvc.dao.EmpDAO;
import com.xiaoning.mvc.dao.impl.EmpDAOJdbcImpl;

public class TestEmpDAOJdbcImpl {
	private EmpDAO ep = new EmpDAOJdbcImpl();
	@Test
	public void testGetAll() { 
		List<Employee> list = ep.getAll();
		System.out.println(list);
	}

	@Test
	public void testAdd() {
		Employee emp = new Employee();
		emp.setId(1);
		emp.setName("chan");
		emp.setPhone("18177507012");
		ep.add(emp);
	}

	@Test
	public void testGetInteger() {
		Employee emp = ep.get(1);
		System.out.println(emp);
	}

	@Test
	public void testDelete() {
		ep.delete(2);
	}

	@Test
	public void testGetCountWithName() {
		long count = ep.getCountWithName("chan");
		System.out.println(count);
	}

}
