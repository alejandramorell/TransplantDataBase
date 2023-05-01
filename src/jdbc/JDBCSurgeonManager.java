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
	
	Connection c;
	
	public JDBCSurgeonManager()  {
		//TODO connect with the database
		//change the company.db thing
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/dogclininc.db"); //creates the connection
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Database access error");
			e.printStackTrace();
		}
		
	}
	
	private void createTables() {
		//TODO i think afterwards Rodrigo creates a general class where all tables are created so i did it yet but i think he would move this method to the other class in the future
	//Clase 01/05/2023
	String table = "CREATE TABLE surgeons (id INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ "name TEXT NOT NULL, "
			+ "adress TEXT, "
			+ "phone INTEGER, "
			+ "speciality TEXT, "
			+ "hiring_date DATE)"; 
	
	table = "CREATE TABLE patients (id INTEGER PRIMARY KEY AUTOINCREMENT, "
		    + "sex TEXT NOT NULL, "
	        + "name TEXT NOT NULL,"
			+ "surname TEXT, "
			+ "dateOfBirth DATE, "
			+ "diasease TEXT, "
			+ "bloodType TEXT NOT NULL, "
			+ "admissionDate DATE, "	
			//TODO change ingressDate to admissionDate everywhere
			+ "adress TEXT, )";
	//TODO finish creating all the tables of our database 
	}
	
	public void insertSurgeon(Surgeon surgeon) {
		try {
			Statement s = c.createStatement();
			String sql = "INSERT INTO SURGEON (name, adress, phone, speciality, hiring_date) VALUES ('" + surgeon.getName() + "', "
					+ surgeon.getAdress() + ", '" + surgeon.getPhone() + ", '" + surgeon.getSpeciality() + ", '" + surgeon.getHiring_date() +"')";
			s.executeUpdate(sql);
			s.close();
		} catch (SQLException e) {
			System.out.println("Database exception.");
			e.printStackTrace();
		}
	}
	public List<Surgeon> searchSurgeonByName(String name){
		
		List<Surgeon> list = new ArrayList<Surgeon>();
		try {
			String sql = "SELECT * FROM SURGEON WHERE name LIKE ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, "%" + name + "%"); 
			ResultSet rs = p.executeQuery(); //
			while (rs.next()) {
				// Create a new Owner
				Integer id = rs.getInt("id");
				String n = rs.getString("name");
				String adress = rs.getString("email");
				Integer phone = rs.getInt("phone");
				String speciality = rs.getString("speciality");
				Date hiringDate = rs.getDate("hiring date");

				Surgeon s = new Surgeon(id, n, adress, phone, speciality, hiringDate);
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
		// TODO Auto-generated method stub
		return null;
	}
	

}
