package model;

//Loginクラス: ログイン情報を表すモデルクラス
public class Login {
	// フィールド（ユーザー名とパスワード）
	private String username;
	private String pass;

	//コンストラクタ: ログイン情報を初期化
	public Login(String username, String pass) {
		this.username = username;
		this.pass = pass;
	}

	//ユーザー名を取得するゲッターメソッド
	public String getUserId() {
		return username;
	}

	//パスワードを取得するゲッターメソッド
	public String getPass() {
		return pass;
	}
}