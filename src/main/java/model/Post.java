package model;

import java.sql.Timestamp;

public class Post {
    private int id;
    private String title;
    private String content;
    private String author;
    private String imagePath;
    private Timestamp createdAt;
    private int likes;
    private boolean deleted;

    // default constructor
    public Post() {}

    public Post(int id, String title, String content, String author, String imagePath, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.imagePath = imagePath;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public int getLikes() { return likes; }
    public void setLikes(int likes) { this.likes = likes; }

    public boolean isDeleted() {return deleted;}

    public void setDeleted(boolean deleted) {this.deleted = deleted;}
}
