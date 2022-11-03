<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>

<html>
<head>
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
<script type="text/javascript" src="../resources/js/book.js"></script>
<title>도서 등록</title>
</head>
<body>
	<jsp:include page="menu.jsp"/>
	<div class="jumbotron">
		<!-- 내용넣기! -->
		<div class="container">
			<h1 class="display-3">도서 등록</h1>
		</div>
	</div>
	<!-- =========== 상품 등록 시작!============ -->
	<div class="container">
		<form name="newBook" action="/addBook" class="form-horizontal" method="post" enctype="multipart/form-data">
			<div class="form-group row">
			 	<label class="col-sm-2">도서코드</label>
			 	<div class="col-sm-3">
			 		<input type="text" id="bookId" name="bookId" class="form-control">
			 	</div>
			</div>
			<div class="form-group row">
			 	<label class="col-sm-2">도서명</label>
			 	<div class="col-sm-3">
			 		<input type="text" id="name" name="name" class="form-control">
			 	</div>
			</div>
			<div class="form-group row">
			 	<label class="col-sm-2">저자</label>
			 	<div class="col-sm-3">
			 		<input type="text" id="author" name="author" class="form-control">
			 	</div>
			</div>
			<div class="form-group row">
			 	<label class="col-sm-2">가격</label>
			 	<div class="col-sm-3">
			 		<input type="text" id="unitPrice" name="unitPrice" class="form-control">
			 	</div>
			</div>
			<div class="form-group row">
			 	<label class="col-sm-2">설명</label>
			 	<div class="col-sm-3">
			 		<textarea id="description" name="description" rows="2" cols="50" class="form-control"></textarea>
			 	</div>
			</div>
			<div class="form-group row">
			 	<label class="col-sm-2">출판사</label>
			 	<div class="col-sm-3">
			 		<input type="text" id="publisher" name="publisher" class="form-control">
			 	</div>
			</div>
			<div class="form-group row">
			 	<label class="col-sm-2">출판일</label>
			 	<div class="col-sm-3">
			 		<input type="text" id="releaseDate" name="releaseDate" class="form-control">
			 	</div>
			</div>
			<div class="form-group row">
			 	<label class="col-sm-2">페이지 수</label>
			 	<div class="col-sm-3">
			 		<input type="text" id="totalPages" name="totalPages" class="form-control">
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
			 		<input type="radio" name="condition" value="New" /> 신규 상품
			 		<input type="radio" name="condition" value="Old" /> 중고 상품
			 	</div>
			</div>
			<!-- ch07에서 추가됨(파일업로드) -->
<!-- 			<div class="form-group row"> -->
<!-- 				<label class="col-sm-2">이미지</label> -->
<!-- 				<div class="col-sm-5"> -->
<!-- <!-- 					파일을 입력받을 수 있음 --> -->
<!-- 					<input type="file" name="productImage" class="form-control" /> -->
<!-- 				</div> -->
<!-- 			</div> -->
			<div class="form-group row">
			 	<div class="col-sm-offset-2 col-sm-10">
			 		<input type="submit" class="btn btn-primary" value="등록" />
			 		<a href="/books" class="btn btn-outline-primary" >상품목록</a>
			 	</div>
			</div>
		</form>
	</div>
	<!-- =========== 상품 등록 끝 ! =========== -->
	<jsp:include page="footer.jsp" />
</body>
</html>