package com.cos.blog.action.post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.dao.PostDao;
import com.cos.blog.model.Post;

public class PostUpdateProcAction implements Action{
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 세션 확인
		HttpSession session = request.getSession();
		if(session.getAttribute("principal") == null) {
			return;
		}

		// 2. 공백, null 확인
		
		// 3. 값 검증( title에 < > 코드가 들어오는걸 방지 )
		//int userId = Integer.parseInt(request.getParameter("userId")); // 동일한 세션인지 검증용(현재 사용안함)
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		title.replaceAll("<", "&lt;");
		title.replaceAll(">", "&rt;");		
		String content = request.getParameter("content");
		
		Post post = Post.builder()
			.id(id)
			.title(title)
			.content(content)
			.build();

		PostDao postDao = PostDao.getInstance();
		postDao.수정하기(post);
		
		//인덱스 페이지로 이동
		response.sendRedirect("index.jsp");
	}
}
