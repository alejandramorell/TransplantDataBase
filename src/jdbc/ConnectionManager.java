package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import transplant.pojos.Surgeon;

public class ConnectionManager {
	
	private Connection c;
	
	//TODO specify our database and connect to it
	public ConnectionManager() { 
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/TransplantDataBase.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			createTables();
		} catch (Exception e) {
			System.out.println("Database access error");
			e.printStackTrace();
		}
	}
	
	private void createTables() {
		
		try {
			//c.setAutoCommit(false); when i want to make a change, the db is not going to do it inmediatly
			Statement s = c.createStatement();
			String table = "CREATE TABLE SURGEON (id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "name TEXT NOT NULL, "
					+ "adress TEXT NOT NULL, "
					+ "phone INTEGER NOT NULL, "
					+ "speciality TEXT NOT NULL, "
					+ "hiring_date DATE NOT NULL)";
			s.executeUpdate(table);
			String table2 = "CREATE TABLE PATIENT (id INTEGER PRIMARY KEY, " //we deleted the autoincrement in patients id so we can use ir to call each patient eventhough they have same name
				    + "sex TEXT NOT NULL, "
			        + "name TEXT NOT NULL,"
					+ "surname TEXT NOT NULL, "
					+ "date_of_birth DATE NOT NULL, "
					+ "disease TEXT NOT NULL, "
					+ "blood_type TEXT NOT NULL, "
					+ "admission_date DATE NOT NULL, "	
					//TODO change ingressDate to admissionDate everywhere
					+ "address TEXT NOT NULL, "
					+ "phone INTEGER NOT NULL )";
			
			s.executeUpdate(table2);
			String table3 = "CREATE TABLE NURSE (id INTEGER PRIMARY KEY AUTOINCREMENT," 
					+ " name TEXT NOT NULL,"
					+ " phone INTEGER," 
					+ " address TEXT NOT NULL," 
					+ " phone INTEGER NOT NULL)";
			
			s.executeUpdate(table3);
			String table4 = "CREATE TABLE ORGAN (id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " type TEXT NOT NULL,"
					+ "blood_type TEXT NOT NULL,"
					+ "donor_id INTEGER NOT NULL,"
					+ "FOREIGN KEY (donor_id) REFERENCES DONOR(id))";
			
			s.executeUpdate(table4);
			String table5 = "CREATE TABLE DONOR (id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "name TEXT NOT NULL,"
					+ "address TEXT NOT NULL,"
					+ "	phone INTEGER NOT NULL,"
					+ "living_state TEXT NOT NULL)";
			
			s.executeUpdate(table5);
			String table6 = "CREATE TABLE REQUESTED_ORGAN (id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "type TEXT NOT NULL,"
					+ "patient_id INTEGER NOT NULL,"
					+ "FOREIGN KEY (patient_id) REFERENCES PATIENT(id))";
			
			s.executeUpdate(table6);
			String table7 = "CREATE TABLE THEATRE (id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "floor INTEGER NOT NULL,"
					+ "number INTEGER NOT NULL)";
			
			s.executeUpdate(table7);
			String table8 = "CREATE TABLE TRANSPLANT (id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "date DATE NOT NULL,"
					+ "requested_organ INTEGER NOT NULL,"
					+ "organ_id INTEGER NOT NULL,"
					+ "theatre_id INTEGER NOGT NULL,"
					+ "FOREIGN KEY (requested_organ) REFERENCES REQUESTED_ORGAN(id),"
					+ "FOREIGN KEY (organ) REFERENCES ORGAN(id),"
					+ "FOREIGN KEY (theatre_id) REFERENCES THEATRE(id))";
			
			s.executeUpdate(table8);
			String table9 = "CREATE TABLE TRANSPLANT_NURSE (transplant_id INTEGER REFERENCES TRANSPLANT(id),"
					+ "nurse_id INTEGER REFERENCES NURSE(id),"
					+ "PRIMARY KEY (nurse_id,transplant_id))";
			
			s.executeUpdate(table9);
			String table10 = "CREATE TABLE TRANSPLANT_SURGEON (transplant_id INTEGER REFERENCES TRANSPLANT(id),"
					+ "surgeon_id INTEGER REFERENCES SURGEON(id),"
					+ "PRIMARY KEY (surgeon_id,transplant_id))";
			
			s.executeUpdate(table10);
			String table11 = "CREATE TABLE WAITING_LIST (id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "registration_date DATE NOT NULL," //TODO esto no seria admission date como en patient?? Seria necesario?porque como ya esta linkeado a patient...
					+ "urgency TEXT NOT NULL,"
					+ "date_of_birth DATE NOT NULL,"
					+ "patient_id INTEGER NOT NULL,"
					+ "FOREIGN KEY (patient_id) REFERENCES PATIENT(id))"; 
			
			s.executeUpdate(table11);
			s.close();
			//c.commit(); changes that were waiting will be made at the same time
			//later he remove this autocomit things
			
		} catch (SQLException e) {
			// Check if the exception is because the tables already exist
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
