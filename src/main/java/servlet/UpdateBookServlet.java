package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.BooksDAO;
import model.Books;

//UpdateBookServlet: 書籍情報の更新を処理するサーブレット
@WebServlet("/UpdateBookServlet")
public class UpdateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// フォーム入力値を取得
		int id = Integer.parseInt(request.getParameter("id")); // IDを取得
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		String publishDateStr = request.getParameter("publishDate");

		// エラーメッセージのリストを作成
		List<String> errorMessages = new ArrayList<>();

		// 入力値のバリデーション
		if (title == null || title.trim().isEmpty()) {
			errorMessages.add("タイトルが入力されていません");
		}
		if (author == null || author.trim().isEmpty()) {
			errorMessages.add("著者が入力されていません");
		}
		if (publisher == null || publisher.trim().isEmpty()) {
			errorMessages.add("出版社が入力されていません");
		}

		// 出版日のバリデーションと変換
		Date publishDate = null;
		if (publishDateStr == null || publishDateStr.trim().isEmpty()) {
			errorMessages.add("出版日が入力されていません");
		} else {
			try {
				publishDate = Date.valueOf(publishDateStr);
			} catch (IllegalArgumentException e) {
				errorMessages.add("出版日は正しい形式で入力してください。");
			}
		}

		// エラーがある場合、入力フォームに戻す
		if (!errorMessages.isEmpty()) {
		    // 入力値を保持するためにリクエストスコープに設定
		    request.setAttribute("book", new Books(0, title, author, publisher, publishDate, null));
		    request.setAttribute("errorMessages", errorMessages);

		    // フォワードしてフォームに戻る
		    request.getRequestDispatcher("/WEB-INF/jsp/EditBook.jsp").forward(request, response);
		    return;
		}

		// 書籍データの作成
	    Books bookToUpdate = new Books(id, title, author, publisher, publishDate, new Timestamp(System.currentTimeMillis()));

	    // 書籍をデータベースで更新
	    BooksDAO booksDAO = new BooksDAO();
	    boolean isUpdated = booksDAO.updateBook(bookToUpdate); // 更新処理を呼び出し
	    System.out.println("Book updated status: " + isUpdated); // デバッグ用ログ

	    // 成功時・失敗時の処理
	    if (isUpdated) {
	        response.sendRedirect("BookListServlet"); // 更新成功時に一覧画面へリダイレクト
	    } else {
	        request.setAttribute("errorMessages", List.of("書籍の更新に失敗しました。"));
	        request.getRequestDispatcher("/WEB-INF/jsp/EditBook.jsp").forward(request, response); // フォーム画面にフォワード
	    }
	}
}