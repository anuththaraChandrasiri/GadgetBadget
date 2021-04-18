package com;
import model.Product;  

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
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	
	
	public String viewCart() 
	 { 
		
	 return obPro.viewCart();
	 
	 }
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN)
	public String addToCart( 
			 @FormParam("pid") String pid, 
			 @FormParam("researcherId") String researcherId, 
			 @FormParam("date") String date,
			 @FormParam("time") Time time,
			 @FormParam("totAmount") String totAmount,
			 @FormParam("status") String status) 
			{ 
			 String output = obPro.addToCart(pid, researcherId, date,time,totAmount,status); 
			return output; 
			}
	
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	
	public String updateCart(String itemData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject cartObject = new JsonParser().parse(itemData).getAsJsonObject(); 
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
	
	public String deleteCartItem(String itemData) 
	{  
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); 
	 
	//Read the value<orderId>
	 String orderId = doc.select("orderId").text(); 
	 String output = obPro.deleteCartItem(orderId); 
	 System.out.print(orderId);
	return output; 
	}

}
