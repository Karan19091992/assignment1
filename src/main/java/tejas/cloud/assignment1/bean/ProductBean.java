package tejas.cloud.assignment1.bean;

import javax.ws.rs.QueryParam;

public class ProductBean {

	private @QueryParam("RFQID") int RFQID;
	private @QueryParam("AccountID") int accountID;
	private @QueryParam("ProductNumber") int productNumber;
	private @QueryParam("ProductCategory") String productCategory;
	private @QueryParam("Quantity") int quantity;

	public int getRFQID() {
		return RFQID;
	}

	public void setRFQID(int rFQID) {
		RFQID = rFQID;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public int getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(int productNumber) {
		this.productNumber = productNumber;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
