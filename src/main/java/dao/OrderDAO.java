package dao;

import model.Cart;
import model.CartItem;
import model.User;
import util.DBContext;

import java.sql.*;

public class OrderDAO {

    // Trả về Order ID vừa tạo
    // Nhận Connection từ Service để dùng chung Transaction
	public int insertOrder(Connection conn, User user, Cart cart, String address) throws SQLException {
		
	    String query = "INSERT INTO Orders (user_id, total_money, shipping_address, status, order_date) VALUES (?, ?, ?, N'Chờ xử lý', GETDATE())";
	    
	    try (PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
	        ps.setInt(1, user.getId());
	        ps.setDouble(2, cart.getTotalMoney());
	        ps.setString(3, address);

	        int affectedRows = ps.executeUpdate();

	        // Lấy ID sinh ra từ getGeneratedKeys()
	        if (affectedRows > 0) {
	            try (ResultSet rs = ps.getGeneratedKeys()) {
	                if (rs.next()) {
	                    return rs.getInt(1); // Trả về Order ID vừa tạo
	                }
	            }
	        }
	    }
	    return -1; // Trả về -1 nếu lỗi
	}

    //  Insert Order Detail
    public void insertOrderDetail(Connection conn, int orderId, int productId, int sizeId, int quantity, double price) throws SQLException {
        String query = "INSERT INTO OrderDetails (order_id, product_id, size_id, quantity, price) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, orderId);
            ps.setInt(2, productId);
            if (sizeId == -1) ps.setNull(3, java.sql.Types.INTEGER);
            else ps.setInt(3, sizeId);
            ps.setInt(4, quantity);
            ps.setDouble(5, price);
            ps.executeUpdate();
        }
    }
}