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

import ifaces.NurseManager;
import transplant.pojos.Nurse;
import transplant.pojos.Surgeon;
import transplant.pojos.Transplant;

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
			// TODO Auto-generated catch block
			System.out.println("Database access error");
			e.printStackTrace();
		}
		
	}

	@Override
	public void insertNurse(Nurse nurse) {
		try {
			Statement s = c.createStatement();
			String sql = "INSERT INTO NURSE (name, adress, phone,email) VALUES ('" + nurse.getName() + "', "
					+ nurse.getAdress() + ", '" + nurse.getPhone() +", '"+nurse.getEmail()+"')";
			s.executeUpdate(sql);
			s.close();
		} catch (SQLException e) {
			System.out.println("Database exception.");
			e.printStackTrace();
		}
	}

	@Override
	public List<Nurse> searchNurseByName(String name) {
		List<Nurse> list = new ArrayList<Nurse>();
		try {
			String sql = "SELECT * FROM NURSE WHERE name LIKE ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, "%" + name + "%");
			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				// Create a new Nurse
				Integer id = rs.getInt("id");
				String n = rs.getString("name");
				String address = rs.getString("address");
				Integer phone = rs.getInt("phone");
				String email = rs.getString("email");
				Nurse nurse = new Nurse(id,n,address,phone,email);
				list.add(nurse);
			}
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Nurse getNurse(int id) {
		try {
			String sql = "SELECT * FROM NURSE WHERE id = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();
			rs.next();
			String name = rs.getString("name");
			String adress = rs.getString("adress");
			Integer phone = rs.getInt("phone");
			String email = rs.getString("email");
			Nurse n = new Nurse(id, name, adress, phone,email);
			rs.close();
			p.close();
			return n;
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
		return null;
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
			String sql = "SELECT * FROM NURSE WHERE email = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, email);
			ResultSet rs = p.executeQuery();
			rs.next();
			Integer id = rs.getInt("id");
			String name = rs.getString("name");
			String address = rs.getString("address");
			Integer phone = rs.getInt("phone");
			String email1 = rs.getString("email");
			Nurse n = new Nurse(id,name,address,phone,email1);
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
