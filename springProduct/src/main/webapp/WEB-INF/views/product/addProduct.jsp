<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<title>상품 등록</title>
<style type="text/css">
	.imgs_wrap{
		width:300px;
		margin-top:50px;
	}
	.imgs_wrap img{
		max-width:100%;
	}
</style>
<script type="text/javascript" src="/resources/js/validation.js"></script>
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	//이미지 미리보기 시작---------------------
	let sel_file = [];
	
	$("#productImage").on("change", handleImgFileSelect);
	//파라미터 e: onchange 이벤트 객체
	function handleImgFileSelect(e){
		//이벤트가 발생된 타겟 안에 이미지 파일들을 가져와보쟈
		let files = e.target.files;
		//이미지가 여러개가 있을 수 있으므로 이미지를 분리하여 배열로 만듦
		let fileArr = Array.prototype.slice.call(files);
		//파일 타입의 배열 반복 f : 파일 배열 안에 들어있는 각각의 이미지 파일 객체 
		fileArr.forEach(function(f){
			if(!f.type.match("image.*")){
				alert("이미지 확장자만 가능합니닷");
				// 함수 종료
				return;
			}
			//이미지 객체를 (f) 전역 배열타입 변수 (sel_file)에 넣쟈
			sel_file.push(f);
			//이미지 객체를 읽을 자바스크립트의  reader 객체 생성
			let reader = new FileReader();
			//e : reader가 이미지 객체를 읽는 이벤트
			reader.onload = function(e){
				//e.target : 이미지 객체 
				//e.target.result : reader가 이미지를 다 읽은 결과 
				let img_html = "<img src=\"" + e.target.result + "\" />";
				//div 사이에 이미지가 렌더링되어 화면에 보임
				//객체.append : 누적, .html : 새로고침, innerHTML : J/S
				$(".imgs_wrap").append(img_html);
			}
			//f : 이미지 파일 객체를 읽은 후 다음 이미지 파일(f)을 위해 초기화
			reader.readAsDataURL(f);
		});//end forEach
	}
	
	//이미지 미리보기 끝---------------------
	
	//PRODUCT 테이블의 PRODUCT_ID  자동생성
	$.ajax({
		url:"/getProductId",
		type: "post",
		success:function(result){
			console.log("result : " + JSON.stringify(result));
			console.log("result.productId는?? " + result.productId);
			$("#productId").val(result.productId);
		}
		
	});
});
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
	<form name="newProduct" action="/addProduct" class="form-horizontal" method="post" enctype="multipart/form-data">
		<div class="form-group row">
			<label class="col-sm-2">상품 코드</label>
			<div class="col-sm-3">
				<input type="text" id="productId" name="productId" class="form-control" readonly />
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
				<input type="file" id="productImage" name="productImage" class="form-control"
					multiple />
			</div>
		</div>
		<div class="form-group row">
			<div class="imgs_wrap">
				
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