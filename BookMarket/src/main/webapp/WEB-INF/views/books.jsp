<%@ page isELIgnored="false" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.springmvc.domain.Book" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<!-- http://localhost:8080/BookMarket/all -->
<link href="/BookMarket/resources/css/bootstrap.min.css" rel="stylesheet">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="menu.jsp" %>
	
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">도서목록</h1>
		</div>
	</div>
	<div class="container">
		<div class="row" align="center">
			<%
				List<Book> list = (List<Book>)request.getAttribute("bookList");
			
				for(int i=0; i<list.size(); i++){
				
				Book item = list.get(i);
			%>
				<div class="col-md-4">
					<img src="/BookMarket/resources/images/<%=item.getBookId() %>.png" style="width:60%">
					<h3><%= item.getName() %></h3>
					<p><%= item.getAuthor() %>
						<br><%=item.getPublisher() %> | <%=item.getReleaseDate() %>
					<p align=left><%=item.getDescription() %>...
					<p><%= item.getUnitPrice() %>원
					<p><a href="/BookMarket/books/book?id=<%=item.getBookId() %>" class="btn btn-secondary" role="button">상세정보 &raquo;</a>
				</div>
			<% }%>
		</div>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>