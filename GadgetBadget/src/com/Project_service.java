package com;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Project;

@Path("/Project")
public class Project_service {
	
	Project researchObj = new Project();
	
	//Adding a new finished research to the system using a form for a specific user
	@POST
	@Path("/add/finished")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertFinishedResearch(@FormParam("reasearcherID") String reasearcherID,
	 @FormParam("topic") String topic,
	 @FormParam("status") String status,
	 @FormParam("price") String price)
	
	{
	 String output = researchObj.insertFinishedProject(reasearcherID, topic, status, price);
	 return output;
	}
//=======================================================================================================================================
	
	//Adding a new unfinished research which requires funds to the system using a json object for a specific user
	@POST
	@Path("/add/unfinished")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUnfinishedProject(String reasearchData) {
		
		 JsonObject researchObject = new JsonParser().parse(reasearchData).getAsJsonObject();
		 
		 String researcherID = researchObject.get("researcherID").getAsString();
		 String topic = researchObject.get("topic").getAsString();
		 String status = researchObject.get("status").getAsString();
		 String amount = researchObject.get("amount").getAsString();
		 
		 String output = researchObj.insertUnfinishedProject(researcherID, topic, status, amount);
		 return output;

		
	}
//=======================================================================================================================================
	
	//Deleting a research from the system using a json object
	@DELETE
	@Path("/delete/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteResearach(String projectID)
	{
		 JsonObject researchObject = new JsonParser().parse(projectID).getAsJsonObject();

	//Read the value from the element <itemID>
		 String projectCode = researchObject.get("projectCode").getAsString();
	 String output = researchObj.deleteProject(projectCode);
	return output;
	}
//=======================================================================================================================================
	
	//Updating a finished research using a XML file to get the details
	@PUT
	@Path("/update/finished")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateFinishedResearch(String projectDetails)
	{
		 Document researchObject = Jsoup.parse(projectDetails, "", Parser.xmlParser());

		 
		 String projectID = researchObject.select("projectID").text();
		 String topic = researchObject.select("topic").text();
		 String amount = researchObject.select("amount").text();
		 String researcherID = researchObject.select("researcherID").text();

		 String output = researchObj.updateFinishedProject(projectID , topic, amount , researcherID);
		 return output;
	}
//=======================================================================================================================================

	//Updating an unfinished research using a XML file to get the details
	@PUT
	@Path("/update/unfinished")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUninishedResearch(String projectDetails)
	{
		 Document researchObject = Jsoup.parse(projectDetails, "", Parser.xmlParser());

		 
		 String projectID = researchObject.select("projectID").text();
		 String topic = researchObject.select("topic").text();
		 String amount = researchObject.select("amount").text();
		 String researcherID = researchObject.select("researcherID").text();

		 String output = researchObj.updateUnfinishedProject(projectID , topic, amount , researcherID);
		 return output;
	}
//=======================================================================================================================================

	//Reading an finished research using a XML file to get the details
	@GET
	@Path("/view/researcher/finished")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String readFinishedResearches(String details) throws IOException
	 {
		 Document researchObject = Jsoup.parse(details, "", Parser.xmlParser());
		 
		 String researcherID = researchObject.select("researcherID").text();




		 return researchObj.readFinishedProjects(researcherID); 
	 } 
//=======================================================================================================================================

	//Reading an unfinished research using a XML file to get the details
	@GET
	@Path("/view/researcher/unfinished")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String readUnfinishedResearches(String details)
	 {
		 Document researchObject = Jsoup.parse(details, "", Parser.xmlParser());
		 
		 String researcherID = researchObject.select("researcherID").text();

		 return researchObj.readUnfinishedProjects(researcherID); 
	 } 
//=======================================================================================================================================

	@GET
	@Path("/view/client/unfinished")
	@Produces(MediaType.TEXT_PLAIN)
	public String readUnfinishedResearches()
	 {
		 		 return researchObj.readUnfinishedProjects(); 
	 }
//========================================================================================================================================
	
	@GET
	@Path("/view/client/finished")
	@Produces(MediaType.TEXT_PLAIN)
	public String readfinishedResearches()
	 {
		 		 return researchObj.readfinishedProjects(); 
	 }
	
	

}
