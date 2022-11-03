<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>상품 목록</title>
</head>
<body>
	<!-- include 액션 태그 -->
	<jsp:include page="menu.jsp" />
	
	<!-- --------------------상품목록 시작-------------------- -->
	<div class="jumbotron">
		<!-- container : 이 안에 내용있다 -->
		<div class="container">
			<h1 class="display-3">상품 목록</h1>
		</div>
	</div>
	<!-- container : 이 안에 내용있다 -->
	<div class="container">
		<!-- 행별 처리 -->
		<div class="row" align="center">
			<c:forEach var="product" items="${product}">
			<!-- 열별 처리 -->
			<div class="col-md-4">
<!-- 				/images/P1234.jpg -->
				<img src="/resources/upload/${product.fileName}" style="width:100%;" alt="${product.pname}" title="${product.pname}" />
				<h3>${product.pname }</h3>
				<p>${product.description }</p>
				<p>${product.unitPrice }원</p>	
				<p><a href="/product?productId=${product.productId}" class="btn btn-outline-primary" role="button"> 상세정보&raquo;</a></p>			
			</div>
			</c:forEach>
		</div>
		<div class="form-group row">
			<div class="col-sm-offset-2 col-sm-10">
			<a href="/addProduct" class="btn btn-primary">상품등록</a>
			</div>
		</div>
	</div>
	<!-- --------------------상품목록 끝-------------------- -->
	
	<jsp:include page="footer.jsp" />
</body>
</html>










