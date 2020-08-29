package com.cos.controller;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet{

	
	@Override
	// 데이터 줘!!(SELECT) -> 어떤 데이터줘? (쿼리스트링 : ?키=값&키=값)
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			System.out.println("doGet() 요청");
			String uri = req.getRequestURI();
			System.out.println(uri);
			
			if(uri.equals("/user.do")) {
				System.out.println("/user 접근");
				resp.sendRedirect("user.jsp");
			}else if(uri.equals("/profile.do")) {
				System.out.println("/profile 접근");
				resp.sendRedirect("profile.jsp");
			}else if(uri.equals("/")) {
				System.out.println("/ 접근");
				resp.sendRedirect("index.jsp");
			}else {
				resp.sendRedirect("index.jsp");
			}
			System.out.println(req.getRequestURL());
		}	

  @Override
  //데이터 줄께(INSERT, DELETE, UPDATE) -> 어떤 데이터 (Http Body - MIME타입)
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  System.out.println("doPost() 요청");
  }
}