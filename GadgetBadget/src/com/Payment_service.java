package com;

import model.Payment;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/PaymentDetails")
public class Payment_service {
	
	Payment payment = new Payment();
	
	//User payment details-------------------------------------------------------------------------------------------------------------------------
	@GET
	@Path("/User")
	@Produces(MediaType.TEXT_HTML)
	public String readPaymentDetails(){
	
		return payment.readPaymentDetails();
	}	
	
	@GET
	@Path("/User/{userId}")
	@Produces(MediaType.TEXT_HTML)
	public String readUserPaymentDetails(@PathParam("userId") int userId){
	
		return payment.readUserPaymentDetails(userId);
	}	
	
	@PUT
	@Path("/User")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUserPaymentDetailsRecord(String paymentDetailsData)
	{
		//Converting the input string to a JSON object
		JsonObject pDetailsObject = new JsonParser().parse(paymentDetailsData).getAsJsonObject();
		
		//Reading the values from the JSON object
		String userId = pDetailsObject.get("userId").getAsString();
		String cardNumber = pDetailsObject.get("cardNumber").getAsString();
		String CVV = pDetailsObject.get("CVV").getAsString();
		String expDate = pDetailsObject.get("expDate").getAsString();
	
		String output = payment.updateUserPaymentDetailsRecord(userId, cardNumber, CVV, expDate);
			
		return output;
	}
}
