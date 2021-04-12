package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import model.Research;

@Path("/Reasearch")
public class Research_service {
	
	Research researchObj = new Research();
	
	@POST
	@Path("/")
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

}
