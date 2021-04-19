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
	public String insertUser(@FormParam("userName") String userName,
		 @FormParam("email") String email,
		 @FormParam("firstName") String firstName,
		 @FormParam("lastName") String lastName,
		 @FormParam("cardNumber") String cardNumber,
		 @FormParam("CVV") String CVV,
		 @FormParam("expDate") String expDate,
		 @FormParam("password") String password)
	{
		 String output = userObj.insertUser(userName,email,firstName,lastName,cardNumber,CVV,expDate,password);
		return output;
	}

	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(String userData)
	{
		//Convert the input string to a JSON object
		 JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
		
		 //Read the values from the JSON object
		 
		 String userId = userObject.get("userId").getAsString();	
		 String firstName = userObject.get("firstName").getAsString();
		 String lastName = userObject.get("lastName").getAsString();
		 String userName = userObject.get("userName").getAsString();
		 String email = userObject.get("email").getAsString();
		 String cardNumber = userObject.get("cardNumber").getAsString();
		 String CVV = userObject.get("CVV").getAsString();
		 String expDate = userObject.get("expDate").getAsString();
		 String password = userObject.get("password").getAsString();
				
		 String output = userObj.updateUser(userId, firstName, lastName, userName,email,cardNumber,CVV,expDate,password);
	
		 return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String userData)
	{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(userData, "", Parser.xmlParser());
	
		//Read the value from the element <itemID>
		 String userId = doc.select("userId").text();
		 
		 String output = userObj.deleteUser(userId);
		
		 return output;
	}


}
