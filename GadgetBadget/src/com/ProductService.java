package com;
import module.Product; 

import java.sql.Date;
import java.sql.Time;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Product")
public class ProductService {
	
	Product obPro = new Product();
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN)
	public String addToCart( 
			 @FormParam("pid") String pid, 
			 @FormParam("researcherId") String researcherId, 
			 @FormParam("date") Date date,
			 @FormParam("time") Time time,
			 @FormParam("totAmount") String totAmount,
			 @FormParam("status") String status) 
			{ 
			 String output = obPro.addToCart(pid, researcherId, date,time,totAmount,status); 
			return output; 
			}
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	
	
	public String viewCart() 
	 { 
		
	 return obPro.viewCart();
	 
	 }
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	
	public String updateCart(String productData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject cartObject = new JsonParser().parse(productData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String orderId = cartObject.get("orderId").getAsString(); 
	 String pid = cartObject.get("pid").getAsString(); 
	 String researcherId = cartObject.get("researcherId").getAsString(); 
	 String date = cartObject.get("date").getAsString(); 
	 String time = cartObject.get("time").getAsString();
	 String totAmount = cartObject.get("totAmount").getAsString();
	 String status = cartObject.get("status").getAsString();
	 
	 String output = obPro.updateCart(orderId, pid, researcherId, date, time,totAmount,status); 
	 
	 return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	
	public String deleteCartItem(String cartData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(cartData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String orderId = doc.select("orderId").text(); 
	 String output = obPro.deleteCartItem(orderId); 
	return output; 
	}

}
