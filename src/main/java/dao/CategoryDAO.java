package dao;

import model.Category;
import util.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

	// Lấy tất cả danh mục để hiển thị lên Sidebar/Header
	public List<Category> getAllCategories() {
		List<Category> list = new ArrayList<>();
		String sql = "SELECT * FROM Categories";

		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Category c = new Category(rs.getInt("category_id"), rs.getString("category_name"));
				list.add(c);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
