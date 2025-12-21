package controller.auth;

import model.User;
import service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("user") != null) {
			response.sendRedirect("views/home.jsp");
			return;
		}
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("username");
		String pass = request.getParameter("password");

		UserService service = new UserService();
		User user = service.login(email, pass);

		if (user != null) {
		    HttpSession session = request.getSession();
		    session.setAttribute("user", user);
		    session.setMaxInactiveInterval(30 * 60);

		    response.sendRedirect(request.getContextPath() + "/home"); 
		
		} else {
			request.setAttribute("errorMessage", "Sai thông tin đăng nhập!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}