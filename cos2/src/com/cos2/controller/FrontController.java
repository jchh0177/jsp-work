package com.cos2.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FrontController extends HttpServlet{

	@Override
	// 데이터 줘!!(SELECT) -> 어떤 데이터줘? (쿼리스트링 : ?키=값&키=값)
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			HttpSession session = req.getSession();
			String uri = req.getRequestURI();
			
			resp.setContentType("text/html; charset=utf-8");			
			
			if(uri.equals("/main.do")) {
				System.out.println("/main 접근");
				resp.sendRedirect("main.jsp");
			}else if(uri.equals("/login.do")) {
				System.out.println("/login 접근");
				resp.sendRedirect("login.jsp");
			}else if(uri.equals("/join.do")) {
				System.out.println("/join 접근");
				resp.sendRedirect("join.jsp");
			}else if(uri.equals("/info.do")) {
				System.out.println("/info 접근");				
				if(session.getAttribute("auth") == null){
					PrintWriter out = resp.getWriter();
					out.print("<script>");
					out.print("alert('인증되지 않은 사용자입니다.');");
					out.print("history.back();");
					out.print("</script>");
				}else {
						PrintWriter out = resp.getWriter();
						resp.sendRedirect("info.jsp");
					}
			}else if(uri.equals("/logout.do")) {
				System.out.println("/logout 접근");
				PrintWriter out = resp.getWriter();
				out.print("<script>");
				out.print("alert('로그아웃 되었습니다.');");
				out.print("history.back();");
				out.print("</script>");
				session.invalidate();					
			}
			System.out.println(req.getRequestURL());
		}	

  @Override
  //데이터 줄께(INSERT, DELETE, UPDATE) -> 어떤 데이터 (Http Body - MIME타입)
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String uri = req.getRequestURI();

		resp.setContentType("text/html; charset=utf-8");	
		
		 if(uri.equals("/loginProc.do")) {
				System.out.println("/loginproc 접근");
				String username = req.getParameter("username");
				String password = req.getParameter("password");
				
				if(username.equals("1") && password.equals("1")){
					session.setAttribute("auth",true);
					resp.sendRedirect("main.jsp");
				}else {
					PrintWriter out = resp.getWriter();
					out.print("<script>");
					out.print("alert('로그인 실패.');");
					out.print("history.back();");
					out.print("</script>");
				}
			}
		else if(uri.equals("/joinProc.do")) {
			System.out.println("/joinProc 접근");
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String email = req.getParameter("email");
			session.setAttribute("auth",true);
			resp.sendRedirect("main.jsp");
		}
		System.out.println(req.getRequestURL());
  }
}
