package model;

public class ProductSizes {
    private int id;
    private int productId;
    private String size;
    private int stockQuantity;

    public ProductSizes() {
    }

    public ProductSizes(int id, int productId, String size, int stockQuantity) {
        this.id = id;
        this.productId = productId;
        this.size = size;
        this.stockQuantity = stockQuantity;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
}