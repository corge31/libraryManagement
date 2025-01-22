package test;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import dao.BooksDAO;
import model.Books;

public class BooksDAOTest {
	public static void main(String[] args) {
		testAddBook(); // 本の追加のテスト
		testFindById(); // IDで本を見つけるテスト
		testUpdateBook(); // 本の更新テスト
		testDeleteBook(); // 本の削除テスト
		testFindAll(); // すべての本を取得するテスト
	}

	// 本の追加のテスト
	public static void testAddBook() {
		BooksDAO dao = new BooksDAO();
		// テスト用の本を作成
		Books book = new Books(0, "Test Title", "Test Author", "Test Publisher", Date.valueOf("2023-01-01"),
				new Timestamp(System.currentTimeMillis()));
		// DAOのaddBookメソッドを呼び出し
		boolean result = dao.addBook(book);
		// 結果の出力
		if (result) {
			System.out.println("testAddBook: 成功しました");
		} else {
			System.out.println("testAddBook: 失敗しました");
		}
	}

	// IDで本を検索するテスト
	public static void testFindById() {
		BooksDAO dao = new BooksDAO();
		// IDが0の本を検索
		Books result = dao.findById(0); // IDが1の本があると仮定
		// 結果の確認
		if (result != null && result.getId() == 0) {
			System.out.println("testFindById: 成功しました");
		} else {
			System.out.println("testFindById: 失敗しました");
		}
	}

	// 本の情報を更新するテスト
	public static void testUpdateBook() {
		BooksDAO dao = new BooksDAO();
		// 更新対象の本を作成
		Books book = new Books(0, "Updated Title", "Updated Author", "Updated Publisher", Date.valueOf("2023-01-01"),
				new Timestamp(System.currentTimeMillis()));
		// DAOのupdateBookメソッドを呼び出し
		boolean result = dao.updateBook(book);
		// 結果の確認
		if (result) {
			System.out.println("testUpdateBook: 成功しました");
		} else {
			System.out.println("testUpdateBook: 失敗しました");
		}
	}

	// 本を削除するテスト
	public static void testDeleteBook() {
		BooksDAO dao = new BooksDAO();
		// IDが0の本を削除
		boolean result = dao.deleteBook(0); // IDが1の本があると仮定
		// 結果の出力
		if (result) {
			System.out.println("testDeleteBook: 成功しました");
		} else {
			System.out.println("testDeleteBook: 失敗しました");
		}
	}

	// すべての本を取得するテスト
	public static void testFindAll() {
		BooksDAO dao = new BooksDAO();
		// DAOのfindAllメソッドを呼び出し
		List<Books> booksList = dao.findAll();
		// 結果の確認と出力
		if (booksList != null && !booksList.isEmpty()) {
			System.out.println("testFindAll: 成功しました");
			// 取得した本の一覧を出力
			for (Books book : booksList) {
				System.out.println(
						"ID: " + book.getId() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor());
			}
		} else {
			System.out.println("testFindAll: 失敗しました");
		}
	}
}