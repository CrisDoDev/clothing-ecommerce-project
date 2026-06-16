package controller.web;

import dao.OrderDAO;
import model.Order;
import model.OrderDetail;
import model.User;
import util.SignatureUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import controller.BaseController;

import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.ResourceBundle;

@WebServlet(name = "OrderHistoryController", urlPatterns = { "/order-history" })
public class OrderHistoryController extends BaseController {

	private final OrderDAO orderDAO = new OrderDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		setMessages(request);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		// Nếu chưa đăng nhập -> đá về trang login
		if (user == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		// Lấy danh sách đơn hàng của user này
		List<Order> listOrders = orderDAO.getOrdersByUserId(user.getId());
		//Vòng lặp chạy ngầm quét kiểm tra toàn vẹn đơn hàng cho khách hàng
		for (Order order : listOrders) {
			if ("Đã xác thực".equals(order.getStatus()) && order.getDigitalSignature() != null) {

				List<OrderDetail> rawDetails = orderDAO.getRawDetailsForHash(order.getId());
				StringBuilder detailStrBuilder = new StringBuilder();
				for (int i = 0; i < rawDetails.size(); i++) {
					OrderDetail d = rawDetails.get(i);
					detailStrBuilder.append("pid").append(d.getProductId()).append("_q").append(d.getQuantity())
							.append("_p").append((long) d.getPrice());
					if (i < rawDetails.size() - 1)
						detailStrBuilder.append("|");
				}
				
				String currentHash = SignatureUtil.buildOrderHash(order.getId(), order.getUserId(),
						order.getTotalMoney(), order.getShippingAddress(), detailStrBuilder.toString());
						
				// Xác thực chữ ký số 
				boolean isIntact = SignatureUtil.verifySignature(currentHash, order.getDigitalSignature(),
						order.getPublicKeyText());

				if (!isIntact) {
					order.setStatus("Lỗi: Dữ liệu bất thường");
				}
			}
		}

		// Gửi sang JSP hiển thị
		request.setAttribute("listOrders", listOrders);
		request.getRequestDispatcher("/views/order-history.jsp").forward(request, response);
	}
}