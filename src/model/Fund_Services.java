package model;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//Import Model Class
import com.Fund;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Fund")
public class Fund_Services {
	
	Fund fundObj = new Fund(); 
	
	@POST
	@Path("/insertfund")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("pId") String pId,
							 @FormParam("reseacherId") String resId,
							 @FormParam("clientId") String cliId,
							 @FormParam("amount") String amount)
	{
			
		String output = fundObj.insertFundData(pId, resId, cliId, amount);
		
		return output;
	}
	
	@GET
	@Path("/readfunds")
	@Produces(MediaType.TEXT_HTML)
	public String fundList()
	{
	
		return fundObj.listFunds();
	}

}