<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	var obj = {"name":"kim", "age":30};
	function test(){
		$.ajax({
			url : "./test",
			type : "post",
			data : JSON.stringify(obj),
			dataType : "json",
			contentType : "application/json",
			success : function(data){
				alert("성공");
			},
			error : function(errorThrown){
				alert("실패");
			}
		});
	}
</script>	
</head>
<body>
	<h3>RESTful 웹 서비스</h3>
	<button onclick="test()" type="button">실행하기</button>
</body>
</html>