package com.cos.blog.controller;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.action.post.PostListAction;
import com.cos.blog.action.post.PostSaveFormAction;
import com.cos.blog.action.post.PostSaveProcAction;
import com.cos.blog.action.user.UserJoinFormAction;
import com.cos.blog.action.user.UserJoinProcAction;
import com.cos.blog.action.user.UserLoginFormAction;
import com.cos.blog.action.user.UserLoginProcAction;
import com.cos.blog.action.user.UserLogoutAction;
import com.cos.blog.action.user.UserUpdateFromAction;
import com.cos.blog.action.user.UserUpdateProcAction;
import com.cos.blog.model.Post;

// http://localhost:8080/blog/*.do
// 모든 .do 요청은 FrontController를 탐
@WebServlet("/post")
public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PostController() {
        super();
    }

	void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/post 요청됨");
		String cmd = request.getParameter("cmd");
		System.out.println("cmd : " + cmd);
		
		Action action = route(cmd);
		if(action != null) action.execute(request, response);
	}

	private Action route(String cmd) {
		if(cmd.equals("list")) {
			return new PostListAction();
		}		else if(cmd.equals("saveForm")) {
			//회원가입 페이지로 이동 Redirect
			return new PostSaveFormAction();
		}else if(cmd.equals("saveProc")) {
			//로그인 페이지로 이동 Redirect
			return new PostSaveProcAction();
		}
		return null;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// request.setCharacterEncoding("UTF-8");
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// request.setCharacterEncoding("UTF-8");
		process(request, response);
	}

}
