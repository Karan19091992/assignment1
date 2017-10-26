package tejas.cloud.assignment1.resource;

import java.util.HashMap;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tejas.cloud.assignment1.bean.ProductBean;
import tejas.cloud.assignment1.proto.ProductsProtoc.GetProduct;
import tejas.cloud.assignment1.proto.ProductsProtoc.ProductSuccess;
import tejas.cloud.assignment1.service.ProductService;
import tejas.cloud.assignment1.service.UserService;

@Path("/quote")
public class quote {

	ProductService ps = new ProductService();
	UserService us = new UserService();

	@Path("/json")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, String> getQuote(@BeanParam ProductBean productBean) {

		HashMap<String, String> hm = new HashMap<String, String>();

		// statically filing in values(used file storge)
		ps.getProducts();
		us.setUser();

		hm = ps.getQuote(productBean);

		return hm;
	}

	@Path("/proto")
	@GET
	@Produces("application/protobuf")
	public Response getAllWidgets(@BeanParam ProductBean productBean) {

		// statically filing in values(use mongodb or file storge)
		ps.getProducts();
		us.setUser();

		GetProduct getProduct = GetProduct.newBuilder().setAccountID(productBean.getAccountID())
				.setRequestID(productBean.getRFQID()).setProductCategory(productBean.getProductCategory())
				.setProductNumber(productBean.getProductNumber()).setQuantity(productBean.getQuantity()).build();

		ProductSuccess productSuccess = ps.getQuote(getProduct);

		// return productSuccess.newBuilder().build();

		return Response.ok(productSuccess).build();

		// return Response.created(null).entity(productSuccess.toString()).build();
	}

}
