package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

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
		
		 JsonObject researchObject = new JsonParser().parse(reasearchData).getAsJsonObject();
		 
		 String researcherID = researchObject.get("researcherID").getAsString();
		 String topic = researchObject.get("topic").getAsString();
		 String status = researchObject.get("status").getAsString();
		 String amount = researchObject.get("amount").getAsString();
		 
		 String output = researchObj.insertUnfinishedResearch(researcherID, topic, status, amount);
		 return output;

		
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteResearach(String projectID)
	{
		 JsonObject researchObject = new JsonParser().parse(projectID).getAsJsonObject();

	//Read the value from the element <itemID>
		 String projectCode = researchObject.get("projectCode").getAsString();
	 String output = researchObj.deleteReasearch(projectCode);
	return output;
	}
	

}
