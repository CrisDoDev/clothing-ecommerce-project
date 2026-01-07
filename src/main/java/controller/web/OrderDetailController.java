package controller.web;

import dao.OrderDAO;
import model.OrderDetailInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@WebServlet(name = "OrderDetailController", urlPatterns = {"/order-details"})
public class OrderDetailController extends HttpServlet {
    
    private final OrderDAO orderDAO = new OrderDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String idStr = request.getParameter("id"); // Lấy Order ID
        
        try (PrintWriter out = response.getWriter()) {
            int orderId = Integer.parseInt(idStr);
            List<OrderDetailInfo> list = orderDAO.getOrderDetails(orderId);
            
            NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

            // Tạo các dòng HTML cho bảng
            for (OrderDetailInfo item : list) {
                out.println("<tr class='table_row'>");
                
                // Ảnh & Tên
                out.println("<td class='column-1'>");
                out.println("<div class='how-itemcart1'>");
                out.println("<img src='images/" + item.getProductImage() + "' alt='IMG' style='width: 60px;'>");
                out.println("</div>");
                out.println("</td>");
                
                // Thông tin
                out.println("<td class='column-2' style='font-size: 14px;'>");
                out.println("<b>" + item.getProductName() + "</b>");
                out.println("<br><span style='color: #888;'>Size: " + item.getSizeName() + "</span>");
                out.println("</td>");
                
                // Giá
                out.println("<td class='column-3'>" + nf.format(item.getPrice()) + "</td>");
                
                // Số lượng
                out.println("<td class='column-4' style='text-align: center;'>x" + item.getQuantity() + "</td>");
                
                // Tổng dòng
                out.println("<td class='column-5'>" + nf.format(item.getTotalPrice()) + "</td>");
                
                out.println("</tr>");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}