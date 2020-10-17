package com.cos.blog.action.post;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.dao.PostDao;
import com.cos.blog.model.Post;

public class PostListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int page = 0;
		
		if(request.getParameter("page") == null) {
			page = 0;
		}else {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		PostDao postDao = PostDao.getInstance();
		List<Post> posts = postDao.글목록(page);
		
		//int count = postDao.글개수();
		//lastPage 연산 
		//int lastPage = count%3;
		//System.out.println("lastPage"+ lastPage);
		//if(lastPage ==)
		//request.setAttribute("lastpage", lastpage);
		request.setAttribute("posts", posts);
		
		RequestDispatcher dis = request.getRequestDispatcher("/post/list.jsp");
		dis.forward(request, response);
	}

}
