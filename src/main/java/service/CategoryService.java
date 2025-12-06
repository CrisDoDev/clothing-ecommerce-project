package service;

import model.Category;
import java.util.List;
import dao.CategoryDAO;

public class CategoryService {
	private CategoryDAO categoryDAO;

	public CategoryService() {
		this.categoryDAO = new CategoryDAO();
	}

	public List<Category> getAllCategories() {
		return categoryDAO.getAllCategories();
	}
}
