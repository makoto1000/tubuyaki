<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>つぶやきアプリ</title>
</head>
<body>
	<h1>つぶやきアプリへようこそ！！</h1>
	<a href="TubuyakiMain?action=register">新規登録</a>
	<form action="TubuyakiMain" method="post">
		メールアドレス：<input type="text" name="email"><br>
		パスワード：<input type="password" name="pass"><br>
		<input type="submit" name="operation" value="ログイン">
	</form>
</body>
</html>