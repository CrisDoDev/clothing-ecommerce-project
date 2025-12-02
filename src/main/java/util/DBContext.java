package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBContext {
    
    // Thông tin kết nối database
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=EcommerceDB;encrypt=true;trustServerCertificate=true";
    private static final String DB_USERNAME = "Ecommerce"; // Thay bằng username 
    private static final String DB_PASSWORD = "123"; // Thay bằng password 
    
    // Singleton instance
    private static DBContext instance;
    private Connection connection;
    
    /**
     * Private constructor để implement Singleton pattern
     */
    private DBContext() {
        try {
            // Load SQL Server JDBC Driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
          
        } catch (ClassNotFoundException e) {
            System.err.println("SQL Server JDBC Driver not found!");
            e.printStackTrace();
        }
    }
    
    /**
     * Get singleton instance
     */
    public static DBContext getInstance() {
        if (instance == null) {
            synchronized (DBContext.class) {
                if (instance == null) {
                    instance = new DBContext();
                }
            }
        }
        return instance;
    }
    
    /**
     * Lấy connection tới database
     */
    public Connection getConnection() {
        try {
            // Kiểm tra connection có còn sống không
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                System.out.println("Database connection established successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Failed to connect to database!");
            e.printStackTrace();
        }
        return connection;
    }
    
    /**
     * Đóng connection
     */
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                           }
        } catch (SQLException e) {
                      e.printStackTrace();
        }
    }
    
    /**
     * Test connection
     */
    public static void main(String[] args) {
        DBContext dbContext = DBContext.getInstance();
        Connection conn = dbContext.getConnection();
        
        if (conn != null) {
            System.out.println("successful!");
            dbContext.closeConnection();
        } else {
            System.out.println("failed!");
        }
    }
}