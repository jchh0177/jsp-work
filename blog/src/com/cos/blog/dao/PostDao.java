package com.cos.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cos.blog.config.DBConn;
import com.cos.blog.model.Post;
import com.cos.blog.model.User;

public class PostDao {
	
	private static PostDao instance = new PostDao();
	private PostDao() {}
	
	public static PostDao getInstance() {
		return instance;
	}

	public int 글개수() {
		String sql = "SELECT count(*) FROM post";
		Connection conn = DBConn.getinstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);		
			ResultSet rs = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public int 수정하기(Post post) {
		String sql = "UPDATE post SET title = ?, content = ? WHERE id = ? ";
		Connection conn = DBConn.getinstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);		
			pstmt.setString(1,post.getTitle());
			pstmt.setString(2,post.getContent());
			pstmt.setInt(3,post.getId());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public int 삭제하기(int id) {
		String sql = "DELETE FROM post WHERE id=?";
		Connection conn = DBConn.getinstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);		
			pstmt.setInt(1,id);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}	
	
	public int 조회수증가(int id) {
		String sql = "UPDATE post SET readCount = readCount+1 WHERE id=?";
		Connection conn = DBConn.getinstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);		
			pstmt.setInt(1,id);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}	

	public Post 상세보기(int id) {
		
		String sql = "SELECT * FROM post WHERE id=?";
		Connection conn = DBConn.getinstance();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Post post = Post.builder()
						.id(rs.getInt("id"))
						.title(rs.getString("title"))
						.content(rs.getString("content"))
						.readCount(rs.getInt("readCount"))
						.createDate(rs.getTimestamp("createDate"))
						.userId(rs.getInt("userId"))
						.build();
				
				return post;
			}					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public  List<Post> 글목록(int page) {
		List<Post> posts = new ArrayList<>();
		
		String sql = "SELECT * FROM post ORDER BY id DESC limit ?,3";
		Connection conn = DBConn.getinstance();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, page);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Post post = Post.builder()
							.id(rs.getInt("id"))
							.title(rs.getString("title"))
							.content(rs.getString("content"))
							.readCount(rs.getInt("readCount"))
							.createDate(rs.getTimestamp("createDate"))
							.userId(rs.getInt("userId"))
							.build();
				
				posts.add(post);
			}
			return posts;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public int 글쓰기(Post post) {
		String sql = "INSERT INTO post(title, content, readCount, userId, createDate) VALUES(?,?,?,?,now()) ";
		Connection conn = DBConn.getinstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);		
			pstmt.setString(1,post.getTitle());
			pstmt.setString(2,post.getContent());
			pstmt.setInt(3,post.getReadCount());
			pstmt.setInt(4,post.getUserId());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
}
