package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import transplant.pojos.Patient;
import transplant.pojos.Transplant;

public class JDBCTransplantManager {
	
	Connection c;
	
	public JDBCTransplantManager()  {
		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/TransplantDataBase.db"); //creates the connection
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Database access error");
			e.printStackTrace();
		}
		
	}	
	
	public void insertTransplant(Transplant transplant) {
		try {
			Statement s = c.createStatement();
			String sql = "INSERT INTO TRANSPLANT (id, date, requested_organ, organ_id, theatre_id)"
					+ " VALUES ('" + transplant.getId() + "',"+ transplant.getRegistrationDate()+"', "+ transplant.getRequestedOrgan() + "', "+ transplant.getOrgan() +"', "+ 
					transplant.getTheatre() +"')";
			s.executeUpdate(sql);
			s.close();
		} catch (SQLException e) {
			System.out.println("Database exception.");
			e.printStackTrace();
		}
	}
	
	//public void showTransplant(Transplant id); 
	
	
	public void updateInformation(Transplant transplant) {
		try {
			String sql = "UPDATE TRANSPLANT SET" + " date = ?, " + " requested_organ = ?, " + " organ_id = ? " + " theatre_id = ? ";
			PreparedStatement p;
			p = c.prepareStatement(sql);
			p.setDate(1, transplant.getRegistrationDate());
			//TODO check if the methods with another type of data need a cast
			p.setRequestedOrgan(2, transplant.getRequestedOrgan());
			p.setOrgan(3, transplant.getOrgan());
			p.setTheatre(4, transplant.getTheatre());
			p.close();
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
	}
	
	

}