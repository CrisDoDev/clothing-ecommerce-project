package controller.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SubmitSignatureController", urlPatterns = { "/submit-signature" })
public class SubmitSignatureController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		try {
			String orderId = request.getParameter("orderId");
			String orderHash = request.getParameter("orderHash");
			String signature = request.getParameter("signature");

			if (orderId != null && signature != null) {
				response.sendRedirect("home?status=processing");
			} else {
				response.sendRedirect("home");
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("home?status=error");
		}
	}
}