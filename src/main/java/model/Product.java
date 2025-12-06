package model;

public class Product {
	private int product_id;
	private String name;
	private String description;
	private double price;
	private String imageUrl;
	private int stockQuantity;
	private int categoryId;

	public Product() {
	}

	public Product(int product_id, String name, String description, double price, String imageUrl, int stockQuantity,
			int categoryId) {
		this.product_id = product_id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imageUrl = imageUrl;
		this.stockQuantity = stockQuantity;
		this.categoryId = categoryId;
	}

	public int getId() {
		return product_id;
	}

	public void setId(int product_id) {
		this.product_id = product_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "Product [id=" + product_id + ", name=" + name + ", price=" + price + "]";
	}
}