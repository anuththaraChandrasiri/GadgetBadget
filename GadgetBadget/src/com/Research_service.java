package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Research;

@Path("/Reasearch")
public class Research_service {
	
	Research researchObj = new Research();
	
	@POST
	@Path("/add new research")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertFinishedResearch(@FormParam("reasearcherID") String reasearcherID,
	 @FormParam("topic") String topic,
	 @FormParam("status") String status,
	 @FormParam("price") String price)
	
	{
	 String output = researchObj.insertFinishedResearch(reasearcherID, topic, status, price);
	 return output;
	}
	
	@POST
	@Path("/add new research")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUnfinishedProject(String reasearchData) {
		
		 JsonObject itemObject = new JsonParser().parse(reasearchData).getAsJsonObject();
		 
		 String researcherID = itemObject.get("researcherID").getAsString();
		 String topic = itemObject.get("topic").getAsString();
		 String status = itemObject.get("status").getAsString();
		 String amount = itemObject.get("amount").getAsString();
		 
		 String output = researchObj.insertUnfinishedResearch(researcherID, topic, status, amount);
		 return output;


		
	}
	

}
