package com.cos.blog.action.post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.dao.PostDao;
import com.cos.blog.dao.UserDao;
import com.cos.blog.model.Post;
import com.cos.blog.model.User;

public class PostSaveProcAction implements Action{
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 세션 확인
		HttpSession session = request.getSession();
		if(session.getAttribute("principal") == null) {
			return;
		}

		// 2. 공백, null 확인
		
		// 3. 값 검증( title에 < > 코드가 들어오는걸 방지 )
		int userId = Integer.parseInt(request.getParameter("userId"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		title.replaceAll("<", "&lt;");
		title.replaceAll(">", "&rt;");
		
		System.out.println("PostController : saveProc :");
		System.out.println("title : " + title);
		System.out.println("content : " + content);
		
		Post post = Post.builder()
			.title(title)
			.content(content)
			.readCount(0)
			.userId(userId)
			.build();
			
		System.out.println(post);			
		// 1. 회원가입 진행 (insert) Model로 이동
		PostDao postDao = PostDao.getInstance();
		postDao.글쓰기(post);
		
		//인덱스 페이지로 이동
		response.sendRedirect("index.jsp");
	}
}
