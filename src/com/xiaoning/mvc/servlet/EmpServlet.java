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
//	����1:
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String method = request.getParameter("method");
//		switch(method){
//		case"add":	add(request,response);break;
//		case"update":	update(request,response);break;
//		case"delete":	delete(request,response);break;
//		}
//	}

	//����2:
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//ȥ��/��.do
		String methodName = req.getServletPath().substring(1);//ȥ���ַ�����ǰһ���ַ�
		methodName = methodName.substring(0, methodName.length()-3);//ȥ���ַ�������3���ַ�
		try {
			//���÷����ȡmethodName��Ӧ�ķ���
			Method method = getClass().getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
			//���÷������methodName��Ӧ�ķ���
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
		//1.�ָ������������õ�Emp�ļ���
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		FuzzyQuery fq = new FuzzyQuery();
		fq.setId(id);
		fq.setName(name);
		fq.setPhone(phone);
		List<Employee> employees = ed.getForListWithFuzzyQuery(fq);
		
		//2.��emp���Ϸ���request��
		request.setAttribute("employees", employees);
		//3.ת��ҳ�浽index.jsp(�������ض���)
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
