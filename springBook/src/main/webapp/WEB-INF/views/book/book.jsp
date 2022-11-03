<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ page import="java.util.List"%>
<%@ page errorPage="exceptionNoBookId.jsp" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>도서 상세 정보</title>
<script type="text/javascript">

function addCart(){
	let result = confirm("도서를 장바구니에 추가할까욤??");
	
	if(result){
		console.log("true");
		document.addForm.submit();
	}else{
		console.log("false");
		document.addForm.reset();
	}
	
}

</script>
</head>
<body>
	<!-- 머리글에 해당하는 munu.jsp 파일의 내용을 포함하도록 include액션 태그 작성 -->
	<jsp:include page="menu.jsp"/>
	<div class="jumbotron">
		<!-- 내용넣기! -->
		<div class="container">
			<h1 class="display-3">도서 정보</h1>
		</div>
	</div>
	<!-- =========== 상품목록 시작!============ -->
	<div class="container">
		<div class="row" align="center">
			<div class="col-md-6">
				<div>
					<img alt="${book.name}" src="/resources/images/${book.filename}"
					style="width:200px;">
				</div>
				<h3>${book.name}</h3>
				<p>${book.description}</p>
				<p>
					<b>상품 코드 : </b>
					<span class="badge badge-danger">${book.bookId}</span>
				</p>
				<p>
					<b>출판사</b> : ${book.publisher}
				</p>
				<p><b>분류</b> : ${book.category}</p>
				<p><b>재고 수</b> : ${book.unitsInStock}</p>
				<h4>${book.unitPrice}원</h4>
				<form name="addForm" action="/addCart?id=${book.bookId}" method="post">
					<a href="#" class="btn btn-info" onclick="addCart()">상품주문 &raquo;</a>
					<a href="/cart" class="btn btn-warning">장바구니 &raquo;</a>
					<a href="/books" class="btn btn-secondary">상품목록 &raquo;</a>
				</form>
			</div>
		</div>
	</div>
	<!-- =========== 상품목록 끝 ! =========== -->
	<jsp:include page="footer.jsp" />
</body>
</html>