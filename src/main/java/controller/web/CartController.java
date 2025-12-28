package controller.web;

import model.Cart;
import model.CartItem;
import model.Product;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "CartController", urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        // Lấy giỏ hàng từ Session
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        // Xử lý Xóa item 
        if ("remove".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String size = request.getParameter("size");
            cart.removeItem(id, size);
            
     
            response.sendRedirect("cart"); 
            return;
        }

        // Mặc định: Chuyển sang trang xem giỏ hàng
        request.getRequestDispatcher("views/shoping-cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        // 1. Thêm vào giỏ 
        if ("add".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                String size = request.getParameter("size");

                Product product = productService.getProductById(String.valueOf(id));
                
                if (product != null) {
                    CartItem item = new CartItem(product, quantity, size);
                    cart.addItem(item);
                }
                
                response.sendRedirect("cart"); 
                
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("home?error=add_failed");
            }
        }
        
        else if ("update".equals(action)) {
         
             response.sendRedirect("cart");
        }
    }
}