package com;

//For REST Service
import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

import model.User;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Users")
public class User_Service
{
	User userObj = new User();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUsers()
	{
		return userObj.readUsers();
	}



}
