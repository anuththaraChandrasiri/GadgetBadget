package model;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.Progress;
//For JSON
import com.google.gson.*;


//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Progress")
public class Progress_Services {
	
	Progress progressobj = new Progress();
	
	//(`progressId`, `fundId`, `pId`, `researcherId`, `clientId`, `document`, `status`)
	@POST
	@Path("/insertfund")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("fundId") String fId,
							 @FormParam("pId") String pId,
							 @FormParam("reseacherId") String rId,
							 @FormParam("clientId") String cId,
							 @FormParam("document") String doc,
							 @FormParam("status") String status)
	{
			
		String output = progressobj.insertProgressDetails(fId, pId, rId, cId, doc, status);
		
		return output;
	}
	
	@GET
	@Path("/readfunds")
	@Produces(MediaType.TEXT_HTML)
	public String fundList()
	{
	
		return progressobj.readProgressDetails();
	}
	
	@DELETE
	@Path("/deleteprogress")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFund(String progData)
	{
		
		 //Convert the input string to an XML document
		 Document doc = Jsoup.parse(progData, "", Parser.xmlParser());
		
		 //Read the value from the element <fundId>
		 String progID = doc.select("progressId").text();
		 String output = progressobj.deleteProgressDetails(progID);
		 
		 return output;
	}
	
	@PUT
	@Path("/updateprogress")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateFund(String itemData)
	{
		
		 //(`progressId`, `fundId`, `pId`, `researcherId`, `clientId`, `document`, `status`)
		 //Convert the input string to a JSON object
		 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		
		 //Read the values from the JSON object 
		 String prid = itemObject.get("progressId").getAsString();
		 String fid = itemObject.get("fundId").getAsString();
		 String pid = itemObject.get("pId").getAsString();
		 String rid = itemObject.get("researcherId").getAsString();
		 String cid = itemObject.get("clientId").getAsString();
		 String doc = itemObject.get("document").getAsString();
		 String stat = itemObject.get("status").getAsString();
		 
		 String output = progressobj.updateProgressDetails(prid, fid, pid, rid, cid, doc, stat);
	 
	 return output;
	}


}
