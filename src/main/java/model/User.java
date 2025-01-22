package model;

//Userクラス: ユーザー情報を表すモデルクラス
public class User {
	// フィールド（ユーザーID、ユーザー名、パスワード）
	private int id;
	private String username;
	private String pass;

	// コンストラクタ: ユーザー情報を初期化
	public User(int id, String username, String pass) {
		this.id = id;
		this.username = username;
		this.pass = pass;
	}

	// ユーザーIDを取得するゲッターメソッド
	public int getID() {
		return id;
	}

	// ユーザー名を取得するゲッターメソッド
	public String getUserName() {
		return username;
	}

	// パスワードを取得するゲッターメソッド
	public String getPass() {
		return pass;
	}
}
