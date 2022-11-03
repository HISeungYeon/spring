<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>도서 목록</title>
<style>
/* 	div{ */
/* 	border: 1px solid red; */
/* 	} */
</style>
</head>
<body>
	<!-- 머리글에 해당하는 munu.jsp 파일의 내용을 포함하도록 include액션 태그 작성 -->
	<jsp:include page="menu.jsp"/>
	<div class="jumbotron">
		<!-- 내용넣기! -->
		<div class="container">
			<h1 class="display-3">도서 목록</h1>
		</div>
	</div>
	<!-- =========== 상품목록 시작!============ -->
	<div class="container">
		<div class="row" style="justify-content:right; margin:0 0 30px 0;">
			<a href="addBook.jsp" class="btn btn-primary">도서 추가</a>
		</div>
		<div class="col" align="left">
			<!-- List<ProductVO> 한 행을 꺼내오면  ProductVO-->
			<c:forEach var="book" items="${listOfBooks}">
				<div class="col-lg">
					<div>
					<img alt="${book.name}" src="../resources/images/${book.filename}" style="width:200px;">
					</div>
					<div>
					<h3>${book.name}</h3>
					<p>${book.description} 
					<a href="book.jsp?id=${book.bookId}"
						class="btn btn-secondary" role="button">상세 정보&raquo;</a>
					</p>
					<p>${book.author} | ${book.publisher} | ${book.unitPrice}원</p>
					<hr />
					</div>
					<!-- 상품 아이디에 대한 상세 정보 페이지가 연결되도록 상세 정보 버튼 작성 -->
				</div>
			</c:forEach>
		</div>
	</div>
	<!-- =========== 상품목록 끝 ! =========== -->
	<jsp:include page="footer.jsp" />
</body>
</html>