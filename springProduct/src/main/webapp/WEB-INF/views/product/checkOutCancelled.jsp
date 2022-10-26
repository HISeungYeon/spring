<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>주문 취소</title>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css" />
</head>
<body>
<div class="jumbotron">
<!-- container : 이 안에 내용이 있다. -->
	<div class="container">
		<h1 class="display-3">주문정보</h1>
	</div>
</div>

<!-- -----------------주문취소 시작 --------------------- -->
<%
	//기존 session 제거 후 새로 생성
	session.invalidate();
%>
<div class="container">
	<h2 class="alert alert-danger">주문이 취소되었습니다.</h2>
</div>
<div class="container">
	<p><a href="/products" class="btn btn-secondary">&laquo; 상품 목록</a></p>
</div>
<!-- -----------------주문취소 끝 --------------------- -->

<jsp:include page="footer.jsp" />
</body>
</html>