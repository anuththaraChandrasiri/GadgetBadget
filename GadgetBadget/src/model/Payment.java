package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Payment {
		
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
		
		//Method to read Payment details
		public String readPaymentDetails() {
					
			String output = "";
					
			try {
					
				Connection con = connect();
						
				if(con == null) 
					return "Error while connecting to the database for reading.";
						
				//Preparing the HTML table, which is to be displayed
				output = "<table border = '1'>"
						+ "<tr><th>User ID</th>"
						+ "<th>Name</th>"
						+ "<th>Email</th>"
						+ "<th>Card number</th>"
						+ "<th>CVV</th>"
						+ "<th>Expiration date</th><tr>";
				
				String query = "select userId, firstName, lastName, email, cardNumber, CVV, expDate from user";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
						
				//Iterate through the rows in the result set
				while(rs.next()) {
						
					Integer userID = rs.getInt("userId");
					String Fname = rs.getString("firstName");
					String Lname = rs.getString("lastName");
					String email = rs.getString("email");
					String cardNumber = rs.getString("cardNumber");
					Integer CVV = rs.getInt("CVV");
					String expDate = rs.getString("expDate");
					String name = Fname + " " + Lname ;
							
					// Add into the HTML table
					output += "<tr><td>" + userID + "</td>";
					output += "<td>" + name + "</td>";
					output += "<td>" + email + "</td>";
					output += "<td>" + cardNumber + "</td>";
					output += "<td>" + CVV + "</td>";
					output += "<td>" + expDate + "</td>";
				}	
							
			con.close();
							
			//Completing the HTML table
			output += "</table>";					
						
			} catch (SQLException e) {
						
				output += "Error while reading the payment details.";
				System.err.println(e.getMessage());
			}		
					
			return output ;		
		}
		
		//Method to read a specific user's payment details
		public String readUserPaymentDetails(int userId) {
						
			String output = "";
						
			try {
						
				Connection con = connect();
							
				if(con == null) 
					return "Error while connecting to the database for reading.";
							
				//Preparing the HTML table, which is to be displayed
				output = "<table border = '1'>"
						+ "<tr><th>User ID</th>"
						+ "<th>Name</th>"
						+ "<th>Email</th>"
						+ "<th>Card number</th>"
						+ "<th>CVV</th>"
						+ "<th>Expiration date</th>"
						+ "<th>Action</th><tr>";
					
				String query = "select userId, firstName, lastName, email, cardNumber, CVV, expDate from user where userId = " + userId;
			
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);			
				
				if(rs.next()) {
							
					Integer userID = rs.getInt("userId");
					String Fname = rs.getString("firstName");
					String Lname = rs.getString("lastName");
					String email = rs.getString("email");
					String cardNumber = rs.getString("cardNumber");
					Integer CVV = rs.getInt("CVV");
					String expDate = rs.getString("expDate");
					String name = Fname + " " + Lname ;
							
					// Add into the HTML table
					output += "<tr><td>" + userID + "</td>";
					output += "<td>" + name + "</td>";
					output += "<td>" + email + "</td>";
					output += "<td>" + cardNumber + "</td>";
					output += "<td>" + CVV + "</td>";
					output += "<td>" + expDate + "</td>";
							
					//Displaying the buttons
					output += "<td><input name = 'btnUpdate' type = 'button' value = 'Update'></td>";
					output += "<td><input name = 'btnConfirm' type = 'button' value = 'Confirm'></td>";
							
				}	
							
			con.close();
							
			//Completing the HTML table
			output += "</table>";					
							
			} catch (SQLException e) {
							
				output += "Error while reading the user payment details.";
				System.err.println(e.getMessage());
			}		
						
			return output ;		
		}
		
		public String updateUserPaymentDetailsRecord(String userId, String cardNumber, String CVV, String expDate) {
			
			String output = "";
			
			try {
				
				Connection con = connect();
				
				if (con == null) {
					
					return "Error while connecting to the database for updating."; 
				}
				
				// Creating a prepared statement
				String query = "update user set cardNumber = ?, CVV = ?, expDate = STR_TO_DATE(?,'%Y/%m/%d') WHERE userId = ?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// Binding values
				preparedStmt.setString(1, cardNumber);
				preparedStmt.setInt(2, Integer.parseInt(CVV));
				preparedStmt.setString(3, expDate);
				preparedStmt.setInt(4, Integer.parseInt(userId));
					
				//Executing the statement
				preparedStmt.execute();
				con.close();
				output = "Updated successfully";
			}
			catch (Exception e)	{

				output = "Error while updating the user payment details record.";
				System.err.println(e.getMessage());
			}
			
			return output;
		}

}
