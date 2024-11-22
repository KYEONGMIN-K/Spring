<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.springmvc.domain.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
			<h1 class="display-3">장바구니</h1>
		</div>
	</div>
	
	<div class="container">
		<div>
			<form:form name="clearForm" method="delete">
				<a href="javascript:clearCart('/BookMarket/cart/delete<%
						Cart cart = (Cart)request.getAttribute("cart"); 
						String id=null;
						if(cart != null){
							id = cart.getCartId();
						}
						
						%><%= id %>')" class="btn btn-danger pull-left">삭제하기</a>
			</form:form>
			<a href="#" class="btn btn-success float-right">주문하기</a>
		</div>
		<div style="padding-top:50px">
			<table class="table table-hover">
				<tr>
					<th>도서</th>
					<th>가격</th>
					<th>수량</th>
					<th>소계</th>
					<th>비고</th>
				</tr>
				<form:form name="removeForm" method="put">
				<% 
					Cart cart = (Cart)request.getAttribute("cart");
					Set<String> keyname = null;
					Iterator iter = null;
					if(cart != null){
						keyname = cart.getCartItems().keySet();
						iter = keyname.iterator(); 
					}			
					 
					if(cart != null){	
						for(int i=0; i<cart.getCartItems().size(); i++){
						System.out.println("view cart : "+cart.getCartItems());
						String tmp = null;
						if(iter.hasNext()){
							tmp = iter.next().toString();
						}
				%>
				<tr>
					<td><%=cart.getCartItems().get(tmp).getBook().getBookId() %>-<%=cart.getCartItems().get(tmp).getBook().getName() %></td>
					<td><%=cart.getCartItems().get(tmp).getBook().getUnitPrice() %></td>
					<td><%=cart.getCartItems().get(tmp).getQuantity() %></td>
					<td><%=cart.getCartItems().get(tmp).getTotalPrice() %></td>
					<td><a href="javascript:removeFormCart('../cart/remove/<%=cart.getCartItems().get(tmp).getBook().getBookId()%>')" class="badge badge-danger">삭제</a>
				</tr>
				<%		} 
					}%>
				</form:form>
				<tr>
					<th></th>
					<th></th>
					<th>총액</th>
					<th><% 
					Cart cart = (Cart)request.getAttribute("cart");
					if(cart != null){
					%><%=cart.getGrandTotal() %>
					<%} %>
					</th>
					<th></th>
				</tr>
			</table>
			<a href="/BookMarket/books" class="btn btn-secondary">&laquo; 쇼핑 계속하기</a>
		</div>
		<hr>
		<%@ include file="footer.jsp" %>
	</div>
</body>
</html>