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
	
	//=========== Reading Details of All Clients ================================
	
	@GET
	@Path("/Client")
	@Produces(MediaType.TEXT_HTML)
	public String readClients()
	{
		return userObj.readClients();
	}
	
	//=========== Reading Details of All Researchers ================================
	
	@GET
	@Path("/Researcher")
	@Produces(MediaType.TEXT_HTML)
	public String readResearchers()
	{
		return userObj.readResearchers();
	}
	
	//=========== Reading Details of All Admins ================================
	
	@GET
	@Path("/Admin")
	@Produces(MediaType.TEXT_HTML)
	public String readAdmins()
	{
		return userObj.readAdmins();
	}
	
	
	//=========== Reading Details of a specific User for login ================================
	
	@GET
	@Path("/User/{userName}/Password/{password}")
	@Produces(MediaType.TEXT_HTML)
	public String userLogin(@PathParam("userName") String userName,
			@PathParam("password") String password){
	
		return userObj.readUserDetails(userName,password);
	}
	
	//========================================================================
	//=========== Adding a new client ================================
	
	@POST
	@Path("/Client")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertClient(@FormParam("userName") String userName,
		 @FormParam("email") String email,
		 @FormParam("firstName") String firstName,
		 @FormParam("lastName") String lastName,
		 @FormParam("cardNumber") String cardNumber,
		 @FormParam("cvv") String cvv,
		 @FormParam("expDate") String expDate,
		 @FormParam("password") String password)
	{
		 String output = userObj.insertClient(userName, email, firstName, lastName,cardNumber,cvv,expDate,password);
		return output;
	}
	
	//=========== Adding a new Researcher ================================
	
	@POST
	@Path("/Researcher")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertResearcher(@FormParam("userName") String userName,
		 @FormParam("email") String email,
		 @FormParam("firstName") String firstName,
		 @FormParam("lastName") String lastName,
		 @FormParam("cardNumber") String cardNumber,
		 @FormParam("cvv") String cvv,
		 @FormParam("expDate") String expDate,
		 @FormParam("password") String password)
	{
		 String output = userObj.insertResearcher(userName, email, firstName, lastName,cardNumber,cvv,expDate,password);
		return output;
	}
	
	//=========== Adding a new Researcher ================================
	
	@POST
	@Path("/Admin")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertAdmin(@FormParam("userName") String userName,
		 @FormParam("email") String email,
		 @FormParam("firstName") String firstName,
		 @FormParam("lastName") String lastName,
		 @FormParam("cardNumber") String cardNumber,
		 @FormParam("cvv") String cvv,
		 @FormParam("expDate") String expDate,
		 @FormParam("password") String password)
	{
		 String output = userObj.insertAdmin(userName, email, firstName, lastName,cardNumber,cvv,expDate,password);
		return output;
	}
	
	
	//=========== Updating Details of a given user ================================
	
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
	
	//=========== Deleting Details of a given user ================================
	
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
