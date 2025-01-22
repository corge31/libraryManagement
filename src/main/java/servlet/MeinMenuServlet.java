package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//MeinMenuServlet: メインメニュー画面を表示するためのサーブレット
@WebServlet("/MeinMenuServlet")
public class MeinMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// GETリクエストの処理
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// メインメニュー画面のJSPファイルを指定
		RequestDispatcher dispatcher = request.getRequestDispatcher(
				"WEB-INF/jsp/MeinMenu.jsp");
		// JSPファイルにフォワードして画面を表示
		dispatcher.forward(request, response);
	}
}