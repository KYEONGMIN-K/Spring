<%@ page isELIgnored="false" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- form 데이터를 넘기기 위해 꼭 필요한 것 -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
			<h1 class="display-3">
				<spring:message code="addBook.form.title.label"/>
			</h1>
		</div>
	</div>
	<div class="container">
		<div class="float-right">
			<form:form action="${pageContext.request.contextPath }/logout" method="POST">
				<input type="submit" class="btn btn-success" value="Logout">
			</form:form>
		</div>
		
		<div class="float-right" style="padding-right:30px">
			<a href="?language=ko" >Korean</a> | <a href="?language=en" >English</a>
		</div>
	<br><br>
	<!-- JSP와 다른 form태그 사용법, form태그 내에서는 form:(form, input 등)으로 표현해야한다.  -->
	<form:form modelAttribute="NewBook" class="form-horizontal" encType="multipart/form-data">
		<fieldset>
		<legend><spring:message code="addBook.form.subtitle.label"/></legend>
			<div class="form-group row">
				<label class="col-sm-2 control-label">
					<spring:message code="addBook.form.bookId.label"/>
				</label>
				<div class="col-sm-3">
					<!-- 
						form:input은 form 태그 내에서 input이 사용되고 있다는 것. 
						path는 form태그에 modelAttribute 사용 시 반드시 DTO의 멤버변수와 동일하게 적어야함. 
					 -->
					<form:input path="bookId" class="form-control"/>
				</div>
				<div class="col-sm-6">
					<form:errors path="bookId" cssClass="text-danger"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label">
					<spring:message code="addBook.form.name.label"/>
				</label>
				<div class="col-sm-3">
					<form:input path="name" class="form-control"/>
				</div>
				<div class="col-sm-6">
					<form:errors path="name" cssClass="text-danger"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label">
					<spring:message code="addBook.form.unitPrice.label"/>
				</label>
				<div class="col-sm-3">
					<form:input path="unitPrice" class="form-control"/>
				</div>
				<div class="col-sm-6">
					<form:errors path="unitPrice" cssClass="text-danger"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label">
					<spring:message code="addBook.form.author.label"/>
				</label>
				<div class="col-sm-3">
					<form:input path="author" class="form-control"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label">
					<spring:message code="addBook.form.description.label"/>
				</label>
				<div class="col-sm-3">
					<form:textarea path="description" cols="50" rows="2" class="form-control"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label">
					<spring:message code="addBook.form.publisher.label"/>
				</label>
				<div class="col-sm-3">
					<form:input path="publisher" class="form-control"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label">
					<spring:message code="addBook.form.category.label"/>
				</label>
				<div class="col-sm-3">
					<form:input path="category" class="form-control"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label">
					<spring:message code="addBook.form.unitsInStock.label"/>
				</label>
				<div class="col-sm-3">
					<form:input path="unitsInStock" class="form-control"/>
				</div>
				<div class="col-sm-6">
					<form:errors path="unitsInStock" cssClass="text-danger"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label">
					<spring:message code="addBook.form.releaseDate.label"/>
				</label>
				<div class="col-sm-3">
					<form:input path="releaseDate" class="form-control"/>
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2 control-label">
					<spring:message code="addBook.form.condition.label"/>
				</label>
				<div class="col-sm-3">
					<form:radiobutton path="condition" value="New"/>New
					<form:radiobutton path="condition" value="Old"/>Old
					<form:radiobutton path="condition" value="E-Book"/>E-Book
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label">
					<spring:message code="addBook.form.bookImage.label"/>
				</label>
				<div class="col-sm-7">
					<form:input path="bookImage" type="file" class="form-control"/>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-offset-2 col-sm-10">
					<!-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> --> 
					<input type="submit" class="btn btn-primary" value="<spring:message code='addBook.form.button.label'/>">
				</div>
			</div>
		</fieldset>
	</form:form>
	<hr>
	<%@ include file="footer.jsp" %>
</div>
</body>
</html>