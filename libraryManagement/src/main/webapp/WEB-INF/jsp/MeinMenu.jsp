<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書館蔵書検索</title>
</head>
<body>
	<h1>図書館蔵書検索</h1>
	<form action="LoginServlet" method="post">
		ユーザー名:<input type="text" name="userId"><br> パスワード:<input
			type="password" name="pass"><br> <input type="submit"
			value="ログイン">
	</form>
</body>
</html>