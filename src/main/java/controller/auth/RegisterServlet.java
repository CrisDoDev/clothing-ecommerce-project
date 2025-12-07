package controller.auth;


import service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); 
        
        // 1. Lấy dữ liệu Họ tên từ Form
        String fullName = request.getParameter("fullname"); 
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String pass = request.getParameter("password");
        String confirmPass = request.getParameter("confirmPassword");

        // Validate cơ bản
        if (!pass.equals(confirmPass)) {
            request.setAttribute("errorMessage", "Mật khẩu xác nhận không khớp!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        UserService service = new UserService();
        //  Truyền fullName vào hàm register
        boolean isSuccess = service.register(fullName, email, pass, phone);

        if (isSuccess) {
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("errorMessage", "Email này đã được sử dụng!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}