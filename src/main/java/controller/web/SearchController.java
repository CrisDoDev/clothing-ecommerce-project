package controller.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;
import service.ProductService;

/**
 * Servlet implementation class SearchController
 */
@WebServlet(name = "SearchController", urlPatterns = {"/search"})
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        String txtSearch = request.getParameter("txt");
        
        try (PrintWriter out = response.getWriter()) {
            ProductService service = new ProductService();

            List<Product> list = service.searchByName(txtSearch);
            
            if (list != null && !list.isEmpty()) {
                for (Product p : list) {
                   
                    NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                    String price = nf.format(p.getPrice());
                    
                    // In ra HTML từng dòng sản phẩm gợi ý
                    out.println("<li class='header-cart-item flex-w flex-t m-b-12'>");
                    
                    // Ảnh sản phẩm
                    out.println("<div class='header-cart-item-img'>");
                    out.println("<img src='images/" + p.getImageUrl() + "' alt='IMG'>");
                    out.println("</div>");
                    
                    // Thông tin
                    out.println("<div class='header-cart-item-txt p-t-8'>");
                    out.println("<a href='detail?pid=" + p.getId() + "' class='header-cart-item-name m-b-18 hov-cl1 trans-04'>");
                    out.println(p.getName());
                    out.println("</a>");
                    out.println("<span class='header-cart-item-info'>" + price + "</span>");
                    out.println("</div>");
                    
                    out.println("</li>");
                }
            } else {
                out.println("<li class='p-t-10 p-b-10 text-center'>Không tìm thấy sản phẩm nào</li>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
