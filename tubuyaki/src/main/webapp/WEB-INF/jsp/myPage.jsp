<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String name = (String)request.getAttribute("name"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>つぶやきアプリ</title>
</head>
<body>
	<h1>ようこそ<%= name %>さん</h1>
	<form action="" method="post">
	検索：<input type="text" name="search">
	<input type="submit" name="search" value="検索">
	</form>
	<!-- 投稿を表示する処理 -->
	<form action="" method="post">
	投稿する：<input type="text" name="textarea">
	<input type="submit" name="tubuyaki" value="つぶやく">
	</form>
</body>
</html>