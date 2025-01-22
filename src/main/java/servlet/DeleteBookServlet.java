package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.BooksDAO;

//DeleteBookServlet: 書籍を削除するためのサーブレット
@WebServlet("/DeleteBookServlet")
public class DeleteBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// POSTリクエストの処理: 書籍を削除
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 削除対象の書籍IDを取得
		int id = Integer.parseInt(request.getParameter("id"));

		// DAOで削除処理
		BooksDAO booksDAO = new BooksDAO();
		boolean deleteSuccess = booksDAO.deleteBook(id);

		// 削除結果に応じてメッセージ設定
		if (deleteSuccess) {
			request.setAttribute("message", "書籍が削除されました。");
		} else {
			request.setAttribute("message", "書籍の削除に失敗しました。");
		}

		// 書籍一覧ページにリダイレクト
		response.sendRedirect("BookListServlet");
	}
}
