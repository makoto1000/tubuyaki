<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.Tubuyaki"%>
<% String name = (String)session.getAttribute("name"); %>
<% String messageOfNoTubuyaki = (String)request.getAttribute("messageOfNoTubuyaki"); %>
<% List<Tubuyaki> tubuyakiList = (List<Tubuyaki>)request.getAttribute("tubuyakiList"); %>
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
	<input type="submit" name="operation" value="検索">
	</form>
	<!-- 投稿を表示する処理 -->
	<form action="TubuyakiMain" method="post">
	投稿する：<textarea name="tubuyaki"></textarea>
	<input type="submit" name="operation" value="つぶやく">
	</form>
	<% for(Tubuyaki tubuyaki : tubuyakiList){%>
	<p><%= tubuyaki.getUserName() %></p>
	<p><%= tubuyaki.getText() %></p>
	<p><%= tubuyaki.getLikes() %></p>
	<%} %>
	
	<% if(messageOfNoTubuyaki != null || !(messageOfNoTubuyaki.isEmpty())){ %>
	<p><%= messageOfNoTubuyaki %></p>
	<%} %>
</body>
</html>