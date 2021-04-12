package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class testClass {
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String hello()
	 {
	 return "Hello world.";
	 }
	
	

}
