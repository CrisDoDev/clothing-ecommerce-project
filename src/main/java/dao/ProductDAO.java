package dao;

import model.Product;
import util.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    //Lấy tất cả sản phẩm
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Products";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRowToProduct(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy sản phẩm có phân trang
    // index: số trang hiện tại
    // size: số lượng sản phẩm trên 1 trang
    public List<Product> getProductsByPage(int index, int size) {
        List<Product> list = new ArrayList<>();
        // Cú pháp SQL Server: Bỏ qua (index-1)*size dòng đầu, lấy tiếp size dòng
        String query = "SELECT * FROM Products ORDER BY product_id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, (index - 1) * size); // Tính toán vị trí bắt đầu
            ps.setInt(2, size);               // Số lượng lấy ra
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRowToProduct(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //Đếm tổng số sản phẩm 
    public int countTotalProducts() {
        String query = "SELECT COUNT(*) FROM Products";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    //Lấy chi tiết 1 sản phẩm theo ID
    public Product getProductById(String id) {
        String query = "SELECT * FROM Products WHERE product_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return mapRowToProduct(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //  Lấy sản phẩm theo Danh mục
    public List<Product> getProductsByCategoryId(String cid) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Products WHERE category_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, cid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRowToProduct(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    private Product mapRowToProduct(ResultSet rs) throws Exception {
        return new Product(
            rs.getInt("product_id"),
            rs.getString("product_name"), 
            rs.getString("description"),
            rs.getDouble("price"),
            rs.getString("image_url"),
            rs.getInt("stock_quantity"),
            rs.getInt("category_id")
        );
    }
}
