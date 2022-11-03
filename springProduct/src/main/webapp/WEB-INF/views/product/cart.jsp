<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="kr.or.ddit.vo.ProductVO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<title>장바구니</title>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css" />
</head>
<body>
<%
	//스크립틀릿
	//장바구니 = 세션
	//장바구니 고유번호 = session.getId();
	String cartId = session.getId();
	out.println("<p> cartId : " + cartId + "</p>");
%>

<jsp:include page="menu.jsp"/>

<div class="jumbotron">
<!-- container : 이 안에 내용이 있다. -->
	<div class="container">
		<h1 class="display-3">장바구니</h1>
	</div>
</div>

<!-- --------------------------------장바구니 상세 내용 시작---------------------------   -->
	<div class="container">
		<div class="row">
			<table width="100%">
				<tr>
					<td align="left">
						<a href="/deleteCart?cartId=<%=cartId%>" class="btn btn-danger">삭제하기</a>
					</td>
					<td align="right">
						<a href="/shippingInfo?cartId=<%=cartId%>" class="btn btn-success">주문하기</a>
					</td>
				</tr>
			</table>
		</div>
		<!-- 장바구니 출력 시작 -->
		<div style="padding-top:50px;">
			<table class="table table-hover">
				<tr>
					<th>상품</th><th>가격</th><th>수량</th><th>금액</th><th>비고</th>
				</tr>
				<%
					//장바구니(session) 안에 들어있는 상품 목록(cartlist)을 가져오쟈
							ArrayList<ProductVO> cartList = 
								(ArrayList<ProductVO>)session.getAttribute("cartlist");
						
							//금액을 누적하는 변수 
							double sum = 0;
						
							//장바구니가 비어있다면,,
							if(cartList==null){
				%>
				<tr>
					<td colspan="5" style="text-align:center;">장바구니에 상품이 없습니다.</td>
				</tr>
				<%
					}else{ //상품이 있다면..
								//상품 리스트에서 하나씩 상품을 꺼내보자
								for(ProductVO product : cartList){
									//금액 = 가격*수량
									double total = product.getUnitPrice() * product.getQuantity();
									BigDecimal totalBig = new BigDecimal(total);
									//금액을 누적
									sum = sum + total;
				%>
				<tr>
					<td><%=product.getProductId() %> - <%=product.getPname() %></td>
					<td><fmt:formatNumber value="<%=product.getUnitPrice() %>" pattern="#,###" /></td>
					<td><%=product.getQuantity() %></td>
					<td><fmt:formatNumber value="<%=totalBig %>" pattern="#,###" /> 원</td>
					<td>
						<a href="/removeCart?productId=<%=product.getProductId() %>" class="badge badge-danger">삭제</a>
					</td>
				</tr>
				<%
						} //end for
						//소수점 제거
						BigDecimal big = new BigDecimal(sum);
				%>
				<tr>
					<th></th>
					<th></th>
					<!-- 총액 : 금액의 합계 -->
					<th>총액</th>
					<th><fmt:formatNumber value="<%=big %>" pattern="#,###" />원</th>
					<th></th>
				</tr>
				<%
					}// end if
				%>
			</table>
			<a href="/products" class="btn btn-secondary">&laquo; 쇼핑 계속하기</a>
		</div>	
		<!-- 장바구니 출력 끝 -->
	</div>
<!-- --------------------------------장바구니 상세 내용  끝---------------------------   -->

<jsp:include page="footer.jsp" />
	
</body>
</html>