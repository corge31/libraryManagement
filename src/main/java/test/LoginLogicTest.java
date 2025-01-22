package test;

import model.Login;
import model.LoginLogic;

public class LoginLogicTest {
	public static void main(String[] args) {
		testExecuteOK(); // ログイン成功のテスト
		testExecuteNG(); // ログイン失敗のテスト
	}

	// ログイン成功のテスト
	public static void testExecuteOK() {
		// 正しいユーザーIDとパスワードを使用してLoginオブジェクトを作成
		Login login = new Login("admin", "administrator");
		// ロジッククラスをインスタンス化
		LoginLogic bo = new LoginLogic();
		// ログイン処理を実行
		boolean result = bo.execute(login);
		// 結果を判定
		if (result) {
			System.out.println("testExecuteOK:成功しました");
		} else {
			System.out.println("testExecuteOK:失敗しました");
		}
	}

	// ログイン失敗のテスト
	public static void testExecuteNG() {
		// 正しくないユーザーIDとパスワードを使用してLoginオブジェクトを作成
		Login login = new Login("admin", "administrator");
		// ロジッククラスをインスタンス化
		LoginLogic bo = new LoginLogic();
		// ログイン処理を実行
		boolean result = bo.execute(login);
		// 結果を判定
		if (!result) {
			System.out.println("testExecuteNG:成功しました");
		} else {
			System.out.println("testExecuteNG:失敗しました");
		}
	}
}