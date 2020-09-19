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
@WebServlet("/comment")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentController() {
        super();
    }

	void route(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/comment 요청됨");
		String cmd = request.getParameter("cmd");
		System.out.println("cmd : " + cmd);
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
