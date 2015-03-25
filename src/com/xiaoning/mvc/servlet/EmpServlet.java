package com.xiaoning.mvc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmpServlet
 */
@WebServlet("/empservlet")
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EmpServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getMethod();
		switch(method){
		case"add":	add(request,response);break;
		case"update":	update(request,response);break;
		case"delete":	delete(request,response);break;
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

}
