package controller.web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;
import model.Product;
import service.CategoryService;
import service.ProductService;

@WebServlet(name = "ProductController", urlPatterns = {"/product"})
public class ProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            ProductService productService = new ProductService();
            CategoryService categoryService = new CategoryService();
            
            //Xử lý Phân trang
            String indexPage = request.getParameter("page");
            int index = 1;
            try {
                if(indexPage != null) index = Integer.parseInt(indexPage);
            } catch (Exception e) { index = 1; }
            
            int pageSize = 12; 
            
            List<Product> listProduct = productService.getProductsByPage(index, pageSize);
            List<Category> listCategory = categoryService.getAllCategories();
            
            //Tính toán số trang
            int totalProduct = productService.countTotalProducts();
            int endPage = totalProduct / pageSize;
            if(totalProduct % pageSize != 0) endPage++;
            
            request.setAttribute("listProduct", listProduct);
            request.setAttribute("listCategory", listCategory);
            request.setAttribute("endPage", endPage);
            request.setAttribute("currentPage", index);
            
            request.getRequestDispatcher("/views/product.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}