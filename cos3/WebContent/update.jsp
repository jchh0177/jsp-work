<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.cos3.config.DBConn"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>데이터 수정</title>
</head>
<body>
	<h1>데이터 수정 페이지</h1>
	<hr />
	<%
		if(request.getParameter("pw") == null || request.getParameter("name") == null || request.getParameter("id") == null){
			return;
		}
	
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		int id = Integer.parseInt(request.getParameter("id"));
		
		String query = "UPDATE person SET pw=?, name=? WHERE id = ?";
		Connection conn = DBConn.getinstance(); // 스트림(라인연결)
		PreparedStatement pstmt = conn.prepareStatement(query); // 버퍼
		pstmt.setString(1,pw); // 첫번째 ?에 password를 넣어라
		pstmt.setString(2,name); // 두번째 ?에 name를 넣어라
	    pstmt.setInt(3, id);
		int result = pstmt.executeUpdate(); // 수정된 행의 개수
	%>
	수정된 행의 개수 : <%=result %>
</body>
</html>