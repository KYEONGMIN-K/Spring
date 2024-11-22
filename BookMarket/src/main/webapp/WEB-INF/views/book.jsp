<%@ page isELIgnored="false" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.springmvc.domain.Book" %>
<!DOCTYPE html>
<html>
<head>
<link href="http://localhost:8080/BookMarket/resources/css/bootstrap.min.css" rel="stylesheet">
<script src="/BookMarket/resources/js/controllers.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="menu.jsp" %>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">도서정보</h1>
		</div>
	</div>
	
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<%
					Book book = (Book)request.getAttribute("book");
					String bookImage = null;
					System.out.println("book주소 : "+book);
					if(book != null && book.getBookImage() != null){
						bookImage = book.getBookImage().getOriginalFilename();
						System.out.println("bookImage : "+bookImage);	
					}
				
					if(bookImage == null){
				%>
					<img src="/BookMarket/resources/images/<%=book.getBookId()%>.png" style="width:100%">			
				<%} else{%>
					<img src="/BookMarket/resources/images/<%=bookImage %>" style="width:100%">
				<%} %>
			</div>
			<div class="col-md-8">
				<h3><%=book.getName() %></h3>
				<p><%=book.getDescription() %></p>
				<br>
				<p><b>도서코드 : </b><span class="badge badge-info"><%=book.getBookId() %></span>
				<p><b>저자</b> : <%=book.getAuthor() %>
				<p><b>출판사</b> : <%=book.getPublisher() %>
				<p><b>출판일</b> : <%=book.getReleaseDate() %>
				<p><b>분류</b> : <%= book.getCategory() %>
				<p><b>재고수</b> : <%= book.getUnitsInStock() %>
				<h4><%= book.getUnitPrice() %>원</h4>
				<br>
				<form:form name="addForm" method="put">
					<p><a href="javascript:addToCart('../cart/add/<%=book.getBookId() %>')" class="btn btn-secondary">도서주문 &raquo;</a>
					<a href="/BookMarket/cart" class="btn btn-warning">장바구니 &raquo;</a>
					<a href="<c:url value='http://localhost:8080/BookMarket/books'/>" class="btn btn-secondary">도서 목록</a>
				</form:form>
			</div>
		</div>
		<hr>
		<%@ include file="footer.jsp" %>
	</div>
	
	
</body>
</html>