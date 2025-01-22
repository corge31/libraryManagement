package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//ShowRegisterBookServlet: 新規書籍登録画面を表示するサーブレット
@WebServlet("/ShowRegisterBookServlet")
public class ShowRegisterBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// GETリクエストの処理
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 新規登録画面のJSPファイルを指定
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/RegisterBook.jsp");
		// JSPファイルにフォワードして画面を表示
		dispatcher.forward(request, response);
	}

	// POSTリクエストの処理
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// POST リクエストでも GET と同じ処理を実行
		doGet(request, response);
	}
}
