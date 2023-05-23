package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ifaces.NurseManager;
import transplant.pojos.Nurse;

public class JDBCNurseManager implements NurseManager{
	
	private Connection c;
	
	public JDBCNurseManager(Connection c) {
		this.c = c;
	}
	
	public JDBCNurseManager()  {
		
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
	public void assignSurgeonTransplant(int surgeon_id, int transplant_id) {
		try {
			String sql = "INSERT INTO TRANSPLANT_SURGEON (transplant_id, surgeon_id) VALUES (?,?)";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, transplant_id);
			p.setInt(2, surgeon_id);
			p.executeUpdate();
			p.close();
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
		
	}
	@Override
	public Nurse getNurseByEmail(String email) {
		try {
			String sql = "SELECT * FROM users WHERE EMAIL = '?'";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, email);
			ResultSet rs = p.executeQuery();
			rs.next();
			Integer id = rs.getInt("id");
			String name = rs.getString("name");
			String address = rs.getString("address");
			Integer phone = rs.getInt("phone");
			Nurse n = new Nurse(id,name,address,phone,email);
			rs.close();
			p.close();
			return n;
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
		return null;
	}	
	
}
