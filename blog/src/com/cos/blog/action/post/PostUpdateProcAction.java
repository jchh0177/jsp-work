package com.cos.blog.action.post;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.dao.PostDao;
import com.cos.blog.dao.UserDao;
import com.cos.blog.model.Post;
import com.cos.blog.model.User;

public class PostUpdateProcAction implements Action{
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
				
		System.out.println("title : " + title);
		System.out.println("content : " + content);

		Post post = new Post (
				id,
				title,
				content
			);
			System.out.println(post);		
		
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter pw = response.getWriter();
		// PostDao 연결
		PostDao postDao = new PostDao();
		// int result = 글수정(id);
		int result = postDao.글수정(post);
		
		if(result ==1) {
			pw.print("ok");
		}else {
			pw.print("fail");
		}
		pw.flush();
	}
}
