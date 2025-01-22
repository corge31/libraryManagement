package servlet;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.BooksDAO;
import model.Books;

//EditBookServlet: 書籍情報の編集を行うためのサーブレット
@WebServlet("/EditBookServlet")
public class EditBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// GETメソッドで書籍の詳細情報を表示
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータから書籍IDを取得
		int id = Integer.parseInt(request.getParameter("id"));

		// DAOから書籍情報を取得
		BooksDAO booksDAO = new BooksDAO();
		Books book = booksDAO.findById(id);

		// 書籍情報が存在する場合
		if (book != null) {
			request.setAttribute("book", book);
			// 編集ページにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/EditBook.jsp");
			dispatcher.forward(request, response);
		} else {
			// 書籍が存在しない場合、エラーメッセージとともに一覧にリダイレクト
			request.setAttribute("errorMessage", "書籍が見つかりませんでした。");
			response.sendRedirect("BookListServlet");
		}
	}

	// POSTリクエストの処理: 書籍情報を更新
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータから書籍情報を取得
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		String publishDateStr = request.getParameter("publishdate");

		// 出版日をSQLのDate型に変換
		Date publishDate = publishDateStr != null && !publishDateStr.isEmpty() ? Date.valueOf(publishDateStr) : null;
		
		// 書籍オブジェクトを作成
		Books book = new Books(id, title, author, publisher, publishDate, null);

		// DAOを使用して書籍情報を更新
		BooksDAO booksDAO = new BooksDAO();
		boolean updateSuccess = booksDAO.updateBook(book);

		// 更新が成功したかどうかで処理を分岐
		if (updateSuccess) {
			// 更新が成功したら一覧にリダイレクト
			response.sendRedirect("BookListServlet");
		} else {
			// 更新が失敗した場合、エラーメッセージを設定して編集ページに戻す
			request.setAttribute("errorMessage", "書籍の更新に失敗しました。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/EditBook.jsp");
			dispatcher.forward(request, response);
		}

	}
}
