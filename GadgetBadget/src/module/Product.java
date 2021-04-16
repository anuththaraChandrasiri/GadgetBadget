package module;

import java.sql.*; 
 
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
	
	public String addToCart(String pid,String researcherId,Date date,Time time,String totAmount,String status) {
		
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
	
}
