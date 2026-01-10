package service;

import dao.ProductDAO;
import model.Product;
import model.ProductSizes;
import java.text.Normalizer;
import java.util.regex.Pattern;
import java.util.List;

public class ProductService {

    private final ProductDAO productDAO;

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

    //Lấy toàn bộ sản phẩm
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }
    
    // Lấy danh sách size theo ID sản phẩm
    public List<ProductSizes> getProductSizesByProductId(String productId) {
        try {
            int pid = Integer.parseInt(productId);
            return productDAO.getProductSizesByProductId(pid);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }   
 // Filter sản phẩm
    public List<Product> filterProducts(String cid, double min, double max, String sort, int index, int size) {
        return productDAO.filterProducts(cid, min, max, sort, index, size);
    }
    
    // Đếm số lượng sau filter
    public int countProductsByFilter(String cid, double min, double max) {
        return productDAO.countProductsByFilter(cid, min, max);
    }
    
    // Tìm kiếm sản phẩm theo tên
    public List<Product> searchByName(String s) {
    	return productDAO.searchByName(s);
    }
    
 // (ADMIN) Các hàm CRUD
    public void insertProduct(Product p) {
        productDAO.insertProduct(p);
    }

    public void updateProduct(Product p) {
        productDAO.updateProduct(p);
    }

    public void deleteProduct(int id) {
        productDAO.deleteProduct(id);
    }
    
    public void updateStock(int sizeId, int newQuantity) {
        productDAO.updateStock(sizeId, newQuantity);
    }

    
}