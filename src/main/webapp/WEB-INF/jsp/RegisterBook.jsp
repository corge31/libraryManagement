<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規蔵書登録</title>
</head>
<body>
	<div>
		<h1>蔵書新規登録</h1>

		<!-- エラーメッセージの表示 -->
		<!-- errorMessages が空でない場合にエラーリストを表示 -->
		<c:if test="${not empty errorMessages and errorMessages.size() > 0}">
			<div class="error-message visible">
				
				<ul>
					<!-- エラーメッセージをリストとして表示 -->
					<c:forEach var="error" items="${errorMessages}">
						<p><c:out value="${error}" /></p>
					</c:forEach>
				</ul>
			</div>
		</c:if>

		<!-- 新規登録フォーム -->
		<form action="${pageContext.request.contextPath}/RegisterBookServlet"
			method="post">
			<div>
				<label for="title">タイトル:</label> <input type="text" name="title"
					id="title" value="${param.title}" />
			</div>
			<div>
				<label for="author">著者:</label> <input type="text" name="author"
					id="author" value="${param.author}" />
			</div>
			<div>
				<label for="publisher">出版社:</label> <input type="text"
					name="publisher" id="publisher" value="${param.publisher}" />
			</div>
			<div>
				<label for="publishDate">出版日:</label> <input type="date"
					name="publishDate" id="publishDate" value="${param.publishDate}" />
			</div>
			<div>
				<input type="submit" value="登録" />
			</div>
		</form>

		<!-- 蔵書一覧に戻る -->
		<a href="BookListServlet">蔵書一覧に戻る</a>
	</div>
</body>
</html>
