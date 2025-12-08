package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=E_CommerceDB;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "sa";
    private static final String PASSWORD = "123456";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("SQL Server JDBC Driver not found", e);
        }
    }

    // (Tùy chọn) Thêm phương thức đóng kết nối an toàn
    public static void close(Connection conn) {
        if (conn!= null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    //Check kết nối được chưa
    public static void main(String[] args) {
        try {
            System.out.println("Đang kết nối...");
            Connection conn = DBContext.getConnection();
            if (conn != null) {
                System.out.println("KẾT NỐI THÀNH CÔNG!");
                System.out.println("Tên sản phẩm: " + conn.getMetaData().getDatabaseProductName());
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("KẾT NỐI THẤT BẠI!");
            e.printStackTrace();
        }
    }
}

