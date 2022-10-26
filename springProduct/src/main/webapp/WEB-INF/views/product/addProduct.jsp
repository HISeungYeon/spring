<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<title>상품 등록</title>
</head>
<body>
<jsp:include page="menu.jsp"/>
<div class="jumbotron">
	<div class="container">
		<h1 class="display-3">상품 등록</h1>
	</div>
</div>
<!-- ======================= 상품 상세 시작 ======================= -->
<div class="container">
	<form name="newProduct" action="/addProduct" class="form-horizontal" method="post">
		<div class="form-group row">
			<label class="col-sm-2">상품 코드</label>
			<div class="col-sm-3">
				<input type="text" id="productId" name="productId" class="form-control">
			</div>
		</div>	
		<div class="form-group row">
			<label class="col-sm-2">상품명</label>
			<div class="col-sm-3">
				<input type="text" id="pname" name="pname" class="form-control">
			</div>
		</div>	
		<div class="form-group row">
			<label class="col-sm-2">상품가격</label>
			<div class="col-sm-3">
				<input type="text" id="unitPrice" name="unitPrice" class="form-control">
			</div>
		</div>	
		<div class="form-group row">
			<label class="col-sm-2">상품설명</label>
			<div class="col-sm-3">
				<textarea rows="2" cols="50" id="description" name="description"
				class="form-control"></textarea>
			</div>
		</div>	
		<div class="form-group row">
			<label class="col-sm-2">제조사</label>
			<div class="col-sm-3">
				<input type="text" id="manufacturer" name="manufacturer" class="form-control">
			</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-2">분류</label>
			<div class="col-sm-3">
				<input type="text" id="category" name="category" class="form-control">
			</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-2">재고수</label>
			<div class="col-sm-3">
				<input type="text" id="unitsInStock" name="unitsInStock" class="form-control">
			</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-2">상태</label>
			<div class="col-sm-5">
				<input type="radio" id="condition1" name="condition" value="New">신규제품
				<input type="radio" id="condition2" name="condition" value="Old">중고제품
				<input type="radio" id="condition3" name="condition" value="Refurbished">재생제품
			</div>
		</div>
		<!-- ch07에서 추가됨 -->
		<div class="form-group row">
			<label class="col-sm-2">이미지</label>
			<div class="col-sm-5">
				<input type="file" id="productImage" name="productImage" class="form-control">
			</div>
		</div>
		<div class="form-group row">
			<div class="col-sm-offset-2 col-sm-10">
				<input type="submit" class="btn btn-primary" value="등록"/>
				<a href="products.jsp" class="btn btn-warn">상품목록</a>
			</div>
		</div>
		<hr>
	</form>
</div>
<!-- ==================== 상품 상세 끝 ==================== -->
<jsp:include page="footer.jsp"/>
</body>
</html>