package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Payment_service_ResearcherPayment {
	
	//A common method to connect to the database
	private Connection connect() {
				
		Connection con = null;
				
		try	{
					
			Class.forName("com.mysql.jdbc.Driver");
					
			//Providing correct details to connect : DBServer/DBName, user name, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/payment_service", "root", "");
			
		}catch (Exception e){
					
			e.printStackTrace();
		}
				
		return con;		
	}
	
	//Method to read Researcher payment details
	public String readResearcherPaymentDetails() {
				
		String output = "";
		String success = "Success";
				
		try {
				
			Connection con = connect();
					
			if(con == null) 
				return "Error while connecting to the database for reading.";
					
			//Preparing the HTML table, which is to be displayed
			output = "<table border = '1'>"
					+ "<tr><th>Researcher payment ID</th>"
					+ "<th>Researcher ID</th>"
					+ "<th>Amount</th>"
					+ "<th>Payment status</th>"
					+ "<th>Action</th><tr>";
				
			String query = "select * from researcher_payment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			//Iterate through the rows in the result set
			while(rs.next()) {
						
				Integer researcherPaymentID = rs.getInt("rPaymentId");
				Integer researcherID = rs.getInt("researcherId");
				Float rPaymentAmount = (float) rs.getInt("amount");
				String paymentStatus = rs.getString("paymentStatus");
						
				// Add into the HTML table
				output += "<tr><td>" +  researcherPaymentID + "</td>";
				output += "<td>" + researcherID + "</td>";
				output += "<td>" + rPaymentAmount + "</td>";
				output += "<td>" + paymentStatus + "</td>";
					
				//Displaying the relevant button
				if(paymentStatus.equalsIgnoreCase(success)) {
						
					output += "<td>None</td>";
							
				}
				else {
						
					output += "<td><input name = 'btnDelete' type = 'button' value = 'Delete'></td>";
					
				}
			}	
						
			con.close();
						
			//Completing the HTML table
			output += "</table>";					
					
		} catch (SQLException e) {
					
			output += "Error while reading the researcher payment details.";
			System.err.println(e.getMessage());
		}		
				
		return output ;		
	}	
	
	public String inserteResearcherPaymentRecord(int researcherId, float amount, String paymentStatus){
		
		String output = "";
		
		try{
			
			Connection con = connect();
			
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			
			// create a prepared statement
			String query = " insert into researcher_payment (`researcherId`,`amount`, `paymentStatus`) values (?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, researcherId);
			preparedStmt.setFloat(2, amount);
			preparedStmt.setString(3, paymentStatus);
	
			// execute the statement
			preparedStmt.execute();
			
			con.close();
			
			output = "Inserted successfully";
		}
		catch (Exception e){
			
			output = "Error while inserting the researcher payment record.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String deleteResearcherPaymentRecord(int researcherPaymentId){
		
		String output = "";
		
		try	{
			
			Connection con = connect();
			
			if (con == null){
				
				return "Error while connecting to the database for deleting.";
			}
			
			// Creating a prepared statement
			String query = "delete from researcher_payment where rPaymentId = ?";
	
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// Binding values
			preparedStmt.setInt(1, researcherPaymentId);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting a researcher payment record.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
}
