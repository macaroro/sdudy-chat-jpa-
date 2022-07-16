<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>글목록</title>
<style type="text/css">
h2{ text-align: center;}
table{   width: 50%; margin: 0 auto;

    border: 1px solid #444444;
    border-collapse: collapse;
  }
  th, td {
    border: 1px solid #444444;
    padding: 10px;
   
  }
 tr:nth-child(2n) {
    background-color: #bbdefb;
  }
 tr:nth-child(2n+1) {
    background-color: #e3f2fd;
  }
  th{ background-color: lightgreen;}
  tr:hover{background-color: orange;}
</style>
</head>
<body>
<h2>글목록</h2>
<table>
	<tr>
		<th>No.</th><th>제목</th><th>작성자</th><th>작성일</th>
	</tr>
<c:forEach var="board" items="${list}">
	<tr>
		<td>${board.num}</td>
		<td><a href="/jpaboard/detail/${board.num}">${board.title}</a></td>
		<td>${board.author}</td>
		<td>${board.wdate}</td>
	</tr>
</c:forEach>
</table>
<footer>
	<c:forEach var="i" begin="${start}" end="${end}">
		<a href="/jpaboard/list?page=${i}&size=2&sort=num,desc&sort=num,desc&sort=author,asc">
			<c:if test="${i==pageInfo.number }"><span>${i+1}</span></c:if>
			<c:if test="${i!=pageInfo.number }">${i+1}</c:if>
		</a>
	</c:forEach>
	</footer>
	</body>
</html>