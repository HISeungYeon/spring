<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<title>상품 등록</title>
<script type="text/javascript">
// 	window.onload = function(){
// 		var condition = "${data.condition}";
// 		var ct1 = document.querySelector("#condition1");
// 		var ct2 = document.querySelector("#condition2");
// 		var ct3 = document.querySelector("#condition3");
// 		if(ct1.value == condition){
// 			ct1.checked = true;
// 		}else if(ct2.value == condition){
// 			ct2.checked = true;
// 		}else if(ct3.value == condition){
// 			ct3.checked = true;
// 		}
// 	};
</script>
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
	<form name="newProduct" action="/update" class="form-horizontal" method="post">
		<div class="form-group row">
			<label class="col-sm-2">상품 코드</label>
			<div class="col-sm-3">
				<input type="text" id="productId" value="${data.productId}" name="productId" class="form-control">
			</div>
		</div>	
		<div class="form-group row">
			<label class="col-sm-2">상품명</label>
			<div class="col-sm-3">
				<input type="text" id="pname" value="${data.pname}" name="pname" class="form-control">
			</div>
		</div>	
		<div class="form-group row">
			<label class="col-sm-2">상품가격</label>
			<div class="col-sm-3">
				<input type="text" id="unitPrice" value="${data.unitPrice}" name="unitPrice" class="form-control">
			</div>
		</div>	
		<div class="form-group row">
			<label class="col-sm-2">상품설명</label>
			<div class="col-sm-3">
				<textarea rows="2" cols="50" id="description" name="description"
				class="form-control">${data.description}</textarea>
			</div>
		</div>	
		<div class="form-group row">
			<label class="col-sm-2">제조사</label>
			<div class="col-sm-3">
				<input type="text" id="manufacturer" value="${data.manufacturer}" name="manufacturer" class="form-control">
			</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-2">분류</label>
			<div class="col-sm-3">
				<input type="text" id="category" value="${data.category}" name="category" class="form-control">
			</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-2">재고수</label>
			<div class="col-sm-3">
				<input type="text" id="unitsInStock" value="${data.unitsInStock}" name="unitsInStock" class="form-control">
			</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-2">상태</label>
			<div class="col-sm-5">
				<input type="radio" id="condition1" name="condition" value="New" 
				<c:if test="${data.condition=='New'}">checked</c:if> 
				/><label for="condition1"> 새상품</label> 
				<input type="radio" id="condition2" name="condition" value="Old" 
				<c:if test="${data.condition=='Old'}">checked</c:if> 
				/> <label for="condition2"> 중고상품</label> 
				<input type="radio" id="condition3" name="condition" value="Refurbished"
				<c:if test="${data.condition=='Refurbished'}">checked</c:if> 
				/> <label for="condition3"> 재생상품</label> 
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
				<input type="submit" class="btn btn-primary" value="수정"/>
				<a href="/products" class="btn btn-warn">상품목록</a>
			</div>
		</div>
		<hr>
	</form>
</div>
<!-- ==================== 상품 상세 끝 ==================== -->
<jsp:include page="footer.jsp"/>
</body>
</html>