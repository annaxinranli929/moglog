package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {
    /*private static final String URL = "jdbc:mysql://localhost:3306/moglog";
    private static final String USER = "root";
    private static final String PASS = "moglog";*/

    private static final String URL = "jdbc:mysql://moglog-database.c5u248ca8v8x.ap-northeast-1.rds.amazonaws.com:3306/moglog?useSSL=false&serverTimezone=UTC";
    private static final String USER = "admin";     // or your chosen username
    private static final String PASS = "Moglog123!";  // the password you set


    private static Connection conn;

    public static Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException("JDBC Driver not found", e);
            }
            conn = DriverManager.getConnection(URL, USER, PASS);
        }
        return conn;
    }
}
