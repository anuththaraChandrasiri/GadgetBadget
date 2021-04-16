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

}
