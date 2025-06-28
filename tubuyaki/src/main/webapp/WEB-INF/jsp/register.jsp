<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String message = (String)request.getAttribute("message"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>つぶやきアプリ</title>
</head>
<body>
	<h1>ユーザー登録</h1>
	<%if(message !=null) { %>
	<%= message %>
	<% } %>
	<form action="TubuyakiMain" method="post">
	お名前：<input type="text" name="text"><br>
	メールアドレス：<input type="email" name="email"><br>
	パスワード：<input type="password" name="pass"><br>
	<input type="submit" name="operation" value="登録">
	</form>
</body>
</html>