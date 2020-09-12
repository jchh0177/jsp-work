package com.cos.blog.controller;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.model.Post;

// http://localhost:8080/blog/*.do
// 모든 .do 요청은 FrontController를 탐
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FrontController() {
        super();
    }

	void route(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		
		if(uri.equals("/post/list.do")) { // 메인페이지
			//모델(DB연결 - 데이터 가져와서)
			Post post = new Post(1,"제목1","내용1",1,null,2);
			request.setAttribute("post", post);
			
			// 페이지 이동시에 request, response가 새로 만들어지는데
			// RequestDispatcher는 request, reponse를 유지시킨다.
			RequestDispatcher dis = request.getRequestDispatcher("/post/list.jsp");
			dis.forward(request, response);
			response.sendRedirect("/post/list.jsp");
		}else if(uri.equals("/post/detail.do")) {
			response.sendRedirect("/post/detail.jsp");
		}else if(uri.equals("/post/saveform.do")) {
			response.sendRedirect("/post/saveForm.jsp");
		}else if(uri.equals("/post/updateform.do")) {
			response.sendRedirect("/post/updateForm.jsp");
		}else if(uri.equals("/user/joinform.do")) {
			response.sendRedirect("/user/joinForm.jsp");
		}else if(uri.equals("/user/loginform.do")) {
			response.sendRedirect("/user/loginForm.jsp");
		}else if(uri.equals("/user/userupdate.do")) {
			response.sendRedirect("/user/userUpdate.jsp");
		}else if(uri.equals("/user/userdetail.do")) {
			response.sendRedirect("/user/userDetail.jsp");
		}
	}
    
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
		
		route(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
		
		route(request, response);
	}

}
