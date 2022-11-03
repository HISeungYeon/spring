<%@ page import="kr.or.ddit.vo.ProductVO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>상품 상세 정보</title>
<script type="text/javascript">

function addToCart(){
	let result = confirm("상품을 장바구니에 추가하시겠습니까?");
	
	if(result){
		console.log("true");
		document.addForm.submit();
	}else{
		console.log("false");
		//초기화!
		document.addForm.reset();
	}
	
}

</script>
</head>
<body>
	<jsp:include page="menu.jsp"/>
	<div class="jumbotron">
		<!-- 내용넣기! -->
		<div class="container">
			<h1 class="display-3">상품 정보</h1>
		</div>
	</div>
	<!-- =========== 상품상세 시작!============ -->
	<div class="container">
		<div class="row" align="center">
			<div class="col-md-6">
				<img src="/resources/upload/${product.fileName}" style="width:70%;" alt="${product.pname}" title="${product.pname}" />
				<h3>${product.pname}</h3>
				<p>${product.description}</p>
				<p>
					<b>상품 ID : </b>
					<span class="badge badge-danger">${product.productId}</span>
				</p>
				<p>
					<b>제조사</b> : ${product.manufacturer}
				</p>
				<p><b>분류 </b> : ${product.category}</p>
				<p><b>재고수 </b> : ${product.unitsInStock}</p>
				<h4>${product.unitPrice}원</h4>
				
				<form name="addForm" action="/addCart" method="post" style="display:inline-block">
					<input type="hidden" name="productId" value="${product.productId}"/>
					<a href="#" class="btn btn-outline-info" onclick="addToCart()">상품주문 &raquo;</a>
				</form>
				
				<a href="/cart" class="btn btn-outline-warning" >장바구니&raquo;</a>
				<a href="/products" class="btn btn-outline-success">상품목록 &raquo;</a>
				<a href="/update?productId=${product.productId}" class="btn btn-outline-primary">상품수정 &raquo;</a>
				
				<form action="/delete" method="post" style="display:inline-block">
					<input  type="hidden" name="productId" value="${product.productId}" />
					<input type="submit" class="btn btn-outline-danger" value="상품삭제 &raquo;" />
				</form>
			</div>
		</div>
	</div>
	<!-- =========== 상품상세 끝 ! =========== -->
	<jsp:include page="footer.jsp" />
</body>
</html>