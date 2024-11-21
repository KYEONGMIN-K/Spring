<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="http://localhost:8080/BookMarket/resources/css/bootstrap.min.css" rel="stylesheet">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Exception exception = (Exception)request.getAttribute("exception");
	%>
	<%@ include file="menu.jsp" %>
	<div class="jumbotron">
		<div class="container">
			<h2 class="alert alert-danger">
				요청한 도서가 존재하지 않습니다.
			</h2>
		</div>
	</div>
	<div class="container">
		<p><%=exception %>
	</div>
	<div class="container">
		<p>
			<a href="/BookMarket/books" class="btn btn-secondary">도서목록 &raquo;</a>
		</p>
	</div>
</body>
</html>