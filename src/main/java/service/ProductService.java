package service;

import dao.ProductDAO;
import model.Product;
import java.util.List;

public class ProductService {

    private ProductDAO productDAO;

    public ProductService() {
        productDAO = new ProductDAO();
    }

    //Lấy danh sách sản phẩm có PHÂN TRANG (Cho Home Page)
    public List<Product> getProductsByPage(int index, int size) {
        return productDAO.getProductsByPage(index, size);
    }

    // Đếm tổng số lượng sản phẩm
    public int countTotalProducts() {
        return productDAO.countTotalProducts();
    }

    // Lấy thông tin chi tiết 1 sản phẩm (Product Detail)
    public Product getProductById(String id) {
        return productDAO.getProductById(id);
    }

    //Lấy danh sách sản phẩm theo Danh Mục(Filter)
    public List<Product> getProductsByCategoryId(String cid) {
        return productDAO.getProductsByCategoryId(cid);
    }

    //Lấy toàn bộ sản phẩ
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }
    
}