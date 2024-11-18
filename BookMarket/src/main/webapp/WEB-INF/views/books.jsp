<%@ page isELIgnored="false" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
			<c:forEach items="${bookList }" var="book">
				<div class="col-md-4">
					<h3>${book.name}</h3>
					<p>${book.author }
						<br>${book.publisher } | ${book.releaseDate }
					<p align=left>${fn:substring(book.description,0,100) }...
					<p>${book.unitPrice }원
					<p><a href="<c:url value='http://localhost:8080/BookMarket/books/book?id=${book.bookId}'/>" class="btn btn-secondary" role="button">상세정보 &raquo;</a>
				</div>
			</c:forEach>
		</div>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>