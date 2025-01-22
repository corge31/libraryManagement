package model;

import java.sql.Date;
import java.sql.Timestamp;

//Booksクラス: 書籍情報を表すモデルクラス
public class Books {
	// フィールド: 書籍の情報を保持
	private int id;
	private String title;
	private String author;
	private String publisher;
	private Date publishdate;
	private Timestamp created_at;

	// コンストラクタ: 書籍情報を初期化
	public Books(int id, String title, String author, String publisher, Date publishdate, Timestamp created_at) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.publishdate = publishdate;
		this.created_at = created_at;
	}

	// ゲッターメソッド: 各フィールドの値を取得
	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getPublisher() {
		return publisher;
	}

	public Date getPublishdate() {
		return publishdate;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}
}
