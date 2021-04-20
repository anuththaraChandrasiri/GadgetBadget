package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Progress {

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
	
	public String insertProgressDetails(String fundId , String pId , String researcherId , String clientId , String doc , String status ) {
		
		String output = "";
		
		try {
			
			Connection con = connect();
			
			if(con == null) {
				
				return "Error while connecting to database";
			}
			
			String query = "INSERT INTO progress (`progressId`, `fundId`, `pId`, `researcherId`, `clientId`, `document`, `status`) VALUES (?,?,?,?,?,?,?) ";
			
			//create prepared statement
			 PreparedStatement pstmt = con.prepareStatement(query);
			 
			 //binding values
			 pstmt.setInt(1,0);
			 pstmt.setInt(2,Integer.parseInt(fundId));
			 pstmt.setInt(3,Integer.parseInt(pId));
			 pstmt.setInt(4,Integer.parseInt(researcherId));
			 pstmt.setInt(5,Integer.parseInt(clientId));
			 
			 File file = new File(doc);
			 
			 FileInputStream input = new FileInputStream(file);
			 
			 pstmt.setBinaryStream(6,input);
			 
			 pstmt.setString(7,status);
			
		}catch(Exception e) {
			
			output = "Error while inserting the progress details..";
			System.err.println(e.getMessage());
			
		}
		
		return output;
	}
	
	public String deleteProgressDetails(String prgID) {
		
		String output = "";
		
		try {
			
			Connection con = connect();
			
			if(con == null) {
				
				return "Error while connecting to database";
			}
			
			String query = "delete from progress where progressId =?";
			
			PreparedStatement pstmt = con.prepareStatement(query);
			 
			//binding values
			pstmt.setInt(1,Integer.parseInt(prgID));
			 
			// execute the statement
			pstmt.execute();
			
			con.close();
			
			output = "Deleted successfully";
			 
			 
			
		}catch(Exception e) {
			
			output = "Error while deleting the progress details..";
			System.err.println(e.getMessage());
			
		}
		
		return output;
	}
	
	public String updateProgressDetails(String progressId , String fundId , String pId , String researcherId , String clientId , String doc , String status ) {
		
		String output = "";
		
		try {
			
			Connection con = connect();
			
			if(con == null) {
				
				return "Error while connecting to database";
			}
			
			String query = "update progress set status=?  where progressId=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			preparedStmt.setString(1,status);
			preparedStmt.setInt(2, Integer.parseInt(progressId));
			
			// execute the statement
			preparedStmt.executeUpdate();
			con.close();
			
			output = "Updated successfully";
			
			
		}catch(Exception e) {
			
			output = "Error while updating the progress details..";
			System.err.println(e.getMessage());
			
		}
		
		return output;
	}
	
	public String readProgressDetails() {
		
		String output = "";

		try {
			
			Connection con = connect();
			
			if(con == null) {
				
				return "Error while connecting to database";
			}
			
			// Prepare the html table to be displayed
			output ="<table border='1'>"+
					"<tr>"+
					"<th>Progress ID</th>"+
					"<th>Fund ID</th>"+
					"<th>PID</th>"+
					"<th>Research Name</th>" +
					"<th>Client ID</th>" +
					"<th>Document</th>" +
					"<th>Status</th>"+
					"<th>Update</th>"+
					"<th>Delete</th>"
					+"</tr>";
			
			String query = "select * from progress";
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set					
			while (rs.next())
			{
				
				int prgID = rs.getInt("progressId");
				int fID = rs.getInt("fundId");
				int pID = rs.getInt("pId");
				int rID = rs.getInt("researcherId");
				int cID = rs.getInt("clientId");
				InputStream document = rs.getBinaryStream("document");
				String status = rs.getString("status");
				
				// Add into the html table
				output += "<tr><td>" + prgID + "</td>";
				output += "<td>" + fID + "</td>";
				output += "<td>" + pID + "</td>";
				output += "<td>" + rID + "</td>";
				output += "<td>" + cID + "</td>";
				output += "<td>" + document + "</td>";
				output += "<td>" + status + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='items.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='itemID' type='hidden' value='" + fID
						+ "'>" 
						+ "</form></td></tr>";
				
			}
			
				con.close();
				
				// Complete the html table
				output += "</table>";
			
			
			
		}catch(Exception e) {
			
			output = "Error while reading the progress details..";
			System.err.println(e.getMessage());
			
		}
		
		return output;
	}
	
}
