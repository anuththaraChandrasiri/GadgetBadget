package model;

import java.sql.*;
import java.text.SimpleDateFormat; 
 
public class Product {

	private Connection connect(){
		
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadget2","root", "");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public String addToCart(String pid,String researcherId,Date date,Time time,String totAmount,String status) 
	{		
		String output = "";
		try {
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to the database for reading.";
			}			
		
		String query = "insert into order(`orderId`,`pid`,`researcherId`,`date`,`time`,`totAmount`,`status`)"
				+ "values(?,?,?,?,?,?,?)";
		
		PreparedStatement prepareStmt = con.prepareStatement(query);
		
		prepareStmt.setInt(1, 0);
		prepareStmt.setInt(2,  Integer.parseInt(pid));
		prepareStmt.setInt(3,  Integer.parseInt(researcherId));
		prepareStmt.setDate(4, date);
		prepareStmt.setTime(5, time);
		prepareStmt.setFloat(6, Float.parseFloat(totAmount));
		prepareStmt.setString(7, status);
		
		prepareStmt.execute();
		con.close();
		output = "Item added to the cart successfully";
		
		}
		catch(Exception e) {
			output = "Error occure while inserting the item";
			System.err.print(e.getMessage());
		}
		
		return output;		
	} 
	
	public String viewCart() {
		
		String output = "";
		try {
			Connection con =  connect();
			
			if(con == null) {
				return "Error while connecting to the database for reading.";
			}
			
			output = "<table border ='1'><tr><td>projectID</td>" +
					 "<td>date</td>" +
					 "<td>time</td>" +
					 "<td>TotalAmount</td>" +
					 "<td>status</td>" +
					 "<td>Update</td>" +
					 "<td>Remove</td></tr>"; 
			
			String query = "select * from order";
			Statement stmt = con.createStatement();
			ResultSet rs   = stmt.executeQuery(query);
			
			while(rs.next()) {
				String orderId = Integer.toString(rs.getInt("orderId"));
				String pid = Integer.toString(rs.getInt("pid"));
				String researcherId = Integer.toString(rs.getInt("researcherId"));
				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
			    String date = formatter.format("date"); 
			    Time timeObj = rs.getTime("time");
			    String time = timeObj.toString();
			    String totAmount = Float.toString(rs.getFloat("totAmount"));
			    String status = rs.getString("status");
			
				output += "<tr><td>"+ pid + "</td>";
				output += "<tr><td>"+ date + "</td>";
				output += "<tr><td>"+ time + "</td>";
				output += "<tr><td>"+ totAmount + "</td>";
				output += "<tr><td>"+ status + "</td>";
				
				output += "<td><input name = 'btnUpdate' type = 'button' value = 'Update' class='btn btn-secondary'></td>"
				+ "<td><form method = 'post' action = 'products.jsp'>"
				+ "<input name='btnRemove' type = 'button' value = 'Delete' class='btn btn-danger'>"
				+ "<input name = 'orderId' type = 'hidden' value = '"+orderId+"'>"
				+ "</form></td></tr>";
			}
				con.close();
				output += "</table>";
							
		}
		catch(Exception e) {
			output = "Occure error while reading products";
			System.err.println(e.getMessage()); 
		}
		
		return output;
		 
	}
	
	public String updateCart(String orderId, String pid,String researcherId,String date,String time,String totAmount,String status) 
	{
		String output = "";
		
		try 
		{
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to the database";
			}
			
			String query = "Update order set pid=? ,researcherId=? ,date=? ,time=? ,totAmount=? ,status=? Where orderId=?";
			PreparedStatement stmt = con.prepareStatement(query);
			
			stmt.setInt(1,Integer.parseInt(pid));
			stmt.setInt(2,Integer.parseInt(researcherId));
//			stmt.setDate(3,date);
//			stmt.setTime(4,time);
			stmt.setFloat(5,Float.parseFloat(totAmount));
			stmt.setString(6,status);
			
			stmt.execute();
			con.close();
			output = "Updated successfully";
			
		}
		catch(Exception e) {
			output = "Occure error while updating products";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String deleteCartItem(String orderId) {
		
		String output = "";
		
		try {
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to the database.";
			}
			
			String query = "delete from order where orderId=?";
			PreparedStatement stmt = con.prepareStatement(query);
			
			stmt.setInt(1,Integer.parseInt(orderId));
			
			stmt.execute();
			con.close();
			output = "Product deleted from cart successfully.";
		}
		catch(Exception e) {
			output = "Error while deleting product from cart!!!!.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
}
