package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Research;

@Path("/Research")
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
	@Path("/deleteResearch")
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
	
	@PUT
	@Path("/updateFinished")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateFinishedResearch(String projectDetails)
	{
		 Document researchObject = Jsoup.parse(projectDetails, "", Parser.xmlParser());

		 
		 String projectID = researchObject.select("projectID").text();
		 String topic = researchObject.select("topic").text();
		 String amount = researchObject.select("amount").text();
		 String researcherID = researchObject.select("researcherID").text();

		 
		
		 
		 String output = researchObj.updateFinishedResearch(projectID , topic, amount , researcherID);
		 return output;
	}
	
	@PUT
	@Path("/updateUnfinished")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUninishedResearch(String projectDetails)
	{
		 Document researchObject = Jsoup.parse(projectDetails, "", Parser.xmlParser());

		 
		 String projectID = researchObject.select("projectID").text();
		 String topic = researchObject.select("topic").text();
		 String amount = researchObject.select("amount").text();
		 String researcherID = researchObject.select("researcherID").text();

		 
		
		 
		 String output = researchObj.updateUnfinishedResearch(projectID , topic, amount , researcherID);
		 return output;
	}
	
	@GET
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String readFinishedResearches( String details)
	 {
		 Document researchObject = Jsoup.parse(details, "", Parser.xmlParser());
		 
		 String researcherID = researchObject.select("researcherID").text();

		 return researchObj.readFinishedResearches(researcherID); 
	 } 
	
	@GET
	@Path("/unfinished")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String readUnfinishedResearches( String details)
	 {
		 Document researchObject = Jsoup.parse(details, "", Parser.xmlParser());
		 
		 String researcherID = researchObject.select("researcherID").text();

		 return researchObj.readUnfinishedResearches(researcherID); 
	 } 
	
	
	

}
