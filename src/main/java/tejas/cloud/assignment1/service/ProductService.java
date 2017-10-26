package tejas.cloud.assignment1.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.validation.MessageInterpolator.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import tejas.cloud.assignment1.bean.ProductBean;
import tejas.cloud.assignment1.model.Product;
import tejas.cloud.assignment1.model.User;
import tejas.cloud.assignment1.proto.ProductsProtoc.GetProduct;
import tejas.cloud.assignment1.proto.ProductsProtoc.ProductError;
import tejas.cloud.assignment1.proto.ProductsProtoc.ProductSuccess;

public class ProductService {

	List<Product> products = new ArrayList<Product>();;

	public void setProduct() {

		String filename = "products.txt";

		// read from the file pending
		Product p1 = new Product(1, "Ipad", 1000, 10, 10);
		Product p2 = new Product(2, "Apple Watch", 300, 20, 10);
		Product p3 = new Product(3, "Macbook Pro", 2500, 5, 10);
		Product p4 = new Product(4, "Iphone X", 1500, 2, 10);

		products.add(p1);
		products.add(p2);
		products.add(p3);
		products.add(p4);

		Gson gson = new Gson();
		String s = gson.toJson(products);

		FileOutputStream outputStream;

		try {
			outputStream = new FileOutputStream(filename);
			outputStream.write(s.getBytes());
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getProducts() {
		try {
			FileInputStream fis = new FileInputStream("products.txt");
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader bufferedReader = new BufferedReader(isr);
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}
			String json = sb.toString();
			Gson gson = new Gson();
			Type type = new TypeToken<ArrayList<Product>>() {}.getType();
			products = gson.fromJson(json,type);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public HashMap<String, String> getQuote(ProductBean productBean) {

		HashMap<String, String> output = new HashMap<String, String>();

		int RFQID = productBean.getRFQID();
		int AccountID = productBean.getAccountID();
		int productNumber = productBean.getProductNumber();
		String productCategory = productBean.getProductCategory();
		int quantity = productBean.getQuantity();

		Product currentProduct = isPresent(productNumber);

		for (int i = 0; i < UserService.users.size(); i++) {
			if (UserService.users.get(i).getAccountID() == AccountID) {
				break;
			} else if (i == UserService.users.size() - 1) {
				output.put("" + AccountID, "Invalid User");
				return output;
			}
		}

		if (currentProduct == null) {
			output.put("" + productNumber, "There is no such product!");
			return output;
		}
		if (currentProduct.getQtyPresent() < quantity) {
			output.put("" + productNumber, "There is not enough quantity present!");
			return output;
		}

		output.put("Unit Price", currentProduct.getPrice() + "");
		output.put("Price Validation Period", currentProduct.getPriceValidationPeriod() + "");

		return output;
	}

	private Product isPresent(int value) {
		Product p = null;
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getProductNumber() == value) {
				p = products.get(i);
				break;
			}
		}
		return p;
	}

	public ProductSuccess getQuote(GetProduct getProduct) {


		HashMap<String, String> output = new HashMap<String, String>();

		int RFQID = getProduct.getRequestID();
		int AccountID = getProduct.getAccountID();
		int productNumber = getProduct.getProductNumber();
		String productCategory = getProduct.getProductCategory();
		int quantity = getProduct.getQuantity();

		Product currentProduct = isPresent(productNumber);

		ProductSuccess prod;
		//ProductError err;
		
		
		/*for (int i = 0; i < UserService.users.size(); i++) {
			if (UserService.users.get(i).getAccountID() == AccountID) {
				break;
			} else if (i == UserService.users.size() - 1) {
				
				err = ProductError.newBuilder().setError(true).setErrorMessage("Invalid User").build();
				return err;
			}
		}

		if (currentProduct == null) {
			output.put("" + productNumber, "There is no such product!");
			return output;
		}
		if (currentProduct.getQtyPresent() < quantity) {
			output.put("" + productNumber, "There is not enough quantity present!");
			return output;
		}*/

		/*output.put("Unit Price", currentProduct.getPrice() + "");
		output.put("Price Validation Period", currentProduct.getPriceValidationPeriod() + "");*/
		
		prod = ProductSuccess.newBuilder().setUnitPrice(currentProduct.getPrice()).setPriceValidationPeriod(currentProduct.getPriceValidationPeriod()).build();

		return prod;
	
	}

}
