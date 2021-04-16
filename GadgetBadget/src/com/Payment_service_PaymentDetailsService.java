package com;

import model.Payment_service_PaymentDetails;

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
public class Payment_service_PaymentDetailsService {
	
	Payment_service_PaymentDetails paymentDetails = new Payment_service_PaymentDetails();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPaymentDetails(){
	
		return paymentDetails.readPaymentDetails();
	}	
	
	@GET
	@Path("/User/{userId}")
	@Produces(MediaType.TEXT_HTML)
	public String readUserPaymentDetails(@PathParam("userId") int userId){
	
		return paymentDetails.readUserPaymentDetails(userId);
	}	
}
