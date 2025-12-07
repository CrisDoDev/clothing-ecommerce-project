package service;

import dao.UserDAO;

import model.User;
import org.mindrot.jbcrypt.BCrypt; // thư viện jBCrypt

public class UserService {
	private UserDAO userDAO = new UserDAO();

	// 1. Hàm ĐĂNG KÝ
	public boolean register(String fullName, String email, String password, String phone) {
		if (userDAO.checkEmailExist(email)) {
			return false;
		}

		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));

		User newUser = new User(fullName, email, hashedPassword, phone, 2);
		userDAO.register(newUser);
		return true;
	}

	// 2. Hàm ĐĂNG NHẬP
	public User login(String email, String password) {
		// Lấy user từ DB lên bằng Email trước
		User user = userDAO.getUserByEmail(email);

		if (user != null) {
			// Dùng BCrypt so sánh password nhập vào và password trong DB
			boolean checkPass = BCrypt.checkpw(password, user.getPassword());

			if (checkPass) {
				return user; // Đăng nhập thành công
			}
		}
		return null; // Sai email hoặc sai pass
	}
}