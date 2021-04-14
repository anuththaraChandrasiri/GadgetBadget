package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Research {
	
	private Connection connect()
	 {
		Connection con = null;
		
		try
		{
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/gadget_badget", "root", "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return con;
		
	 }

	public String insertFinishedResearch(String reasearcherID, String topic, String status, String price)
	{
		String output = "";
		
		try
		{
				Connection con = connect();
				
				if (con == null)
				{
					return "Error while connecting to the database for inserting.";
				}
				
			// create a prepared statement
			String query = " insert into project values (NULL,?,?,?)";
			String query2 = " insert into finished values (LAST_INSERT_ID(),?,NULL,NULL,?)";

	 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 PreparedStatement preparedStmt2 = con.prepareStatement(query2);

			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(reasearcherID));
			 preparedStmt.setString(2, topic);
			 preparedStmt.setString(3, status);
			// execute the statement
			 preparedStmt2.setInt(1, Integer.parseInt(reasearcherID));

			 preparedStmt2.setFloat(2, Float.parseFloat(price));

	
			 preparedStmt.execute();
			 preparedStmt2.execute();

			 con.close();
			 
			 output = "Inserted successfully";
			 }
			 catch (Exception e)
			 {
				 output = "Error while inserting the reasearch.";
				 System.err.println(e.getMessage());
			 }
			 return output;
	 }
	
	public String insertUnfinishedResearch(String researcherID, String topic, String status, String amount)
	{
		String output = "";
		
		try
		{
				Connection con = connect();
				
				if (con == null)
				{
					return "Error while connecting to the database for inserting.";
				}
				
			// create a prepared statement
			String query = " insert into project values (NULL,?,?,?)";
			String query2 = " insert into unfinished values (LAST_INSERT_ID(),?,NULL,?)";

	 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 PreparedStatement preparedStmt2 = con.prepareStatement(query2);

			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(researcherID));
			 preparedStmt.setString(2, topic);
			 preparedStmt.setString(3, status);
			 
			 preparedStmt2.setInt(1, Integer.parseInt(researcherID));
			 preparedStmt2.setFloat(2, Float.parseFloat(amount));
			// execute the statement
			

			 preparedStmt.execute();
			 preparedStmt2.execute();

			 con.close();
			 
			 output = "Inserted successfully";
			 }
			 catch (Exception e)
			 {
				 output = "Error while inserting the reasearch.";
				 System.err.println(e.getMessage());
			 }
			 return output;
	 }
	
	public String deleteReasearch(String projectID)
	{
		String output = "";
		
		try
		{
				Connection con = connect();
				
				if (con == null)
				{
					return "Error while connecting to the database for inserting.";
				}
				
			// create a prepared statement
			String query = "delete from project where pid = ?";

	 
			 PreparedStatement preparedStmt = con.prepareStatement(query);

			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(projectID));
			
			// execute the statement
			

			 preparedStmt.execute();

			 con.close();
			 
			 output = "Deleted successfully";
			 }
			 catch (Exception e)
			 {
				 output = "Error while inserting the reasearch.";
				 System.err.println(e.getMessage());
			 }
			 return output;
	 }
	
	public String updateFinishedResearch(String projectID , String topic, String amount , String researcherID)
	{
		String output = "";
		
		try
		{
				Connection con = connect();
				
				if (con == null)
				{
					return "Error while connecting to the database for inserting.";
				}
				
			// create a prepared statement
			String query = "update project p,finished f set p.topic = ? ,f.price=? where  p.pid = ? and p.researcherID = ? and  f.pid = ? and f.researcherID = ?";

	 
			 PreparedStatement preparedStmt = con.prepareStatement(query);

			 // binding values
			 preparedStmt.setString(1, topic);
			 preparedStmt.setInt(3, Integer.parseInt(projectID));
			 preparedStmt.setInt(4, Integer.parseInt(researcherID));
			 preparedStmt.setString(2, amount);

			 preparedStmt.setInt(5, Integer.parseInt(projectID));
			 preparedStmt.setInt(6, Integer.parseInt(researcherID));

			 
			
			// execute the statement
			

			 preparedStmt.execute();

			 con.close();
			 
			 output = "Updated  successfully";
			 }
			 catch (Exception e)
			 {
				 output = "Error while inserting the reasearch.";
				 System.err.println(e.getMessage());
			 }
			 return output;
	 }
	
	public String updateUnfinishedResearch(String projectID , String topic, String amount , String researcherID)
	{
		String output = "";
		
		try
		{
				Connection con = connect();
				
				if (con == null)
				{
					return "Error while connecting to the database for inserting.";
				}
				
			// create a prepared statement
			String query = "update project p,unfinished f set p.topic = ? ,f.requiredAmount=? where  p.pid = ? and p.researcherID = ? and  f.pid = ? and f.researcherID = ?";

	 
			 PreparedStatement preparedStmt = con.prepareStatement(query);

			 // binding values
			 preparedStmt.setString(1, topic);
			 preparedStmt.setInt(3, Integer.parseInt(projectID));
			 preparedStmt.setInt(4, Integer.parseInt(researcherID));
			 preparedStmt.setString(2, amount);

			 preparedStmt.setInt(5, Integer.parseInt(projectID));
			 preparedStmt.setInt(6, Integer.parseInt(researcherID));

			 
			
			// execute the statement
			

			 preparedStmt.execute();

			 con.close();
			 
			 output = "Updated  successfully";
			 }
			 catch (Exception e)
			 {
				 output = "Error while inserting the reasearch.";
				 System.err.println(e.getMessage());
			 }
			 return output;
	 }
	
	
}
