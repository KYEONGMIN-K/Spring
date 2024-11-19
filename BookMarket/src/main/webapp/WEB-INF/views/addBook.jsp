<%@ page isELIgnored="false" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- form 데이터를 넘기기 위해 꼭 필요한 것 -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link href="/BookMarket/resources/css/bootstrap.min.css" rel="stylesheet">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%@ include file="menu.jsp" %>
	
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">도서 등록</h1>
		</div>
	</div>
	<div class="container">
	<!-- JSP와 다른 form태그 사용법, form태그 내에서는 form:(form, input 등)으로 표현해야한다.  -->
	<form:form modelAttribute="NewBook" class="form-horizontal" encType="multipart/form-data">
		<fieldset>
		<legend>${addTitle }</legend>
			<div class="form-group row">
				<label class="col-sm-2 control-label">도서ID</label>
				<div class="col-sm-3">
					<!-- 
						form:input은 form 태그 내에서 input이 사용되고 있다는 것. 
						path는 form태그에 modelAttribute 사용 시 반드시 DTO의 멤버변수와 동일하게 적어야함. 
					 -->
					<form:input path="bookId" class="form-control"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label">도서명</label>
				<div class="col-sm-3">
					<form:input path="name" class="form-control"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label">가격</label>
				<div class="col-sm-3">
					<form:input path="unitPrice" class="form-control"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label">저자</label>
				<div class="col-sm-3">
					<form:input path="author" class="form-control"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label">상세정보</label>
				<div class="col-sm-3">
					<form:textarea path="description" cols="50" rows="2" class="form-control"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label">출판사</label>
				<div class="col-sm-3">
					<form:input path="publisher" class="form-control"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label">분야</label>
				<div class="col-sm-3">
					<form:input path="category" class="form-control"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label">재고수</label>
				<div class="col-sm-3">
					<form:input path="unitsInStock" class="form-control"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label">출판일</label>
				<div class="col-sm-3">
					<form:input path="releaseDate" class="form-control"/>
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2 control-label">상태</label>
				<div class="col-sm-3">
					<form:radiobutton path="condition" value="New"/>New
					<form:radiobutton path="condition" value="Old"/>Old
					<form:radiobutton path="condition" value="E-Book"/>E-Book
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label">도서이미지</label>
				<div class="col-sm-7">
					<form:input path="bookImage" type="file" class="form-control"/>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" class="btn btn-primary" value="등록">
				</div>
			</div>
		</fieldset>
	</form:form>
	<hr>
	<%@ include file="footer.jsp" %>
</div>
</body>
</html>