<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
	<form action="/post?cmd=updateProc" method="post">

		<br /> <br />
		<h6 class="m-2">
			작성자 : <i> ${post.userId} </i> 조회수 : <i>${post.readCount}</i> 작성일 : <i>${post.createDate}</i>
		</h6>
		<br />

		<div class="form-group">
			<input type="text" class="form-control" name="title" required="required"  value="${post.title}"/>
		</div>

		<div class="form-group">
			<textarea id="summernote" class="form-control" name="content">${post.content}</textarea>
		</div>
	</form>
	<br /> <br />
	<button onclick="postUpdate(${post.id})" class="btn btn-danger">확인</button>
	<a href="/post?cmd=detail&id=${post.id} " class="btn btn-warning">취소</a>
</div>
<script>
$('#summernote').summernote({
    tabsize: 2,
    height: 300
  });

		function postUpdate(id) {
		var title = document.getElementsByTagName('input').value
		var content = document.getElementsByTagName('textarea').value
		var response = fetch("http://localhost:8080/post?cmd=updateProc&id="+id"&title="+title"&content="+content,{
			method:"post"
		}) //Pending
		.then(function(res) { // 다운로드 완료시 실행(파싱)
			//3초 뒤 실행
			// res => Promise 객체(다운받은 데이터)
			// res.text();
			// res.json();
			return res.text();
		})
		.then(function(res) {
			alert(res);
			if(res == "ok"){
				alert("수정성공");
				location.href = "/";
			}else{
				alert("수정실패");
				history.back();
			}
		});

		console.log("1");
		console.log("2");
		console.log("3");
		console.log("4");
	}
</script>
<%@ include file="../layout/footer.jsp"%>