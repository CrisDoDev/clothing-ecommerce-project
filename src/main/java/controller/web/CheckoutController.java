package controller.web;

import model.Cart;
import model.User;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "CheckoutController", urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {

    private final OrderService orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Cart cart = (Cart) session.getAttribute("cart");

        // Chưa đăng nhập -> Bắt đăng nhập
        if (user == null) {
            response.sendRedirect("login.jsp?message=login_required");
            return;
        }

        // Giỏ hàng trống -> Về trang chủ
        if (cart == null || cart.getItems().isEmpty()) {
            response.sendRedirect("product");
            return;
        }
              
        // Hiển thị trang checkout
        request.setAttribute("user", user);
        request.getRequestDispatcher("/views/checkout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Cart cart = (Cart) session.getAttribute("cart");

        if (user != null && cart != null) {
            // Lấy địa chỉ giao hàng từ form 
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            
            String fullShippingInfo = address + " - SĐT: " + phone;

            // Gọi Service xử lý Transaction
            boolean success = orderService.placeOrder(user, cart, fullShippingInfo);

            if (success) {
                // Mua thành công -> Xóa giỏ hàng
                session.removeAttribute("cart");
                // Chuyển hướng trang báo thành công
                response.sendRedirect("home?status=order_success");
            } else {
                // Mua thất bại -> Báo lỗi
                request.setAttribute("errorMessage", "Đặt hàng thất bại! Có thể do hết hàng.");
                request.getRequestDispatcher("/views/checkout.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("home");
        }
    }
}