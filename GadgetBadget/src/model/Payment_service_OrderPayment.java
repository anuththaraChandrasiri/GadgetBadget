package model;

import java.sql.Connection;
import java.sql.DriverManager;
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

}
