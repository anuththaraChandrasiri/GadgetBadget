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
	
	public String insertUsers(String userName,String email,String firstName,String lastName,String cardNumber,String CVV,String expDate,String password)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " insert into user(`userId`,`userName`,`email`,`firstName`,`lastName`,`cardNumber`,`CVV`,`expDate`,`password`) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, userName);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, firstName);
			preparedStmt.setString(5, lastName); 
			preparedStmt.setInt(6, Integer.parseInt(cardNumber)); 
			preparedStmt.setInt(7, Integer.parseInt(CVV)); 
			preparedStmt.setString(8, expDate); 
			preparedStmt.setString(9, password); 

			//execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		return output;
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
		 output = "<table border='1'><tr>"+
		 "<th>User Name</th>" +
		 "<th>Email</th>" +
		 "<th>First Name</th><th>Last Name</th>" +
		 "<th>Card Number</th>" +
		 "<th>CVV</th>" +
		 "<th>Expiration Date</th>" +
		 "<th>Password</th></tr>";
		
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
			 String password = rs.getString("password");
			 
			 // Add into the html table
			 output += "<td>" + firstName + "</td>";
			 output += "<td>" + lastName + "</td>";
			 output += "<td>" + userName + "</td>";
			 output += "<td>" + password + "</td>";
			 output += "<td>" + email + "</td>";
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
		 output = "Error while reading Users.";
		 System.err.println(e.getMessage());
	 }
		 return output;
	 }
	
	
	
	public String readUserDetails(int userId)
	 {
		 String output = "";
		 try
		 {
			 Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database for reading."; }
		 
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr>"+
		 "<th>User Name</th>" +
		 "<th>Email</th>" +
		 "<th>First Name</th>"+
		 "<th>Last Name</th>" +
		 "<th>Card Number</th>" +
		 "<th>CVV</th>" +
		 "<th>Expiration Date</th>" +
		 "<th>Password</th></tr>";
		
		 String query = "select * from user where userId = " + userId;
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
			 String userID = Integer.toString(rs.getInt("userId"));
			 String userName = rs.getString("userName");
			 String email = rs.getString("email");
			 String firstName = rs.getString("firstName");
			 String lastName = rs.getString("lastName");
			 String CardNumber = rs.getString("CardNumber");
			 String CVV = rs.getString("CVV");
			 String expDate = rs.getString("expDate");
			 String password = rs.getString("password");
			 
			 // Add into the html table
			 output += "<td>" + userName + "</td>";
			 output += "<td>" + email + "</td>";
			 output += "<td>" + firstName + "</td>";
			 output += "<td>" + lastName + "</td>";
			 output += "<td>" + password + "</td>";
			 output += "<td>" + CardNumber + "</td>";
			 output += "<td>" + CVV + "</td>";
			 output += "<td>" + expDate + "</td>";
			 
	 }
		 con.close();
		 
		 // Complete the html table
		 output += "</table>";
	 }
	 catch (Exception e)
	 {
		 output = "Error while reading Users.";
		 System.err.println(e.getMessage());
	 }
		 return output;
	 }
	
	

	public String updateUser(String userId,String fname, String lname, String uname, String email,String cnumber,String CVV,String expdate,String password)
	{
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database for updating."; }
			
		 	// create a prepared statement
			 String query = "UPDATE user SET userName=?,email=?,firstName=?,lastName=?,cardNumber=?,CVV=?,expDate=?,password=? WHERE userId=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setString(1, uname);
			 preparedStmt.setString(2, email);
		 	 preparedStmt.setString(3, fname);
			 preparedStmt.setString(4, lname);
			 preparedStmt.setInt(5, Integer.parseInt(cnumber));
			 preparedStmt.setInt(6, Integer.parseInt(CVV));
			 preparedStmt.setString(7, expdate);
			 preparedStmt.setString(8, password);
			 preparedStmt.setInt(9, Integer.parseInt(userId));
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
			 output = "Error while updating the User.";
			 System.err.println(e.getMessage());
		 }
		 	return output;
		 }
	
	
	public String deleteUser(String userId)
	 {
		String output = "";
	 try
	 {
		 Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database for deleting."; }
			 
		 	// create a prepared statement
			 String query = "delete from user where userId=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(userId));
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
			 output = "Error while deleting the User.";
			 System.err.println(e.getMessage());
		 }
		 	return output;
		 }
	
}
