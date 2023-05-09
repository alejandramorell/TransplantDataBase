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

import transplant.pojos.Organ;
import transplant.pojos.Patient;
import transplant.pojos.Transplant;
import ifaces.*;

public class JDBCTransplantManager implements TransplantManager{
	
	private Connection c;
	
	public JDBCTransplantManager(Connection c) {
		this.c = c;
	}
	
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
	
	@Override
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
	
	
	@Override
	public void updateInformation(Transplant transplant) {
		try {
			String sql = "UPDATE TRANSPLANT SET" + " date = ?, " + " requested_organ = ?, " + " organ_id = ? " + " theatre_id = ? ";
			PreparedStatement p;
			p = c.prepareStatement(sql);
			p.setDate(1, transplant.getRegistrationDate());
			//TODO check if the methods with another type of data need a cast
			p.setString(2, transplant.getRequestedOrgan().getType());
			p.setString(3, transplant.getOrgan().getType());
			p.setInt(4, transplant.getTheatre().getFloor());
			p.close();
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
	}

	@Override
	public Transplant getTransplant(int id) {
		try {
			String sql = "SELECT * FROM TRANSPLANT WHERE id = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();
			rs.next();
			String sex = rs.getString("sex");
			String n = rs.getString("name");
			String surname = rs.getString("surname");
			Date dob = rs.getDate("date of birth");
			String disease = rs.getString("disease");
			String bloodType = rs.getString("blood type");
			Date admissionDate = rs.getDate("date of admission");
			String adress = rs.getString("adress");
			Integer phone= rs.getInt("phone");
			Patient p1 = new Patient(id, sex, n, surname, dob, disease, bloodType, admissionDate, adress,phone);
			rs.close();
			p.close();
			return p1;
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
		return null;
	}
	


	
	

}
