package servlet;

import java.io.IOException;
import java.sql.Date;

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
        try {
            // クライアントから送信された更新対象の書籍情報を取得
            int id = Integer.parseInt(request.getParameter("id"));
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String publisher = request.getParameter("publisher");
            String publishDateStr = request.getParameter("publishdate");

            // エラーメッセージリストを作成
            StringBuilder errorMessage = new StringBuilder();

            // 入力値のバリデーション
            if (title == null || title.trim().isEmpty()) {
                errorMessage.append("タイトルを入力してください。<br>");
            }
            if (author == null || author.trim().isEmpty()) {
                errorMessage.append("著者を入力してください。<br>");
            }

            // 出版日の文字列をSQLのDate型に変換
            Date publishDate = null;
            if (publishDateStr != null && !publishDateStr.isEmpty()) {
                try {
                    publishDate = Date.valueOf(publishDateStr);
                } catch (IllegalArgumentException e) {
                    errorMessage.append("出版日は正しい形式で入力してください。<br>");
                }
            }

            // エラーメッセージが存在する場合
            if (errorMessage.length() > 0) {
                request.setAttribute("errorMessage", errorMessage.toString());
                request.getRequestDispatcher("WEB-INF/jsp/EditBook.jsp").forward(request, response);
                return;
            }

            // 書籍オブジェクトを作成
            Books book = new Books(id, title, author, publisher, publishDate, null);

            // DAOを使用して書籍情報を更新
            BooksDAO booksDAO = new BooksDAO();
            boolean updateSuccess = booksDAO.updateBook(book);

            // 更新結果に応じて処理を分岐
            if (updateSuccess) {
                request.setAttribute("message", "書籍情報が更新されました。");
                request.getRequestDispatcher("WEB-INF/jsp/bookList.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "書籍情報の更新に失敗しました。");
                request.getRequestDispatcher("WEB-INF/jsp/EditBook.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "システムエラーが発生しました。");
            request.getRequestDispatcher("WEB-INF/jsp/EditBook.jsp").forward(request, response);
        }
    }
}

