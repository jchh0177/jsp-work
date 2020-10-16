package com.cos.blog.action.post;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.dao.PostDao;
import com.cos.blog.model.Post;

public class PostUpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. id값 받기 (null 인지 체크)
		int id = Integer.parseInt(request.getParameter("id"));
		
		//2. DAO 연결해서 Post postEntity = 상세보기(id) 함수 호출
		PostDao postDao = new PostDao();
		int result = postDao.조회수증가(id);
		if(result ==1) {
			Post postEntity = postDao.상세보기(id);
			
			//3. request에 postEntity 담기
			request.setAttribute("post", postEntity);
							
			//4. updateForm.jsp 이동 => RequestDispatcher 사용하기
			RequestDispatcher dis = request.getRequestDispatcher("/post/updateForm.jsp");
			dis.forward(request, response);
		}else {
			response.sendRedirect("/");
		}
	}
}
