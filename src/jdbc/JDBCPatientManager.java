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

import ifaces.PatientManager;
import transplant.pojos.Patient;

public class JDBCPatientManager implements PatientManager{
	private Connection c;
	
	public JDBCPatientManager(Connection c) {
		this.c = c;
	}
	
	public JDBCPatientManager()  {
		
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
	public void insertPatient(Patient patient) {
		try {
			String sql = "INSERT INTO PATIENT (id, sex, name, surname, date_of_birth, blood_type, admission_date, address, phone) VALUES (?, ?, ?, ?)";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, patient.getId());
			p.setString(2, patient.getSex());
			p.setString(3, patient.getName());
			p.setString(4, patient.getSurname());
			p.setDate(5, patient.getDateOfBirth());
			p.setString(6, patient.getBloodType());
			p.setDate(7, patient.getAdmissionDate());
			p.setString(8, patient.getAddress());
			p.setInt(8, patient.getPhone());
			p.executeUpdate();
			p.close();
		} catch (SQLException e) {
			System.out.println("Database exception.");
			e.printStackTrace();
		}
	
	}
	
		


	@Override
	public List<Patient> searchPatientByName(String name) {
		List<Patient> list = new ArrayList<Patient>();
		try {
			String sql = "SELECT * FROM PATIENT WHERE name LIKE ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, "%" + name + "%"); 
			ResultSet rs = p.executeQuery(); //
			while (rs.next()) {
				// Create a new patient
			
				Integer id = rs.getInt("id");
				String sex = rs.getString("sex");
				String n = rs.getString("name");
				String surname = rs.getString("surname");
				Date dob = rs.getDate("date of birth");
				String bloodType = rs.getString("blood type");
				Date admissionDate = rs.getDate("date of admission");
				String adress = rs.getString("adress");
				Integer phone= rs.getInt("phone");
				Patient p1 = new Patient(id, sex, n, surname, dob,bloodType, admissionDate, adress,phone);
				// IMPORTANT: I don't have the requested organs
				// Add the Patient to the list
				list.add(p1);
			}
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public Patient getPatient(int id) {
		try {
			String sql = "SELECT * FROM PATIENT WHERE id = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();
			rs.next();
			String sex = rs.getString("sex");
			String n = rs.getString("name");
			String surname = rs.getString("surname");
			Date dob = rs.getDate("date of birth");
			String bloodType = rs.getString("blood type");
			Date admissionDate = rs.getDate("date of admission");
			String adress = rs.getString("adress");
			Integer phone= rs.getInt("phone");
			Patient p1 = new Patient(id, sex, n, surname, dob,bloodType, admissionDate, adress,phone);
			rs.close();
			p.close();
			return p1;
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public void updatePatient(Patient patient) {
		try {
			String sql = "UPDATE PATIENT SET" + " sex = ?, " + " name = ?, " + " surname = ? " + " date_of_birth = ? " +  " blood_type = ? " + " admission_date = ? " + " adress = ? " + " WHERE id = ?";
			PreparedStatement p;
			p = c.prepareStatement(sql);
			p.setString(1, patient.getSex());
			p.setString(2, patient.getName());
			p.setString(3, patient.getSurname());
			p.setDate(4, patient.getDateOfBirth());
			p.setString(6, patient.getBloodType());
			p.setDate(7, patient.getAdmissionDate());
			p.setString(8, patient.getAddress());
			p.setInt(9, patient.getId());
			p.executeUpdate();
			p.close();
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
	}


	@Override
	public void removePatient(int id) {
			try {
				String sql = "DELETE FROM PATIENT WHERE id = ?";
				PreparedStatement p;
				p = c.prepareStatement(sql);
				p.setInt(1, id);
				p.executeUpdate();
				p.close();
			} catch (SQLException e) {
				System.out.println("Database error.");
				e.printStackTrace();
			}
		
	}
	



}
