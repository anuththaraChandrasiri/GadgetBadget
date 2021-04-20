package com;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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
			
			output = "Error while inserting the details..";
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
			
			output = "Error while deleting the details..";
			System.err.println(e.getMessage());
			
		}
		
		return output;
	}
	
	public String updateProgressDetails() {
		
		String output = "";
		
		try {
			
			Connection con = connect();
			
			if(con == null) {
				
				return "Error while connecting to database";
			}
			
		}catch(Exception e) {
			
			
		}
		
		return output;
	}
	
}
