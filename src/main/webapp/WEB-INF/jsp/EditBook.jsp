<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>蔵書情報更新</title>

</head>
<body>
	<div class="form-container">
		<!-- エラーメッセージを上部に表示 -->
		<c:if test="${not empty errorMessage}">
			<div class="error-message" style="color: red; margin-bottom: 10px;">
				<!-- HTMLタグを解釈するため escapeXml を false に設定 -->
				<c:out value="${errorMessage}" escapeXml="false" />
			</div>
		</c:if>


		<h1>蔵書情報更新</h1>

		<!-- 情報更新フォーム -->
		<form action="EditBookServlet" method="post">
			<!-- 更新対象の蔵書IDを保持 -->
			<input type="hidden" name="id" value="${book.id}" />

			<!-- タイトル入力欄 -->
			<div class="form-group">
				<label for="title">タイトル:</label> <input type="text" name="title"
					id="title" value="${book.title}" />
			</div>

			<!-- 著者入力欄 -->
			<div class="form-group">
				<label for="author">著者:</label> <input type="text" name="author"
					id="author" value="${book.author}" />
			</div>

			<!-- 出版社入力欄 -->
			<div class="form-group">
				<label for="publisher">出版社:</label> <input type="text"
					name="publisher" id="publisher" value="${book.publisher}" />
			</div>

			<!-- 出版日入力欄 -->
			<div class="form-group">
				<label for="publishdate">出版日:</label> <input type="date"
					name="publishdate" id="publishdate" value="${book.publishdate}" />
			</div>

			<!-- ボタンエリア -->
			<div class="button-container">
				<!-- 更新ボタン -->
				<input type="submit" formaction="UpdateBookServlet" value="更新"
					class="update-button" /><br>
				<!-- 削除ボタン: DeleteBookServletに送信 -->
				<input type="submit" formaction="DeleteBookServlet" value="削除"
					class="delete-button" /><br>
				<!-- 一覧画面に戻るリンク -->
				<a href="BookListServlet" class="back-button">蔵書一覧に戻る</a>
			</div>
		</form>
	</div>
</body>
</html>
