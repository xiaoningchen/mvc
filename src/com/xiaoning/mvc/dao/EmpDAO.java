package com.xiaoning.mvc.dao;

import java.util.List;

import com.xiaoning.mvc.bean.Employee;

public interface EmpDAO {
	/**
	 * ͨ��������ѯ
	 * @param ce:����
	 * @return
	 */
	public List<Employee> getForListWithFuzzyQuery(FuzzyQuery ce);
	/**
	 * ��ȡһ��employee�ļ���
	 * @return
	 */
	public List<Employee> getAll();
	/**
	 * ����һ��employee
	 * @param emp
	 */
	public void add(Employee emp);
	/**
	 * ͨ��id��ȡemployee
	 * @param id
	 * @return
	 */
	public Employee get(Integer id);
	/**
	 * ɾ��һ��employee
	 * @param id
	 */
	public void delete(Integer id);
	/**
	 * �鿴�����м�����ͬname��employee
	 * @param name
	 * @return
	 */
	public long getCountWithName(String name);
}
