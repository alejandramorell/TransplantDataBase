package jdbc;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {
	
	private Connection c;
	

	public ConnectionManager() { 
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/TransplantDataBase.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON"); //we activate a FK
			System.out.println("Database connection opened.");
			createTables();
		} catch (Exception e) {
			System.out.println("Database access error");
			e.printStackTrace();
		}
	}
	
	private void createTables() {
		
		try {
			//c.setAutoCommit(false); when i want to make multiple changes at same time, the db is not going to do it inmediatly
			Statement s = c.createStatement();
			String table = "CREATE TABLE SURGEON (id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "name TEXT NOT NULL, "
					+ "address TEXT NOT NULL, "
					+ "phone INTEGER NOT NULL, "
					+ "speciality TEXT NOT NULL, "
					+ "hiring_date DATE NOT NULL, "
					+ "email TEXT NOT NULL)";
			s.executeUpdate(table);
			
			String table2 = "CREATE TABLE PATIENT (id INTEGER PRIMARY KEY, " 
				    + "sex TEXT NOT NULL, "
			        + "name TEXT NOT NULL,"
					+ "surname TEXT NOT NULL, "
					+ "date_of_birth DATE NOT NULL, "
					+ "blood_type TEXT NOT NULL, "
					+ "admission_date DATE NOT NULL, "	
					+ "address TEXT NOT NULL, "
					+ "phone INTEGER NOT NULL) ";
			s.executeUpdate(table2);
		
			String table3 = "CREATE TABLE ORGAN (id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "type TEXT NOT NULL,"
					+ "blood_type TEXT NOT NULL,"
					+ "donor_id INTEGER NOT NULL,"
					+ "FOREIGN KEY (donor_id) REFERENCES DONOR(id) ON DELETE CASCADE)";
			s.executeUpdate(table3);
			
			String table4 = "CREATE TABLE DONOR (id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "name TEXT NOT NULL,"
					+ "address TEXT NOT NULL,"
					+ "phone INTEGER NOT NULL,"
					+ "living_state TEXT NOT NULL)";			
			s.executeUpdate(table4);
			
			String table5 = "CREATE TABLE THEATRE (id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "floor INTEGER NOT NULL,"
					+ "number INTEGER NOT NULL)";			
			s.executeUpdate(table5);
			
			String table6 = "CREATE TABLE TRANSPLANT (id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "registration_date DATE NOT NULL,"
					+ "requested_type TEXT NOT NULL,"
					+ "organ_id INTEGER NOT NULL,"
					+ "theatre_id INTEGER NOT NULL,"
					+ "patient_id INTEGER NOT NULL,"
					+ "FOREIGN KEY (organ_id) REFERENCES ORGAN(id)ON DELETE CASCADE,"
					+ "FOREIGN KEY (patient_id) REFERENCES PATIENT(id)ON DELETE CASCADE,"
					+ "FOREIGN KEY (theatre_id) REFERENCES THEATRE(id) ON DELETE CASCADE)";					
			s.executeUpdate(table6);
			
			String table7 = "CREATE TABLE TRANSPLANT_SURGEON (transplant_id INTEGER REFERENCES TRANSPLANT(id),"
					+ "surgeon_id INTEGER REFERENCES SURGEON(id) ON DELETE CASCADE,"
					+ "PRIMARY KEY (surgeon_id,transplant_id))";			
			s.executeUpdate(table7);
			
			s.close();
			
		} catch (SQLException e) {
			if (e.getMessage().contains("already exist")) {
				return;
			}
			System.out.println("Database error.");
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return c;
	}
	
	public void closeConnection() {
		try {
			c.close();
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
	}
}
