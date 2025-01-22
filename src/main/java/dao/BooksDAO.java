package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Books;

public class BooksDAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/libraryManagement";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	// 本をIDで取得
	public Books findById(int id) {
		Books book = null;
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT ID, TITLE, AUTHOR, PUBLISHER, PUBLISHDATE, CREATED_AT FROM BOOKS WHERE ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);
			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				int bookId = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String author = rs.getString("AUTHOR");
				String publisher = rs.getString("PUBLISHER");
				Date publishdate = rs.getDate("PUBLISHDATE");
				Timestamp created_at = rs.getTimestamp("CREATED_AT");
				book = new Books(bookId, title, author, publisher, publishdate, created_at);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return book;
	}

	// 本を追加
	public boolean addBook(Books book) {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "INSERT INTO BOOKS (TITLE, AUTHOR, PUBLISHER, PUBLISHDATE, CREATED_AT) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, book.getTitle());
			pStmt.setString(2, book.getAuthor());
			pStmt.setString(3, book.getPublisher());
			pStmt.setDate(4, book.getPublishdate());
			pStmt.setTimestamp(5, book.getCreated_at());

			int result = pStmt.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateBook(Books book) {
	    // バリデーション: タイトルと著者が空の場合は更新しない
	    if (book.getTitle() == null || book.getTitle().trim().isEmpty() ||
	        book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
	        System.out.println("タイトルまたは著者が未入力のため、更新を中止しました。");
	        return false;
	    }

	    boolean isUpdated = false;
	    try {
	        Class.forName("org.h2.Driver"); // ドライバの確認 (H2 データベースを仮定)
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    String sql = "UPDATE BOOKS SET TITLE = ?, AUTHOR = ?, PUBLISHER = ?, PUBLISHDATE = ? WHERE ID = ?";
	    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, book.getTitle());
	        pstmt.setString(2, book.getAuthor());
	        pstmt.setString(3, book.getPublisher());
	        pstmt.setDate(4, book.getPublishdate());
	        pstmt.setInt(5, book.getId());

	        int rowsUpdated = pstmt.executeUpdate();
	        isUpdated = rowsUpdated > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return isUpdated;
	}


	// 本を削除
	public boolean deleteBook(int id) {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "DELETE FROM BOOKS WHERE ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);

			int result = pStmt.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// すべての本を取得
	public List<Books> findAll() {
		List<Books> booksList = new ArrayList<>();
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT ID, TITLE, AUTHOR, PUBLISHER, PUBLISHDATE, CREATED_AT FROM BOOKS";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				Books book = new Books(
						rs.getInt("ID"),
						rs.getString("TITLE"),
						rs.getString("AUTHOR"),
						rs.getString("PUBLISHER"),
						rs.getDate("PUBLISHDATE"),
						rs.getTimestamp("CREATED_AT"));
				booksList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return booksList;
	}

	// 指定された条件に基づいて一部でも完全一致で本を検索する
	public List<Books> findBooks(String title, String author, String publisher) {
	    List<Books> booksList = new ArrayList<>();

	    try {
	        Class.forName("org.h2.Driver");
	    } catch (ClassNotFoundException e) {
	        throw new IllegalStateException("JDBCドライバを読み込めませんでした");
	    }

	    // すべての条件が空の場合は空のリストを返す
	    if ((title == null || title.isEmpty()) &&
	        (author == null || author.isEmpty()) &&
	        (publisher == null || publisher.isEmpty())) {
	        return booksList;
	    }

	    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
	        // SQL文を作成（OR条件を使用）
	        StringBuilder sql = new StringBuilder(
	            "SELECT ID, TITLE, AUTHOR, PUBLISHER, PUBLISHDATE, CREATED_AT FROM BOOKS WHERE ");

	        // 動的に条件を追加
	        boolean isFirstCondition = true;
	        if (title != null && !title.isEmpty()) {
	            sql.append("TITLE = ?");
	            isFirstCondition = false;
	        }
	        if (author != null && !author.isEmpty()) {
	            if (!isFirstCondition) sql.append(" OR ");
	            sql.append("AUTHOR = ?");
	            isFirstCondition = false;
	        }
	        if (publisher != null && !publisher.isEmpty()) {
	            if (!isFirstCondition) sql.append(" OR ");
	            sql.append("PUBLISHER = ?");
	        }

	        PreparedStatement pStmt = conn.prepareStatement(sql.toString());

	        // プレースホルダに検索条件を設定
	        int index = 1;
	        if (title != null && !title.isEmpty()) {
	            pStmt.setString(index++, title);
	        }
	        if (author != null && !author.isEmpty()) {
	            pStmt.setString(index++, author);
	        }
	        if (publisher != null && !publisher.isEmpty()) {
	            pStmt.setString(index++, publisher);
	        }

	        ResultSet rs = pStmt.executeQuery();

	        // 結果をBooksリストに追加
	        while (rs.next()) {
	            Books book = new Books(
	                rs.getInt("ID"),
	                rs.getString("TITLE"),
	                rs.getString("AUTHOR"),
	                rs.getString("PUBLISHER"),
	                rs.getDate("PUBLISHDATE"),
	                rs.getTimestamp("CREATED_AT"));
	            booksList.add(book);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return booksList;
	}

}