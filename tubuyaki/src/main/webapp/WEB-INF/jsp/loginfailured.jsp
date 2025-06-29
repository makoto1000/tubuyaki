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
	<h1>ログイン失敗</h1>
	<%= message %><br>
	<a href="index.jsp">戻る</a>
</body>
</html>