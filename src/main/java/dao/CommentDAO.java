package dao;

import model.Comment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
    // 指定された投稿のコメントを取得
    public List<Comment> findByPostId(int postId) throws SQLException {
        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT * FROM comments WHERE post_id = ? ORDER BY created_at ASC";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, postId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Comment c = new Comment(
                        rs.getInt("id"),
                        rs.getInt("post_id"),
                        rs.getString("author"),
                        rs.getString("content"),
                        rs.getTimestamp("created_at")
                );
                comments.add(c);
            }
        }
        return comments;
    }

    // コメントを追加
    public void insert(Comment comment) throws SQLException {
        String sql = "INSERT INTO comments (post_id, author, content) VALUES (?, ?, ?)";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, comment.getPostId());
            ps.setString(2, comment.getAuthor());
            ps.setString(3, comment.getContent());

            ps.executeUpdate();
        }
    }

    public void delete(int commentId) throws SQLException {
        String sql = "DELETE FROM comments WHERE id = ?";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, commentId);
            ps.executeUpdate();
        }
    }
}
