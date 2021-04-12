package model;

import java.sql.*;

public class User {
	
	//A common method to connect to the DB
	private Connection connect()
	 {
		Connection con = null;
	 try
	 {
		 Class.forName("com.mysql.jdbc.Driver");
	
		 //Provide the correct details: DBServer/DBName, username, password
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/paf_project","root", "root");
	 }
	 catch (Exception e)
	 {
		 e.printStackTrace();}
	     return con;
	 }

	
	public String readUsers()
	 {
		 String output = "";
		 try
		 {
			 Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database for reading."; }
		 
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>First Name</th><th>Last Name</th>" +
		 "<th>User Name</th>" +
		 "<th>Email</th>" +
		 "<th>Card Number</th>" +
		 "<th>CVV</th>" +
		 "<th>Expiration Date</th>" +
		 "<th>Update</th><th>Remove</th></tr>";
		
		 String query = "select * from user";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
			 String userId = Integer.toString(rs.getInt("userId"));
			 String userName = rs.getString("userName");
			 String email = rs.getString("email");
			 String firstName = rs.getString("firstName");
			 String lastName = rs.getString("lastName");
			 String CardNumber = rs.getString("CardNumber");
			 String CVV = rs.getString("CVV");
			 String expDate = rs.getString("expDate");
			 
			 // Add into the html table
			 output += "<td>" + userName + "</td>";
			 output += "<td>" + email + "</td>";
			 output += "<tr><td>" + firstName + "</td>";
			 output += "<td>" + lastName + "</td>";
			 output += "<td>" + CardNumber + "</td>";
			 output += "<td>" + CVV + "</td>";
			 output += "<td>" + expDate + "</td>";
			 
			 // buttons
			 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
			 + "<td><form method='post' action='items.jsp'>"
			 + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
			 + "<input name='userId' type='hidden' value='" + userId
			 + "'>" + "</form></td></tr>";
	 }
		 con.close();
		 
		 // Complete the html table
		 output += "</table>";
	 }
	 catch (Exception e)
	 {
		 output = "Error while reading the items.";
		 System.err.println(e.getMessage());
	 }
		 return output;
	 }
	
}
