
package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Login;
import model.LoginLogic;

//LoginServlet: ログイン処理を担当するサーブレット
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//GETリクエストの処理
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// メインメニュー画面のJSPファイルにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/MeinMenu.jsp");
		dispatcher.forward(request, response);
	}

	//POSTリクエストの処理（ログイン処理）
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータの取得（ユーザーIDとパスワード）
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");

		// ログイン成功フラグの初期化
        boolean result = false;

        // 未入力時はログイン失敗
        if ((userId == null || userId.isEmpty()) || (pass == null || pass.isEmpty())) {
            result = false; // どちらか一方でも未入力なら失敗
        } else {
            // 両方入力されている場合は通常のログイン処理
            Login login = new Login(userId, pass);
            LoginLogic bo = new LoginLogic();
            result = bo.execute(login);
        }

        // ログイン処理の成否によって処理を分岐
        if (result) { // ログイン成功時
            // セッションスコープにユーザーIDを保存
            HttpSession session = request.getSession();
            session.setAttribute("userId", userId);
            // ログイン成功画面にフォワード
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/LoginSuccess.jsp");
            dispatcher.forward(request, response);
        } else { // ログイン失敗時
            // ログイン失敗画面にフォワード
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/LoginFalse.jsp");
            dispatcher.forward(request, response);
        }
	}
}