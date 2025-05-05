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

    public List<Post> findAll() {
        // 投稿データを格納するためのリストを初期化
        List<Post> postList = new ArrayList<>();

        String sql = "select * from posts ORDER BY created_at DESC";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setTitle(rs.getString("title"));
                post.setContent(rs.getString("content"));
                post.setAuthor(rs.getString("author"));
                post.setImagePath(rs.getString("image_path"));
                post.setCreatedAt(rs.getTimestamp("created_at"));
                postList.add(post);
            }

        } catch (SQLException e) {
            System.err.println("Error posts: " + e.getMessage());
        }

        return postList;
    }
}
