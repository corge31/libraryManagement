<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!-- JSTLコアタグライブラリを使用するための宣言 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書館蔵書検索</title>
</head>
<body>
	<h1>ログインが成功しました</h1>
	<p>
		<!-- ユーザーIDの表示 -->
		ようこそ
		<c:out value="${userId}" />
		さん
	</p>
	<!-- 蔵書一覧画面へのリンク -->
	<a href="BookListServlet">蔵書一覧観覧画面へ</a>
</body>
</html>

