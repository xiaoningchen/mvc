package com.xiaoning.mvc.dao;

import java.util.List;

import com.xiaoning.mvc.bean.Employee;

public interface EmpDAO {
	/**
	 * 通过条件查询
	 * @param ce:条件
	 * @return
	 */
	public List<Employee> getForListWithFuzzyQuery(FuzzyQuery ce);
	/**
	 * 获取一个employee的集合
	 * @return
	 */
	public List<Employee> getAll();
	/**
	 * 增加一个employee
	 * @param emp
	 */
	public void add(Employee emp);
	/**
	 * 通过id获取employee
	 * @param id
	 * @return
	 */
	public Employee get(Integer id);
	/**
	 * 删除一个employee
	 * @param id
	 */
	public void delete(Integer id);
	/**
	 * 查看表中有几个相同name的employee
	 * @param name
	 * @return
	 */
	public long getCountWithName(String name);
}
