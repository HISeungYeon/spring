<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<title>Ajax방식 파일업로드</title>
<script type="text/javascript">
$(function(){
	$("#inputFile").on("change", function(event){
		console.log("change");
		
		//event.target => <input type="file" id="inputFile" name="picture" />
		let files = event.target.files;
		
		let file = files[0];
		console.log("file : " + file);
		
		//가상의 <form>
		let formData = new FormData();
		formData.append("file", file);
		
		//contentType : 보내는 타입
		// dataType : 응답 타입
		$.ajax({
			url:"/board/uploadAjax",
			processData:false,
			contentType:false,
			data:formData,
			type:"post",
			dataType:"text",
			success:function(data){
				console.log("data : " + data);
			}
		});
	});
});
</script>
</head>
<body>
<form action="" method="post" enctype="multipart/form-data">
	<p><input type="file" id="inputFile" name="picture" /></p>
	<p><input type="button" value="업로드" /></p>

</form>

</body>
</html>