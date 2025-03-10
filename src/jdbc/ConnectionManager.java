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
			c = DriverManager.getConnection("jdbc:sqlite:./db/NeuroPed.db");
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
			
			String table1 = "CREATE TABLE PATIENT (id INTEGER PRIMARY KEY AUTOINCREMENT, " 
				    + "sex TEXT NOT NULL, "
			        + "name TEXT NOT NULL,"
					+ "surname TEXT NOT NULL, "
					+ "date_of_birth DATE NOT NULL, "
					+ "pathology TEXT NOT NULL, "	
					+ "diagnosis TEXT NOT NULL) ";
			s.executeUpdate(table1);
			
			String table2 = "CREATE TABLE STAFF (id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "name TEXT NOT NULL, "
					+ "surname TEXT NOT NULL, "
					+ "field TEXT NOT NULL, "
					+ "patient_id INTEGER NOT NULL,"
					+ "FOREIGN KEY (patient_id) REFERENCES PATIENT(id) ON DELETE CASCADE)";
			s.executeUpdate(table2);
						
		
			String table3 = "CREATE TABLE TEST (id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "type TEXT NOT NULL,"
					+ "result TEXT NOT NULL,"
					+ "dateOfTest DATE NOT NULL,"
					+ "patient_id INTEGER NOT NULL,"
					+ "treatment_id INTEGER NOT NULL,"
					+ "FOREIGN KEY (patient_id) REFERENCES PATIENT(id) ON DELETE CASCADE,"
					+ "FOREIGN KEY (treatment_id) REFERENCES TREATMENT(id) ON DELETE CASCADE)";
			s.executeUpdate(table3);
			
						
			String table4 = "CREATE TABLE TREATMENT (id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "days INTEGER NOT NULL,"
					+ "hoursPerDay INTEGER NOT NULL,"
					+ "goals TEXT NOT NULL,"
					+ "technology TEXT NOT NULL,"
					+ "patient_id INTEGER NOT NULL,"
					+ "FOREIGN KEY (patient_id) REFERENCES PATIENT(id) ON DELETE CASCADE)";
			s.executeUpdate(table4);
			
			String table5 = "CREATE TABLE TREATMENT_STAFF (treatment_id INTEGER REFERENCES TREATMENT(id),"
					+ "staff_id INTEGER REFERENCES STAFF(id) ON DELETE CASCADE,"
					+ "PRIMARY KEY (treatment_id,staff_id))";			
			s.executeUpdate(table5);
			
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
			System.out.println("Database closed.");
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ConnectionManager conMan = new ConnectionManager();
		conMan.createTables();
		System.out.println("Tables created.");
		conMan.closeConnection();
	}
}
