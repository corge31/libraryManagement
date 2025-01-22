<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- JSTLコアタグライブラリを使用するための宣言 -->

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>蔵書一覧</title>
    <style>
        /* プレースホルダーテキストの色をグレーに設定 */
        ::placeholder {
            color: gray;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>蔵書一覧</h1>

        <!-- 新規蔵書登録ボタン -->
        <form action="ShowRegisterBookServlet" method="get">
            <input type="submit" value="新規蔵書登録" class="button" />
        </form>

        <!-- 検索フォーム -->
        <div class="search-container">
            <form action="BookListServlet" method="get">
                <label for="title">タイトル:</label>
                <input type="text" name="title" id="title" placeholder="タイトルで検索" value="${param.title}" />
                <br>
                <label for="author">著者:</label>
                <input type="text" name="author" id="author" placeholder="著者で検索" value="${param.author}" />
                <br>
                <label for="publisher">出版社:</label>
                <input type="text" name="publisher" id="publisher" placeholder="出版社で検索" value="${param.publisher}" />
                <br>
                <input type="hidden" name="searchSubmitted" value="true" />
                <input type="submit" value="検索" class="button" />
            </form>
        </div>

        <!-- エラーメッセージ表示 -->
        <c:if test="${not empty errorMessage}">
            <p class="error-message">
                <c:out value="${errorMessage}" />
            </p>
        </c:if>

        <!-- 一覧表示ボタン -->
        <form action="BookListServlet" method="get">
            <input type="submit" value="一覧を表示" class="button" />
        </form>

        <!-- 蔵書一覧表 -->
        <table id="bookListTable" style="border: 1px solid black; border-collapse: collapse;">
            <tr>
                <th style="border: 1px solid black;">ID</th>
                <th style="border: 1px solid black;">タイトル</th>
                <th style="border: 1px solid black;">著者</th>
                <th style="border: 1px solid black;">出版社</th>
                <th style="border: 1px solid black;">出版日</th>
            </tr>

            <c:choose>
                <c:when test="${not empty bookList}">
                    <c:forEach var="book" items="${bookList}">
                        <tr>
                            <td style="border: 1px solid black;"><c:out value="${book.id}" /></td>
                            <td style="border: 1px solid black;"><a href="EditBookServlet?id=${book.id}">
                                <c:out value="${book.title}" />
                            </a></td>
                            <td style="border: 1px solid black;"><c:out value="${book.author}" /></td>
                            <td style="border: 1px solid black;"><c:out value="${book.publisher}" /></td>
                            <td style="border: 1px solid black;"><c:out value="${book.publishdate}" /></td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="5" style="text-align: center;">蔵書が見つかりませんでした。</td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </table>
    </div>
</body>
</html>
