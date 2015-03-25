package com.xiaoning.mvc.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiaoning.mvc.bean.Employee;
import com.xiaoning.mvc.dao.EmpDAO;
import com.xiaoning.mvc.dao.FuzzyQuery;
import com.xiaoning.mvc.dao.impl.EmpDAOJdbcImpl;

/**
 * Servlet implementation class EmpServlet
 */
@WebServlet("*.do")
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmpDAO ed = new EmpDAOJdbcImpl();

    public EmpServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
//	方法1:
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String method = request.getParameter("method");
//		switch(method){
//		case"add":	add(request,response);break;
//		case"update":	update(request,response);break;
//		case"delete":	delete(request,response);break;
//		}
//	}

	//方法2:
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//去除/和.do
		String methodName = req.getServletPath().substring(1);//去掉字符串的前一个字符
		methodName = methodName.substring(0, methodName.length()-3);//去掉字符串后面3个字符
		try {
			//利用反射获取methodName对应的方法
			Method method = getClass().getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
			//利用反射调用methodName对应的方法
			method.invoke(this, req,resp);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} 
	}
	private void delete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		System.out.println("delete");
	}

	private void update(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		System.out.println("update");
	}

	private void add(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		System.out.println("add");
	}
	private void query(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//1.现根据搜索条件拿到Emp的集合
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		FuzzyQuery fq = new FuzzyQuery();
		fq.setId(id);
		fq.setName(name);
		fq.setPhone(phone);
		List<Employee> employees = ed.getForListWithFuzzyQuery(fq);
		
		//2.把emp集合放入request中
		request.setAttribute("employees", employees);
		//3.转发页面到index.jsp(不能用重定向)
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
