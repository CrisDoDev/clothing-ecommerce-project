package controller.admin;

import model.Order;
import model.OrderDetail;
import model.OrderDetailInfo;
import service.OrderService;
import service.ProductService;
import util.SignatureUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.BaseController;
import dao.OrderDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "OrderManagementController", urlPatterns = { "/admin/orders" })
public class OrderManagementController extends BaseController {

	private final OrderService orderService = new OrderService();
	private final ProductService productService = new ProductService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// Sử dụng phương thức gộp từ BaseController
		setMessages(request);

		String action = request.getParameter("action");

		// Xem chi tiết
		if ("view_detail".equals(action)) {
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				List<OrderDetailInfo> details = orderService.getOrderDetails(id);

				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();

				// Tự build JSON thủ công
				StringBuilder json = new StringBuilder("[");
				for (int i = 0; i < details.size(); i++) {
					OrderDetailInfo d = details.get(i);
					json.append("{");
					json.append("\"productName\": \"").append(d.getProductName().replace("\"", "\\\"")).append("\",");
					json.append("\"productImage\": \"").append(d.getProductImage()).append("\",");
					json.append("\"sizeName\": \"").append(d.getSizeName() == null ? "N/A" : d.getSizeName())
							.append("\",");
					json.append("\"price\": ").append(d.getPrice()).append(",");
					json.append("\"quantity\": ").append(d.getQuantity());
					json.append("}");

					if (i < details.size() - 1)
						json.append(",");
				}
				json.append("]");

				out.print(json.toString());
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// Xem danh sách
		else {
			String status = request.getParameter("status");
			List<Order> listOrder = orderService.getAllOrders(status);
			OrderDAO orderDAO = new OrderDAO();
			for (Order order : listOrder) {

				if (("Đã xác thực".equals(order.getStatus()) || "Lỗi: Dữ liệu bất thường".equals(order.getStatus())
						|| "Lỗi: Khóa hết hiệu lực".equals(order.getStatus())) && order.getDigitalSignature() != null) {

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
					boolean isIntact = SignatureUtil.verifySignature(currentHash, order.getDigitalSignature(),
							order.getPublicKeyText());

					if (!isIntact) {
						order.setStatus("Lỗi: Dữ liệu bất thường");
						orderDAO.updateOrderHashAndStatus(order.getId(), order.getOrderHash(),
								"Lỗi: Dữ liệu bất thường");
					}
					// Dữ liệu nguyên vẹn nhưng khóa bị hết hiệu lực
					else if ("REVOKED".equals(order.getKeyStatus()) && order.getKeyRevokedAt() != null
							&& order.getSignedAt() != null && !order.getSignedAt().before(order.getKeyRevokedAt())) {

						order.setStatus("Lỗi: Khóa hết hiệu lực");
						orderDAO.updateOrderHashAndStatus(order.getId(), order.getOrderHash(),
								"Lỗi: Khóa hết hiệu lực");
					}
					// Mọi thứ đều hợp lệ
					else {

						if ("Lỗi: Dữ liệu bất thường".equals(order.getStatus())
								|| "Lỗi: Khóa hết hiệu lực".equals(order.getStatus())) {

							order.setStatus("Đã xác thực");

							orderDAO.updateOrderHashAndStatus(order.getId(), order.getOrderHash(), "Đã xác thực");

						}
					}
				}
			}
			request.setAttribute("listOrder", listOrder);
			request.getRequestDispatcher("/views/admin/order-manager.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		// --- CẬP NHẬT TRẠNG THÁI ---
		if ("updateStatus".equals(action)) {
			try {
				int orderId = Integer.parseInt(request.getParameter("orderId"));
				String newStatus = request.getParameter("status");

				// 1. Lấy thông tin đơn hàng hiện tại để kiểm tra trạng thái cũ
				Order currentOrder = orderService.getOrderById(orderId);

				// 2. LOGIC HOÀN KHO:
				if ("Đã hủy".equals(newStatus) && !"Đã hủy".equals(currentOrder.getStatus())) {
					productService.restoreStockForOrder(orderId);
				}

				// 3. Cập nhật trạng thái mới cho đơn hàng
				orderService.updateOrderStatus(orderId, newStatus);

				response.sendRedirect("orders?message=updated");
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("orders?error=fail");
			}
		}
	}
}