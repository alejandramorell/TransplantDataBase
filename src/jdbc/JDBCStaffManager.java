package jdbc;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ifaces.StaffManager;
import pojos.Staff;

public class JDBCStaffManager implements StaffManager {
	
	private Connection c;
	
	public JDBCStaffManager(Connection c) {
		this.c = c;
	}
	
	
	public void insertStaff(Staff staff){
		try {
			Statement s = c.createStatement();
			String sql = "INSERT INTO STAFF (name, surname, field) VALUES ('" + staff.getName() + "', '"
					+ staff.getSurname() + "', " + staff.getField() + "')";
			s.executeUpdate(sql);
			s.close();
		} catch (SQLException e) {
			System.out.println("Database exception.");
			e.printStackTrace();
		}
	}
	

	public List<Staff> searchStaffByName(String name){
		
		List<Staff> list = new ArrayList<Staff>();
		try {
			String sql = "SELECT * FROM STAFF WHERE name LIKE ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, "%" + name + "%"); 
			ResultSet rs = p.executeQuery(); 
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String n = rs.getString("name");
				String surname = rs.getString("surname");
				String field = rs.getString("field");
				Staff s = new Staff(id, n, surname, field);
				list.add(s);
			}
			rs.close();
			p.close();
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
		return list;
	}

	public Staff getStaff(int id) { 
		try {
			String sql = "SELECT * FROM STAFF WHERE id = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();
			rs.next();
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			String field = rs.getString("field");
			Staff s = new Staff(id, name, surname, field);
			rs.close();
			p.close();
			return s;
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
		return null;
	}
	
	/*public Staff getStaffByName(String email) {
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
	}*/	

}
