package com;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

import model.Fund;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Fund")
public class Fund_Services {
	
	Fund fundObj = new Fund(); 
	
	
	@POST
	@Path("/insertfund")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertFund(@FormParam("pId") int pId,
							 @FormParam("researcherId") int reseacherId,
							 @FormParam("clientId") int clientId,
							 @FormParam("amount") float amount)
	{
			
		String output = fundObj.insertFund(pId, reseacherId, clientId, amount);
		
		return output;
	}
	
	@GET
	@Path("/readfunds")
	@Produces(MediaType.TEXT_HTML)
	public String fundList()
	{
	
		return fundObj.listFunds();
	}
	
	@DELETE
	@Path("/deletefund")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFund(String fundData)
	{
		
		 //Convert the input string to an XML document
		 Document doc = Jsoup.parse(fundData, "", Parser.xmlParser());
		
		 //Read the value from the element <fundId>
		 String fundId = doc.select("fundId").text();
		 String output = fundObj.deleteFund(fundId); 
		 
		 return output;
	}
	
	@PUT
	@Path("/updatefund")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateFund(String itemData)
	{
		
		//Convert the input string to a JSON object
		 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		
		 //Read the values from the JSON object 
		 String fid = itemObject.get("fundId").getAsString();
		 String pid = itemObject.get("pId").getAsString();
		 String rid = itemObject.get("researcherId").getAsString();
		 String cid = itemObject.get("clientId").getAsString();
		 String price = itemObject.get("amount").getAsString();
		 
		 String output = fundObj.updateFund(fid , pid , rid , cid , price);
	 
	 return output;
	}

}