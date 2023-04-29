package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {
	
	private Connection c;
	
	//TODO specify our database and connect to it
	public ConnectionManager() { 
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/dogclinic.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			createTables();
		} catch (Exception e) {
			System.out.println("Database access error");
			e.printStackTrace();
		}
	

	}
	
	private void createTables() {
		
		//TODO pol please could you change the queries according to our tables in sql
		try {
			//c.setAutoCommit(false); when i want to make a change, the db is not going to do it inmediatly
			Statement s = c.createStatement();
			String table = "CREATE TABLE owners (id INTEGER PRIMARY KEY AUTOINCREMENT," + " name TEXT NOT NULL,"
					+ " phone INTEGER," + " email TEXT NOT NULL)";
			s.executeUpdate(table);
			String table2 = "CREATE TABLE dogs (id INTEGER PRIMARY KEY AUTOINCREMENT," + " name TEXT NOT NULL,"
					+ " dob DATE NOT NULL," + " breed TEXT," + " ownerId INTEGER REFERENCES owners(id))";
			s.executeUpdate(table2);
			String table3 = "CREATE TABLE vets (id INTEGER PRIMARY KEY AUTOINCREMENT," + " name TEXT NOT NULL,"
					+ " phone INTEGER," + " email TEXT NOT NULL," + " speciality TEXT)";
			s.executeUpdate(table3);
			String table4 = "CREATE TABLE dogsVets (" + " dogId INTEGER REFERENCES dogs(id) ON DELETE CASCADE,"
					+ " vetId INTEGER REFERENCES vets(id) ON DELETE CASCADE)";
			s.executeUpdate(table4);
			//c.commit(); changes that were waiting will be made at the same time
			//later he remove this autocomit things
			s.close();
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
