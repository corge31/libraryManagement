package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.BooksDAO;
import model.Books;

@WebServlet("/BookListServlet")
public class BookListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // GETリクエストの処理: 書籍の検索と一覧表示
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 検索条件を取得
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher");

        // DAOをインスタンス化
        BooksDAO booksDAO = new BooksDAO();
        List<Books> bookList;

        // フォームが送信されたかをチェック
        String searchSubmitted = request.getParameter("searchSubmitted");

        if ("true".equals(searchSubmitted)) {
            // フォームが送信された場合のみ処理を行う
            if ((title == null || title.isEmpty()) &&
                    (author == null || author.isEmpty()) &&
                    (publisher == null || publisher.isEmpty())) {
                // エラーメッセージをリクエストスコープに設定
                request.setAttribute("errorMessage", "検索条件を入力してください");
                // 全ての蔵書を取得
                bookList = booksDAO.findAll();
            } else {
                // 条件に基づいて本を検索
                bookList = booksDAO.findBooks(title, author, publisher);
            }
        } else {
            // フォームが送信されていない場合は、全ての蔵書を取得
            bookList = booksDAO.findAll();
        }

        // 検索結果をリクエストスコープに設定
        request.setAttribute("bookList", bookList);

        // JSPにフォワードして結果を表示
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/bookList.jsp");
        dispatcher.forward(request, response);
    }
}