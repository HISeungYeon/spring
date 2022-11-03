<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>파일업로드</title>
</head>
<body>
<form action="/register/registerFile01" method="post" enctype="multipart/form-data">
	<p><input type="file" name="picture" /></p>
	<p><input type="submit" value="업로드" /></p>

</form>
<hr />
<form action="/register/registerFile02" method="post" enctype="multipart/form-data">
	<p>userId : <input type="text" name="userId" value="hi" />
	<p>password : <input type="text" name="password" value="java" />
	<p><input type="file" name="picture" /></p>
	<p><input type="submit" value="업로드" /></p>
</form>
<hr />
<form action="/register/registerFile03" method="post" enctype="multipart/form-data">
	<p>userId : <input type="text" name="userId" value="hi" />
	<p>password : <input type="text" name="password" value="java" />
	<p><input type="file" name="picture" /></p>
	<p><input type="submit" value="업로드" /></p>
</form>
<hr />
<form action="/register/registerFile05" method="post" enctype="multipart/form-data">
	<p>userId : <input type="text" name="userId" value="hi" />
	<p>password : <input type="text" name="password" value="java" />
	<p><input type="file" name="picture" /></p>
	<p><input type="file" name="picture2" /></p>
	<p><input type="submit" value="업로드" /></p>
</form>
<hr />
<form action="/register/registerFile06" method="post" enctype="multipart/form-data">
	<p>userId : <input type="text" name="userId" value="hi" />
	<p>password : <input type="text" name="password" value="java" />
	<p><input type="file" name="pictureList" /></p>
	<p><input type="file" name="pictureList" /></p>
	<p><input type="submit" value="업로드" /></p>
</form>
<hr />
<form action="/register/registerFile07" method="post" enctype="multipart/form-data">
	<p>userId : <input type="text" name="userId" value="hi" />
	<p>password : <input type="text" name="password" value="java" />
	<p><input type="file" name="pictures" multiple/></p> <!-- MultipartFile[] -->
	<p><input type="file" name="pictures" multiple/></p> <!-- MultipartFile[] -->
	<p><input type="submit" value="업로드" /></p>
</form>

</body>
</html>