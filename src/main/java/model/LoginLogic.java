package model;

import dao.UsersDAO;

//LoginLogic: ログイン処理を行うビジネスロジッククラス
public class LoginLogic {
	// ログイン処理を実行するメソッド
	public boolean execute(Login login) {
		// UsersDAOをインスタンス化し、データベースからユーザーを検索
		UsersDAO dao = new UsersDAO();
		User User = dao.findByLogin(login);
		// ユーザーが見つかった場合はtrue、それ以外はfalseを返す
		return User != null;
	}
}