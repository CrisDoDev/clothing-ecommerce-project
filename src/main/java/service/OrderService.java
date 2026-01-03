package service;

import dao.OrderDAO;
import dao.ProductDAO;
import model.Cart;
import model.CartItem;
import model.User;
import util.DBContext;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderService {
    private final OrderDAO orderDAO = new OrderDAO();
    private final ProductDAO productDAO = new ProductDAO();

    //Xử lý Transaction mua hàng
    public boolean placeOrder(User user, Cart cart, String address) {
        Connection conn = null;
        try {
            
            conn = DBContext.getDataSource().getConnection();
          
            conn.setAutoCommit(false); 

            // Insert bảng Orders
            int orderId = orderDAO.insertOrder(conn, user, cart, address);
            
            // Duyệt giỏ hàng để Insert Details & Trừ kho
            for (CartItem item : cart.getItems()) {
                int productId = item.getProduct().getId();
                String sizeName = item.getSize();
                
                // Lấy size_id thực tế từ DB
                int sizeId = productDAO.getSizeId(productId, sizeName);
                
                // Insert vào OrderDetails
                orderDAO.insertOrderDetail(conn, orderId, productId, sizeId, item.getQuantity(), item.getProduct().getPrice());
                
                // Trừ tồn kho (Nếu trừ thất bại Rollback)
                boolean updateStockSuccess = productDAO.decreaseStock(conn, sizeId, item.getQuantity());
                if (!updateStockSuccess) {
                    throw new SQLException("Hết hàng hoặc lỗi tồn kho cho sản phẩm: " + item.getProduct().getName());
                }
            }

            // Nếu chạy đến đây ok -> COMMIT
            conn.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            // Nếu có lỗi -> ROLLBACK 
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return false;
        } finally {
            // Đóng kết nối/Trả về pool
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Trả lại trạng thái mặc định
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}