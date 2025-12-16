package dao;

import model.Product;
import model.ProductSizes;
import util.DBContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

	// Khai báo DataSource để quản lý pool
	private final DataSource dataSource;

	//  Constructor nhận nguồn dữ liệu từ DBContext
	public ProductDAO() {
		this.dataSource = DBContext.getDataSource();
	}

	// Lấy tất cả sản phẩm
	public List<Product> getAllProducts() {
		List<Product> list = new ArrayList<>();
		String query = "SELECT * FROM Products";

		// 3. Dùng try-with-resources để tự động đóng conn, ps, rs
		try (Connection conn = this.dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				list.add(mapRowToProduct(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// Lấy sản phẩm có phân trang 
	public List<Product> getProductsByPage(int index, int size) {
		List<Product> list = new ArrayList<>();
		
		String query = "SELECT * FROM Products ORDER BY product_id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

		try (Connection conn = this.dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setInt(1, (index - 1) * size); // Vị trí bắt đầu
			ps.setInt(2, size); // Số lượng cần lấy

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					list.add(mapRowToProduct(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// Đếm tổng số sản phẩm
	public int countTotalProducts() {
		String query = "SELECT COUNT(*) FROM Products";

		try (Connection conn = this.dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery()) {

			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Lấy chi tiết 1 sản phẩm theo ID
	public Product getProductById(String id) {
		String query = "SELECT * FROM Products WHERE product_id = ?";
		Product product = null;

		try (Connection conn = this.dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setString(1, id); 

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					product = mapRowToProduct(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	// Lấy sản phẩm theo Danh mục
	public List<Product> getProductsByCategoryId(String cid) {
		List<Product> list = new ArrayList<>();
		String query = "SELECT * FROM Products WHERE category_id = ?";

		try (Connection conn = this.dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setString(1, cid);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					list.add(mapRowToProduct(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// Lấy danh sách các size của 1 sản phẩm
    public List<ProductSizes> getProductSizesByProductId(int productId) {
        List<ProductSizes> list = new ArrayList<>();
        String query = "SELECT * FROM ProductSizes WHERE product_id = ?";

        try (Connection conn = this.dataSource.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, productId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new ProductSizes(
                        rs.getInt("size_id"),
                        rs.getInt("product_id"),
                        rs.getString("size"),
                        rs.getInt("stock_quantity")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
	// --- PHẦN FILTER ---
    // Đếm số lượng sản phẩm sau khi lọc (để chia trang)
    public int countProductsByFilter(String cid, double minPrice, double maxPrice) {
        String sql = "SELECT COUNT(*) FROM Products WHERE 1=1";
        
        if (cid != null && !cid.isEmpty()) {
            sql += " AND category_id = " + cid;
        }
        
        if (minPrice >= 0) sql += " AND price >= " + minPrice;
        if (maxPrice > 0) sql += " AND price <= " + maxPrice;

        try (Connection conn = this.dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Phương thức lọc sản phẩm chính
    public List<Product> filterProducts(String cid, double minPrice, double maxPrice, String sortType, int index, int size) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE 1=1";

        if (cid != null && !cid.isEmpty()) {
            sql += " AND category_id = " + cid;
        }
        
        if (minPrice >= 0) sql += " AND price >= " + minPrice;
        if (maxPrice > 0) sql += " AND price <= " + maxPrice;

        switch (sortType) {
            case "newest":
                sql += " ORDER BY created_date DESC";
                break;
            case "price_asc":
                sql += " ORDER BY price ASC"; 
                break;
            case "price_desc":
                sql += " ORDER BY price DESC"; 
                break;
            default:
                sql += " ORDER BY product_id ASC"; 
                break;
        }

        sql += " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        
        try (Connection conn = this.dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setInt(1, (index - 1) * size);
            ps.setInt(2, size);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRowToProduct(rs)); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // Tìm kiếm sản phẩm theo tên
    public List<Product> searchByName(String s) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Products WHERE product_name COLLATE SQL_Latin1_General_CP1_CI_AI LIKE ?";
        try (Connection conn = this.dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, "%" + s + "%");
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRowToProduct(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

	// Helper method để map dữ liệu
	private Product mapRowToProduct(ResultSet rs) throws SQLException {
		return new Product(rs.getInt("product_id"), rs.getString("product_name"), rs.getString("description"),
				rs.getDouble("price"), rs.getString("image_url"),
				rs.getInt("category_id"));
	}
}