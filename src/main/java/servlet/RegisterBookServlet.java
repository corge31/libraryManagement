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

//RegisterBookServlet: 新しい書籍を登録するためのサーブレット
@WebServlet("/RegisterBookServlet")
public class RegisterBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// POSTリクエストの処理
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("RegisterBookServlet: POST method called");

		// フォーム入力値を取得
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
			request.setAttribute("errorMessages", errorMessages);
			request.getRequestDispatcher("/WEB-INF/jsp/RegisterBook.jsp").forward(request, response);
			return;
		}

		// 書籍データの作成
		Books newBook = new Books(0, title, author, publisher, publishDate, new Timestamp(System.currentTimeMillis()));

		// 書籍をデータベースに追加
		BooksDAO booksDAO = new BooksDAO();
		boolean isAdded = booksDAO.addBook(newBook);// データベース操作
		System.out.println("Book added status: " + isAdded);// デバッグ用ログ

		// 成功時・失敗時の処理
		if (isAdded) {
			response.sendRedirect("BookListServlet");// 登録成功時に一覧画面へリダイレクト
		} else {
			request.setAttribute("errorMessages", List.of("書籍の登録に失敗しました。"));
			request.getRequestDispatcher("/WEB-INF/jsp/RegisterBookForm.jsp").forward(request, response);// フォーム画面にフォワード
		}
	}
}
