package dao;

import util.DBContext;
import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

	// 1. Lấy thông tin user theo Email
	public User getUserByEmail(String email) {
		String query = "SELECT * FROM Users WHERE email = ?";
		try (Connection conn = DBContext.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("user_id"));
				u.setFullName(rs.getString("full_name"));
				u.setEmail(rs.getString("email"));
				u.setPassword(rs.getString("password")); // Cần lấy pass hash về để so sánh
				u.setPhone(rs.getString("phone"));
				u.setAddress(rs.getString("address"));
				u.setRoleId(rs.getInt("role_id"));
				return u;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 2. Check Email tồn tại (để chặn đăng ký trùng)
	public boolean checkEmailExist(String email) {
		String query = "SELECT count(*) FROM Users WHERE email = ?";
		try (Connection conn = DBContext.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// 3. Đăng ký người dùng mới
	public void register(User user) {
		String query = "INSERT INTO Users (full_name, email, password, phone, role_id) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = DBContext.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, user.getFullName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getPhone());
			ps.setInt(5, 2); // Mặc định role_id = 2 (Customer)
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}