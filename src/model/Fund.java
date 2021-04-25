package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Fund {
	
	private Connection connect()
	 {
		Connection con = null;
		 try
		 {
			 Class.forName("com.mysql.cj.jdbc.Driver");
		
			 //Provide the correct details: DBServer/DBName, username, password
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadget_badget", "root", "");
			 
		 }
		 catch (Exception e)
		 {
		 		e.printStackTrace();
		 		
		 }
		 return con;
	 }
	
	public String insertFund(int pId , int researcherId , int clientId , float amount) {
			 
			 String output = "";
			 
			 try {
				 
				 //connect to database
				 Connection con = connect();
				 
				 if(con == null) {
					 
					 return "Error while connectiong to database";
				 }
				 
					 
					 //create query
					 String query = "INSERT INTO fund(`fundId`,`pId`,`researcherId`,`clientId`,`amount`) VALUES (? ,? ,? ,? ,? )" ;
					 
					 //create prepared statement
					 PreparedStatement pstmt = con.prepareStatement(query);
					 
					 //binding values
					 pstmt.setInt(1,0);
					 pstmt.setInt(2,pId);
					 pstmt.setInt(3,researcherId);
					 pstmt.setInt(4,clientId);
					 pstmt.setFloat(5,amount);
					 
					 //execute query
					 pstmt.executeUpdate();
					 
					 //close connection with database
					 con.close();
					 
					 output = "Inserted Sucessfully";
					 	 
			 }catch(Exception e) {
				 
				 output = "Error while inserting the item.";
				 System.err.println(e.getMessage());
				 
			 }
			
			 return output;
		 }
		
	 
	public String listFunds() {
		
		String output = "";
		
		try {
			
			Connection con = connect();
			
			if(con == null) {
				
				return "Error while connecting to database ";
			}
			
			// Prepare the html table to be displayed
			output ="<table border='1'>"+
					"<tr>"+
					"<th>Fund ID</th>"+
					"<th>Item Name</th>" +
					"<th>Project ID</th>" +
					"<th>Client ID</th>" +
					"<th>Amount</th>"+
					"</tr>";
			
			String query = " select * from fund ";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set					
			while (rs.next())
			{
				
				int fID = rs.getInt("fundId");
				int pID = rs.getInt("pId");
				int rID = rs.getInt("researcherId");
				int cID = rs.getInt("clientId");
				float amount = rs.getFloat("amount");
				
				// Add into the html table
				output += "<tr><td>" + fID + "</td>";
				output += "<td>" + pID + "</td>";
				output += "<td>" + rID + "</td>";
				output += "<td>" + cID + "</td>";
				output += "<td>" + amount + "</td>";
				
				// buttons
				output += "</form></tr>";
				
			}
			
				con.close();
				
				// Complete the html table
				output += "</table>";
			
			
			
			
		}catch(Exception e) {
			
			output = "Error while reading";
			System.err.println(e.getMessage());
			
		}		
		
		return output;
	}
	
	public String deleteFund(String fid) {
		
		String output = "";
		
		try {
			
			Connection con = connect();
			
			if(con == null) {
				
				return "Error while connecting to database";
			}
			
			String query = "delete from fund where fundId =?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(fid));
			
			// execute the statement
			preparedStmt.execute();
			
			con.close();
			
			output = "Deleted successfully";
			
			
		}catch(Exception e) {
			
			output = "Error, query cannot perform";
			
		}
		
		return output;
	}
	public String updateFund(String fId , String pId , String resId , String cliId , String amount) {
		
		String output = "";
		
		try {
			
			Connection con = connect();
			
			if(con == null) {
				
				
				return "Error connecting to database";
			}
			
			String query = "update fund set amount=? where fundId=? ";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//binding values
			preparedStmt.setFloat(1,Float.parseFloat(amount));
			preparedStmt.setInt(2, Integer.parseInt(fId));
			
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Updated successfully";
			
			
		}catch(Exception e) {
			
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
			 
		}
		
		
		return output;
	}

}