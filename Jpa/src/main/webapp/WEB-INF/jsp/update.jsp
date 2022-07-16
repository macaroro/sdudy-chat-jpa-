<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시판 글 입력폼</title>
<style type="text/css">
	main { width:fit-content; margin:0 auto;}
	main>h3 { width:fit-content; margin:1em auto; border-bottom:3px double black;}
	form {padding:1em; border:1px solid black; border-radius:7px;}
	div>label { display:inline-block; width:2em; padding-right:1em; text-align:right; vertical-align:top;  }
	div>input {width:30em; margin-bottom:0.5em; }
	div>textarea { width:30em; height:10em; }
	form>div:last-child { margin:1em auto; margin-bottom:0; width:fit-content; text-align:center;}
	nav { width:fit-content; margin:1em auto; }
	nav > a { text-decoration:none; }
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
function update()
{
	var serData = $('#edit_form').serialize();
	$.ajax({
		url:'/jpaboard/update',
		method:'post',
		cache:false,
		data:serData,
		dataType:'json',
		success:function(res){
			alert(res.updated==true? '수정성공':'실패');
	
				location.href="/jpaboard/detail/"+${bbs.num};
		},
		error:function(xhr,status,err){
			alert(err);
		}
	});
	return false;
}

</script>
</head>
<body>
<main>
<h3>게시판 글 수정 폼</h3>
<form id="edit_form" onsubmit="return update();">

	<div>
		<label for="title">글번호</label>
		<input type="text" id="num" name="num" value="${bbs.num}" >
	</div>
	<div>
		<label for="title">제목</label>
		<input type="text" id="title" name="title" value="${bbs.title}" >
	</div>
	<div>
		<label for="author">작성자</label>
		<input type="text" id="author" name="author" value="${bbs.author}" readonly>
	</div>
		<div>
		<label for="title">날짜</label>
		<input type="text" id="wdate" name="wdate" value="${bbs.wdate}" >
	</div>
	<div>
		<label for="contents">내용</label>
		<input id="contents" name="contents" value="${bbs.contents}"></input>
	</div>
	<div>
		<button type="reset">취소</button>
		<button type="submit">수정</button>
	</div>
</form>
</main>

</body>
</html>