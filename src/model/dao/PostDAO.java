package model.dao;

import model.dto.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {

    public void insertPost(Post post) throws SQLException {
        try (Connection conn = DBConnectionManager.getConnection()) {
            String sql = "INSERT INTO posts (title, content, author, image_path) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getContent());
            ps.setString(3, post.getAuthor());
            ps.setString(4, post.getImagePath());
            ps.executeUpdate();
        }
    }

    public List<Post> findAll() throws SQLException {
        List<Post> posts = new ArrayList<>();
        try (Connection conn = DBConnectionManager.getConnection()) {
            String sql = "SELECT * FROM posts WHERE deleted = 0 ORDER BY id DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Post post = new Post(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("author"),
                        rs.getString("image_path"),
                        rs.getTimestamp("created_at")
                );
                post.setLikes(rs.getInt("likes"));
                post.setDeleted(rs.getBoolean("deleted"));
                posts.add(post);
            }
        }
        return posts;
    }

    public Post findById(int id) throws SQLException {
        try (Connection conn = DBConnectionManager.getConnection()) {
            String sql = "SELECT * FROM posts WHERE id = ? AND deleted = 0";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Post(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("author"),
                        rs.getString("image_path"),
                        rs.getTimestamp("created_at")
                );
            } else {
                return null;
            }
        }
    }

    public List<Post> findByKeyword(String title) throws SQLException {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM posts WHERE title LIKE ? AND deleted = 0 ORDER BY id DESC";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + title + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Post post = new Post(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("author"),
                        rs.getString("image_path"),
                        rs.getTimestamp("created_at")
                );
                post.setLikes(rs.getInt("likes"));
                posts.add(post);
            }
        }
        return posts;
    }

    public int update(Post post) throws SQLException {
        try (Connection conn = DBConnectionManager.getConnection()) {
            String sql = "UPDATE posts SET title = ?, content = ?, author = ?, image_path = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, post.getTitle());
            ps.setString(2, post.getContent());
            ps.setString(3, post.getAuthor());
            ps.setString(4, post.getImagePath());
            ps.setInt(5, post.getId());

            return ps.executeUpdate();
        }
    }

    public void incrementLikes(int postId) throws SQLException {
        try (Connection conn = DBConnectionManager.getConnection()) {
            String sql = "UPDATE posts SET likes = likes + 1 WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, postId);
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        try (Connection conn = DBConnectionManager.getConnection()) {
            String sql = "UPDATE posts SET deleted = 1 WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    /*
    public void restore(int id) throws SQLException {
        try (Connection conn = DBConnectionManager.getConnection()) {
            String sql = "UPDATE posts SET deleted = 0 WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
     */
}
