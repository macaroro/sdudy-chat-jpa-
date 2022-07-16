<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시물 상세보기</title>

<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
	function deleteIt(num)
	{
		if(!confirm('해당 게시물을 삭제하시겠어요?')) return;
		$.ajax({
			url: '/jpaboard/delete/'+num,
			method:'get',
			cache:false,
			dataType:'json',
			success:function(res){
				alert(res.deleted ? '삭제 성공':'Failed');
				location.href='/jpaboard/list';
			},
			error:function(xhr,status,err){
				alert('Error:'+err);
			}
		});
	}
</script>

</head>
<body>
<main>
<h3>게시물 상세보기</h3>
<table>
	<tr><th>글번호</th><td>${bbs.num}</td></tr>
	<tr><th>제목</th><td>${bbs.title}</td></tr>
	<tr><th>작성자</th><td>${bbs.author}</td></tr>
	<tr><th>내용</th><td>${bbs.contents}</td></tr>
	<tr><th>날짜</th><td>${bbs.wdate}</td></tr>
</table>
</main>

<a href="/jpaboard/edit/${bbs.num}">수정</a>
[<a href="javascript:deleteIt(${bbs.num});">삭제</a>]<br>
</body>
</html>

