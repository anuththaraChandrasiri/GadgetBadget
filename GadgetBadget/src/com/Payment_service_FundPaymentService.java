package com;

import model.Payment_service_FundPayment;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/FundPaymentDetails")
public class Payment_service_FundPaymentService {
	
	Payment_service_FundPayment fundPayment = new Payment_service_FundPayment();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readFundPaymentDetails(){
		
		return fundPayment.readFundPaymentDetails();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertFundPayment(@FormParam("pId") String projectId, @FormParam("fundId") String fId,
			@FormParam("researcherId") String rId, @FormParam("amount") String totAmount, @FormParam("paymentStatus") String paymentStatus)
	{
		int pId = Integer.parseInt(projectId);
		int fundId = Integer.parseInt(fId);
		int researcherId = Integer.parseInt(rId);
		float amount = Float.parseFloat(totAmount);
		
		String output = fundPayment.insertFundPaymentRecord(pId, fundId, researcherId, amount, paymentStatus);
		
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFundPaymentRecord(String fundPaymentData)
	{
		//Converting the input string to an XML document
		Document doc = Jsoup.parse(fundPaymentData, "", Parser.xmlParser());
		
		//Reading the value from the element <userId>
		int fundPaymentId = Integer.parseInt(doc.select("fPaymentId").text());
		
		String output = fundPayment.deleteFundPaymentRecord(fundPaymentId);
		
		return output;
	}

}
