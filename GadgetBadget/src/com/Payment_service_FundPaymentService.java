package com;

import model.Payment_service_FundPayment;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/FundPaymentDetails")
public class Payment_service_FundPaymentService {
	
	Payment_service_FundPayment fundPayment = new Payment_service_FundPayment();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	{
	return fundPayment.readFundPaymentDetails();
	}

}
