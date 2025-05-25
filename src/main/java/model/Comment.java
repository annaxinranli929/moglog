package model;

import java.sql.Timestamp;

public class Comment {
    private int id;
    private int postId;
    private String author;
    private String content;
    private Timestamp createdAt;

    // 空のコンストラクタ
    public Comment() {
    }

    // 全部入りのコンストラクタ
    public Comment(int id, int postId, String author, String content, Timestamp createdAt) {
        this.id = id;
        this.postId = postId;
        this.author = author;
        this.content = content;
        this.createdAt = createdAt;
    }

    // ゲッター・セッター（必要なだけ）
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
