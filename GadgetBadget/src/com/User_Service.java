package com;

import model.User;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

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
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUser(@FormParam("firstName") String firstName,
		 @FormParam("lastName") String lastName,
		 @FormParam("userName") String userName,
		 @FormParam("email") String email,
		 @FormParam("cardNumber") String cardNumber,
		 @FormParam("CVV") String CVV,
		 @FormParam("expDate") String expDate,
		 @FormParam("password") String password)
	{
		 String output = userObj.insertUser(firstName,lastName,userName,email,cardNumber,CVV,expDate,password);
		return output;
	}



}
