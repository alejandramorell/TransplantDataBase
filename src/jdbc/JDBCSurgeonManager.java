package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
		//TODO Auto-generated method stub
		return null;
		
	}
	public Surgeon getSurgeon(int id) {
		return null;
	}
	

}
