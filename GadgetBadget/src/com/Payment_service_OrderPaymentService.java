package com;

import model.Payment_service_OrderPayment;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/OrderPaymentDetails")
public class Payment_service_OrderPaymentService {
	
	Payment_service_OrderPayment orderPayment = new Payment_service_OrderPayment();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readOrderPayment(){
	
		return orderPayment.readOrderPaymentDetails();
	}	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertOrderPayment(@FormParam("pId") String projectId, @FormParam("orderId") String oId,
			@FormParam("researcherId") String rId, @FormParam("amount") String totAmount, @FormParam("paymentStatus") String paymentStatus)
	{
		int pId = Integer.parseInt(projectId);
		int orderId = Integer.parseInt(oId);
		int researcherId = Integer.parseInt(rId);
		float amount = Float.parseFloat(totAmount);
		
		String output = orderPayment.insertOrderPaymentRecord(pId, orderId, researcherId, amount, paymentStatus);
		
		return output;
	}	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteOrderPayment(String orderPaymentData)
	{
		//Converting the input string to an XML document
		Document doc = Jsoup.parse(orderPaymentData, "", Parser.xmlParser());
		
		//Reading the value from the element <userId>
		int orderPaymentId = Integer.parseInt(doc.select("oPaymentId").text());
		
		String output = orderPayment.deleteOrderPaymentRecord(orderPaymentId);
		
		return output;
	}
}
