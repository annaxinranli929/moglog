package model.dao;
import java.sql.Connection;
import java.sql.SQLException;

public class DBTest {
    public static void main(String[] args) {
        try {
            Connection conn = model.dao.DBConnectionManager.getConnection();
            System.out.println("✅ Connected to MySQL!");
            conn.close();
        } catch (SQLException e) {
            System.out.println("❌ Connection failed: " + e.getMessage());
        }
    }
}

