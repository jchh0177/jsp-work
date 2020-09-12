<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.cos3.config.DBConn"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>데이터 찾기</title>
</head>
<body>
	<h1>데이터 찾기(SELECT)</h1>
	<hr />
	<%
		if(request.getParameter("id") == null){
			return;
		}
	
		int id = Integer.parseInt(request.getParameter("id"));
		
		String query = "SELECT id,pw,name FROM person WHERE id=?";
		Connection conn = DBConn.getinstance(); // 스트림(라인연결)
		PreparedStatement pstmt = conn.prepareStatement(query); // 버퍼
		pstmt.setInt(1,id); // 첫번째 ?에 id를 넣어라
		ResultSet rs = pstmt.executeQuery(); // 수행된 행의 개수
		rs.next(); //커서 한칸 내리기
	%>
	<table border="1">
		<tr>
			<th>아이디</th>
			<th>패스워드</th>
			<th>이름</th>
		</tr>
		<tr>
			<th><%=rs.getInt("id") %></th>
			<th><%=rs.getString("pw") %></th>
			<th><%=rs.getString("name") %></th>
		</tr>
	</table>
</body>
</html>