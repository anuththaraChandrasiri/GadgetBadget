package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Payment_service_OrderPayment {
	
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
		
	//Method to read Order payment details
	public String readOrderPaymentDetails() {
			
		String output = "";
		String success = "Success";
			
		try {
			
			Connection con = connect();
				
			if(con == null) 
				return "Error while connecting to the database for reading.";
				
			//Preparing the HTML table, which is to be displayed
			output = "<table border = '1'>"
					+ "<tr><th>Order payment ID</th>"
					+ "<th>Project ID</th>"
					+ "<th>Order ID</th>"
					+ "<th>Researcher ID</th>"
					+ "<th>Amount</th>"
					+ "<th>Payment status</th>"
					+ "<th>Action</th><tr>";
				
			String query = "select * from order_payment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
				
			//Iterate through the rows in the result set
			while(rs.next()) {
					
				Integer orderPaymentID = rs.getInt("oPaymentId");
				Integer projectID = rs.getInt("pId");
				Integer orderID = rs.getInt("orderId");
				Integer researcherID = rs.getInt("researcherId");
				Float orderAmount = (float) rs.getInt("amount");
				String paymentStatus = rs.getString("paymentStatus");
					
				// Add into the HTML table
				output += "<tr><td>" +  orderPaymentID + "</td>";
				output += "<td>" + projectID + "</td>";
				output += "<td>" + orderID + "</td>";
				output += "<td>" + researcherID + "</td>";
				output += "<td>" + orderAmount + "</td>";
				output += "<td>" + paymentStatus + "</td>";
					
				//Displaying the relevant button
				if(paymentStatus.equalsIgnoreCase(success)) {
						
					output += "<td><input name = 'btnPay' type = 'button' value = 'Pay   '></td>";
							
				}
				else {
						
					output += "<td><input name = 'btnDelete' type = 'button' value = 'Delete'></td>";
					
				}
			}	
					
			con.close();
					
			//Completing the HTML table
			output += "</table>";					
				
		} catch (SQLException e) {
				
			output += "Error while reading the order payment details.";
			System.err.println(e.getMessage());
		}		
			
		return output ;		
	}
	
	public String insertOrderPaymentRecord(int pId, int orderId, int researcherId, float amount, String paymentStatus){
		
		String output = "";
		
		try{
			
			Connection con = connect();
			
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			
			// create a prepared statement
			String query = " insert into order_payment (`pId`, `orderId`, `researcherId`, `amount`, `paymentStatus`) values (?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, pId);
			preparedStmt.setInt(2, orderId);
			preparedStmt.setInt(3, researcherId);
			preparedStmt.setFloat(4, amount);
			preparedStmt.setString(5, paymentStatus);
	
			// execute the statement
			preparedStmt.execute();
			
			con.close();
			
			output = "Inserted successfully";
		}
		catch (Exception e){
			
			output = "Error while inserting the order payment record.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String deleteOrderPaymentRecord(int orderPaymentId){
		
		String output = "";
		
		try	{
			
			Connection con = connect();
			
			if (con == null){
				
				return "Error while connecting to the database for deleting.";
			}
			
			// Creating a prepared statement
			String query = "delete from order_payment where oPaymentId = ?";
	
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// Binding values
			preparedStmt.setInt(1, orderPaymentId);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting an order payment record.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
}
