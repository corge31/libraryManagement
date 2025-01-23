<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>蔵書情報更新</title>
</head>
<body>
	<div>
		<h1>蔵書情報更新</h1>

		<!-- エラーメッセージの表示 -->
		<c:if test="${not empty errorMessages}">
			<div class="error-message visible">
				<ul>
					<c:forEach var="error" items="${errorMessages}">
						<li><c:out value="${error}" /></li>
					</c:forEach>
				</ul>
			</div>
		</c:if>

		<!-- 情報更新フォーム -->
		<form action="${pageContext.request.contextPath}/EditBookServlet"
			method="post">

			<!-- ★IDをhiddenで送信 -->
			<input type="hidden" name="id" value="${book.id}" />

			<!-- タイトル入力欄 -->
			<div>
				<label for="title">タイトル:</label> <input type="text" name="title"
					id="title" value="${not empty book.title ? book.title : ''}" />
			</div>

			<!-- 著者入力欄 -->
			<div>
				<label for="author">著者:</label> <input type="text" name="author"
					id="author" value="${not empty book.author ? book.author : ''}" />
			</div>

			<!-- 出版社入力欄 -->
			<div>
				<label for="publisher">出版社:</label> <input type="text"
					name="publisher" id="publisher"
					value="${not empty book.publisher ? book.publisher : ''}" />
			</div>

			<!-- 出版日入力欄 -->
			<div>
				<label for="publishDate">出版日:</label> <input type="date"
					name="publishDate" id="publishDate"
					value="${not empty book.publishdate ? book.publishdate : ''}" />
			</div>

			<!-- ボタンエリア -->
			<div>
				<!-- 更新ボタン -->
				<input type="submit" formaction="UpdateBookServlet" value="更新" /><br>
				<!-- 削除ボタン -->
				<input type="submit" formaction="DeleteBookServlet" value="削除" /><br>
				<!-- 一覧画面に戻るリンク -->
				<a href="BookListServlet">蔵書一覧に戻る</a>
			</div>
		</form>
	</div>
</body>
</html>
