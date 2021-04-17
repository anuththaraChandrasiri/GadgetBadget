package com;

import model.Payment_service_ResearcherPayment;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/ResearcherPaymentDetails")
public class Payment_service_ResearcherPaymentService {
	
	Payment_service_ResearcherPayment researcherPayment = new Payment_service_ResearcherPayment();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readResearcherPayment(){
	
		return researcherPayment.readResearcherPaymentDetails();
	}	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertResearcherPayment(@FormParam("researcherId") String researcherId, @FormParam("amount") String totAmount,
			@FormParam("paymentStatus") String paymentStatus)
	{
		int rId = Integer.parseInt(researcherId);
		float amount = Float.parseFloat(totAmount);
		
		String output = researcherPayment.inserteResearcherPaymentRecord(rId, amount, paymentStatus);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteResearcherPayment(String researcherPaymentData)
	{
		//Converting the input string to an XML document
		Document doc = Jsoup.parse(researcherPaymentData, "", Parser.xmlParser());
		
		//Reading the value from the element <userId>
		int researcherPaymentId = Integer.parseInt(doc.select("rPaymentId").text());
		
		String output = researcherPayment.deleteResearcherPaymentRecord(researcherPaymentId);
		
		return output;
	}


}
