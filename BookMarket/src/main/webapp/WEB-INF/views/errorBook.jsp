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
		String id = (String)request.getAttribute("invaildBookId");
		String url = (String)request.getAttribute("url");
		Exception exception = (Exception)request.getAttribute("exception");
	%>
	<%@ include file="menu.jsp" %>
	<div class="jumbotron">
		<div class="container">
			<h2 class="alert alert-danger">
				해당 도서가 존재하지 않습니다.
				도서 ID : <%=id %>
			</h2>
		</div>
	</div>
	<div class="container">
		<p><%=url %>
		<p><%=exception %>
	</div>
	<div class="container">
		<p>
			<a href="/BookMarket/books" class="btn btn-secondary">도서목록 &raquo;</a>
		</p>
	</div>
</body>
</html>