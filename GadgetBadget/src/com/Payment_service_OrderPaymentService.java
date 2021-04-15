package com;

import model.Payment_service_OrderPayment;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/OrderPaymentDetails")
public class Payment_service_OrderPaymentService {
	
	Payment_service_OrderPayment orderPayment = new Payment_service_OrderPayment();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	{
	return orderPayment.readOrderPaymentDetails();
	}		
}
