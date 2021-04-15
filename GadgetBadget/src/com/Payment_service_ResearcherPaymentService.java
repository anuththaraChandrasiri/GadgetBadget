package com;

import model.Payment_service_ResearcherPayment;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/ResearcherPaymentDetails")
public class Payment_service_ResearcherPaymentService {
	
	Payment_service_ResearcherPayment researcherPayment = new Payment_service_ResearcherPayment();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	{
	return researcherPayment.readResearcherPaymentDetails();
	}	

}
