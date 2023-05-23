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
import ifaces.SurgeonManager;
import transplant.pojos.Surgeon;

public class JDBCSurgeonManager implements SurgeonManager {
	
	private Connection c;
	
	public JDBCSurgeonManager(Connection c) {
		this.c = c;
	}
	
	public JDBCSurgeonManager()  {
		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/TransplantDataBase.db"); //creates the connection
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
		} catch (Exception e) {
			System.out.println("Database access error");
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void insertSurgeon(Surgeon surgeon) {
		try {
			Statement s = c.createStatement();
			String sql = "INSERT INTO SURGEON (name, address, phone, speciality, hiring_date, email) VALUES ('" + surgeon.getName() + "', '"
					+ surgeon.getAddress() + "', " + surgeon.getPhone() + ", '" + surgeon.getSpeciality() + "', " + surgeon.getHiring_date() + ", '"+surgeon.getEmail()+"')";
			s.executeUpdate(sql);
			s.close();
		} catch (SQLException e) {
			System.out.println("Database exception.");
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Surgeon> searchSurgeonByName(String name){
		
		List<Surgeon> list = new ArrayList<Surgeon>();
		try {
			String sql = "SELECT * FROM SURGEON WHERE name LIKE ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, "%" + name + "%"); 
			ResultSet rs = p.executeQuery(); //
			while (rs.next()) {
				// Create a new Surgeon
				Integer id = rs.getInt("id");
				String n = rs.getString("name");
				String adress = rs.getString("adress");
				Integer phone = rs.getInt("phone");
				String speciality = rs.getString("speciality");
				Date hiringDate = rs.getDate("hiring date");
				String email = rs.getString("email");
				Surgeon s = new Surgeon(id, n, adress, phone, speciality, hiringDate,email);
				// IMPORTANT: I don't have the dogs
				// Add the Surgeon to the list
				list.add(s);
			}
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Surgeon getSurgeon(int id) { 
		try {
			String sql = "SELECT * FROM SURGEON WHERE id = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();
			rs.next();
			String name = rs.getString("name");
			String adress = rs.getString("adress");
			Integer phone = rs.getInt("phone");
			String speciality = rs.getString("speciality");
			Date hiringDate = rs.getDate("hiring date");
			String email = rs.getString("email");
			Surgeon s = new Surgeon(id, name, adress, phone, speciality, hiringDate,email);
			rs.close();
			p.close();
			return s;
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public Surgeon getSurgeonByEmail(String email) {
		try {
			String sql = "SELECT * FROM SURGEON WHERE email = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, email);
			ResultSet rs = p.executeQuery();
			
			Integer id = rs.getInt("id");
			String n = rs.getString("name");
			String address = rs.getString("address");
			Integer phone = rs.getInt("phone");
			String speciality = rs.getString("speciality");
			Date hiringDate = rs.getDate("hiring_date");
			String email1 = rs.getString("email");
			Surgeon s = new Surgeon(id,n,address,phone,speciality,hiringDate,email1);
			rs.close();
			p.close();
			return s;
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
		return null;
	}	

}
