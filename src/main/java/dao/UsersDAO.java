package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Login;
import model.User;

public class UsersDAO {
  private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/libraryManagement";
  private final String DB_USER = "sa";
  private final String DB_PASS = "";
  
  public User findByLogin(Login login) {
    User Users = null;
    // JDBCドライバを読み込む
    try {
        Class.forName("org.h2.Driver");
    } catch (ClassNotFoundException e) {
        throw new IllegalStateException("JDBCドライバを読み込めませんでした");
    }
    // データベースへ接続
    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

      // SELECT文を準備
      String sql = "SELECT ID, USERNAME, PASSWORD FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";

      PreparedStatement pStmt = conn.prepareStatement(sql);
      pStmt.setString(1, login.getUserId());
      pStmt.setString(2, login.getPass());

      // SELECTを実行し、結果表を取得
      ResultSet rs = pStmt.executeQuery();
      
      if (rs.next()) {
        // ユーザーが存在したらデータを取得
        // そのユーザーを表すUsersインスタンスを生成
        int id = rs.getInt("ID");
        String username = rs.getString("USERNAME");
        String password = rs.getString("PASSWORD");
        
        Users = new User(id, username, password);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }

    return Users;
  }
}