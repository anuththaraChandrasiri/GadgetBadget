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
	@Path("/Users")
	@Produces(MediaType.TEXT_HTML)
	public String readPaymentDetails(){
	
		return payment.readPaymentDetails();
	}	
	
	@GET
	@Path("/Users/User/{userId}")
	@Produces(MediaType.TEXT_HTML)
	public String readUserPaymentDetails(@PathParam("userId") int userId){
	
		return payment.readUserPaymentDetails(userId);
	}	
	
	@PUT
	@Path("/Users/User")
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
	
	//Order payment details-------------------------------------------------------------------------------------------------------------------------

	@GET
	@Path("/OrderPayment")
	@Produces(MediaType.TEXT_HTML)
	public String readOrderPayment(){
	
		return payment.readOrderPaymentDetails();
	}	
	
	@POST
	@Path("/OrderPayment")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertOrderPayment(@FormParam("pId") String projectId, @FormParam("orderId") String oId,
			@FormParam("researcherId") String rId, @FormParam("amount") String totAmount, @FormParam("paymentStatus") String paymentStatus)
	{
		int pId = Integer.parseInt(projectId);
		int orderId = Integer.parseInt(oId);
		int researcherId = Integer.parseInt(rId);
		float amount = Float.parseFloat(totAmount);
		
		String output = payment.insertOrderPaymentRecord(pId, orderId, researcherId, amount, paymentStatus);
		
		return output;
	}	
	
	@DELETE
	@Path("/OrderPayment")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteOrderPayment(String orderPaymentData)
	{
		//Converting the input string to an XML document
		Document doc = Jsoup.parse(orderPaymentData, "", Parser.xmlParser());
		
		//Reading the value from the element <userId>
		int orderPaymentId = Integer.parseInt(doc.select("oPaymentId").text());
		
		String output = payment.deleteOrderPaymentRecord(orderPaymentId);
		
		return output;
	}
	
	//Fund payment details-------------------------------------------------------------------------------------------------------------------------
	
	@GET
	@Path("/FundPayment")
	@Produces(MediaType.TEXT_HTML)
	public String readFundPaymentDetails(){
		
		return payment.readFundPaymentDetails();
	}
	
	@POST
	@Path("/FundPayment")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertFundPayment(@FormParam("pId") String projectId, @FormParam("fundId") String fId,
			@FormParam("researcherId") String rId, @FormParam("amount") String totAmount, @FormParam("paymentStatus") String paymentStatus)
	{
		int pId = Integer.parseInt(projectId);
		int fundId = Integer.parseInt(fId);
		int researcherId = Integer.parseInt(rId);
		float amount = Float.parseFloat(totAmount);
		
		String output = payment.insertFundPaymentRecord(pId, fundId, researcherId, amount, paymentStatus);
		
		return output;
	}
	
	@DELETE
	@Path("/FundPayment")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFundPaymentRecord(String fundPaymentData)
	{
		//Converting the input string to an XML document
		Document doc = Jsoup.parse(fundPaymentData, "", Parser.xmlParser());
		
		//Reading the value from the element <userId>
		int fundPaymentId = Integer.parseInt(doc.select("fPaymentId").text());
		
		String output = payment.deleteFundPaymentRecord(fundPaymentId);
		
		return output;
	}
	
	//Researcher payment details----------------------------------------------------------------------------------------------------------------------
	
	@GET
	@Path("/ResearcherPayment")
	@Produces(MediaType.TEXT_HTML)
	public String readResearcherPayment(){
	
		return payment.readResearcherPaymentDetails();
	}	
	
	@POST
	@Path("/ResearcherPayment")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertResearcherPayment(@FormParam("researcherId") String researcherId, @FormParam("amount") String totAmount,
			@FormParam("paymentStatus") String paymentStatus)
	{
		int rId = Integer.parseInt(researcherId);
		float amount = Float.parseFloat(totAmount);
		
		String output = payment.inserteResearcherPaymentRecord(rId, amount, paymentStatus);
		return output;
	}
	
	@DELETE
	@Path("/ResearcherPayment")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteResearcherPayment(String researcherPaymentData)
	{
		//Converting the input string to an XML document
		Document doc = Jsoup.parse(researcherPaymentData, "", Parser.xmlParser());
		
		//Reading the value from the element <userId>
		int researcherPaymentId = Integer.parseInt(doc.select("rPaymentId").text());
		
		String output = payment.deleteResearcherPaymentRecord(researcherPaymentId);
		
		return output;
	}
	
}