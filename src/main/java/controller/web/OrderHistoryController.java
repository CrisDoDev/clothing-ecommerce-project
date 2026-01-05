package controller.web;

import dao.OrderDAO;
import model.Order;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderHistoryController", urlPatterns = { "/order-history" })
public class OrderHistoryController extends HttpServlet {

	private final OrderDAO orderDAO = new OrderDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		// Nếu chưa đăng nhập -> đá về trang login
		if (user == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		// Lấy danh sách đơn hàng của user này
		List<Order> listOrders = orderDAO.getOrdersByUserId(user.getId());

		// Gửi sang JSP hiển thị
		request.setAttribute("listOrders", listOrders);
		request.getRequestDispatcher("/views/order-history.jsp").forward(request, response);
	}
}