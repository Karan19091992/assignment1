package tejas.cloud.assignment1.model;

import com.google.gson.annotations.Expose;

public class Product {
	
	@Expose
	private int productNumber;
	
	@Expose
	private String productName;

	
	public enum Category {
	};

	@Expose
	private int price;
	
	@Expose
	private int qtyPresent;
	
	@Expose
	private int priceValidationPeriod;
	
	public Product() {}

	public Product(int productNumber,String productName, int price, int qtyPresent, int priceValidationPeriod) {
		this.productNumber = productNumber;
		this.productName = productName;
		this.price = price;
		this.qtyPresent = qtyPresent;
		this.priceValidationPeriod = priceValidationPeriod;
	}

	public int getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(int productNumber) {
		this.productNumber = productNumber;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQtyPresent() {
		return qtyPresent;
	}

	public void setQtyPresent(int qtyPresent) {
		this.qtyPresent = qtyPresent;
	}

	public int getPriceValidationPeriod() {
		return priceValidationPeriod;
	}

	public void setPriceValidationPeriod(int priceValidationPeriod) {
		this.priceValidationPeriod = priceValidationPeriod;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}
