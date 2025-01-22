package test;

import dao.UsersDAO;
import model.Login;
import model.User;

public class UsersDAOTest {
	public static void main(String[] args) {
		testFindByLoginOK(); // ユーザーが見つかる場合のテスト
		testFindByLoginNG(); // ユーザーが見つからない場合のテスト
	}

	// ユーザーが見つかる場合のテスト
	public static void testFindByLoginOK() {
		// テスト用の正しいログイン情報を作成
		Login login = new Login("admin", "administrator");
		// UsersDAOインスタンスを生成
		UsersDAO dao = new UsersDAO();
		// findByLoginメソッドを呼び出し、ユーザー情報を取得
		User result = dao.findByLogin(login);
		// 結果を判定
		if (result != null && (result.getID() == 0)
				&& result.getUserName().equals("admin")
				&& result.getPass().equals("administrator")) {
			System.out.println("testFindByLoginOK:成功しました");
		} else {
			System.out.println("testFindByLoginOK:失敗しました");
		}
	}

	// ユーザーが見つからない場合のテスト
	public static void testFindByLoginNG() {
		// テスト用の不正なログイン情報を作成
		Login login = new Login("admin", "administrator");
		// UsersDAOインスタンスを生成
		UsersDAO dao = new UsersDAO();
		// findByLoginメソッドを呼び出し、ユーザー情報を取得
		User result = dao.findByLogin(login);
		// 結果を判定
		if (result == null) {
			System.out.println("testFindByLoginNG:成功しました");
		} else {
			System.out.println("testFindByLoginNG:失敗しました");
		}
	}
}