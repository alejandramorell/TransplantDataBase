package jdbc;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ifaces.*;
import pojos.*;


public class JDBCTreatmentManager implements TreatmentManager{
	
	private Connection c;
	private PatientManager patientMan;
	private TreatmentManager treatmentMan;
	private StaffManager staffMan;
	
	public JDBCTreatmentManager(Connection c, PatientManager patientMan, TreatmentManager treatmentMan, StaffManager staffMan) {
		super();
		this.c = c;
		this.patientMan = patientMan;
		this.treatmentMan = treatmentMan;
		this.staffMan = staffMan;
		
	}
	
	@Override
	public void insertTreatment(Treatment treatment) {
		try {
			String sql = "INSERT INTO TREATMENT (days,hoursPerDay, goals, technology, patient_id) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, treatment.getDays());
			p.setInt(2, treatment.getHoursPerDay());
			p.setString(3, treatment.getGoals());
			p.setString(4, treatment.getTechnology());
			p.setInt(5, treatment.getPatient().getId());
			p.executeUpdate();
			p.close();
		} catch (SQLException e) {
			System.out.println("Database exception.");
			e.printStackTrace();
		}
	}
	
	@Override
	public void insertPatient(Patient patient) {
		try {
			String sql = "INSERT INTO PATIENT (id, sex, name, surname, date_of_birth, blood_type, admission_date, address, phone) VALUES (?, ?, ?, ?,?,?,?,?,?)";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, patient.getId());
			p.setString(2, patient.getSex());
			p.setString(3, patient.getName());
			p.setString(4, patient.getSurname());
			p.setDate(5, patient.getDateOfBirth());
			p.setString(6, patient.getPathology());
			p.setString(8, patient.getDiagnosis());
			p.executeUpdate();
			p.close();
		} catch (SQLException e) {
			System.out.println("Database exception.");
			e.printStackTrace();
		}
	
	}
	
	@Override
	public Patient getPatientById(int id) {
		try {
			String sql = "SELECT * FROM PATIENT WHERE id = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();
			rs.next();
			
			String sex = rs.getString("sex");
			String n = rs.getString("name");
			String surname = rs.getString("surname");
			Date dob = rs.getDate("date_of_birth");
			String pathology = rs.getString("pathology");
			String diagnosis = rs.getString("diagnosis");
			Patient p1 = new Patient(id, sex, n, surname, dob, pathology, diagnosis);
			
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
	public Treatment getTreatment(int id) {
		try {
			String sql = "SELECT * FROM TREATMENT WHERE id = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();
			rs.next();
			Integer days = rs.getInt("days");
			Integer hoursPerDay = rs.getInt("hours per day");
			String goals = rs.getString("goals");
			String technology = rs.getString("technology");
			Patient patient = patientMan.getPatient(rs.getInt("patient_id"));
			
			Treatment t = new Treatment(days, hoursPerDay, goals, technology, patient);
			rs.close();
			p.close();
			return t;
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public List<Treatment> getAllTreatments() {
		List<Treatment> list = new ArrayList<Treatment> ();
		
		try {
			String sql = "SELECT * FROM TREATMENT";
			PreparedStatement p = c.prepareStatement(sql);
			ResultSet rs = p.executeQuery();
			
			while (rs.next()) {
				Integer id =  rs.getInt("id");
				Integer days =  rs.getInt("days");
				Integer hoursPerDay =  rs.getInt("hoursPerDay");
				String goals = rs.getString("goals");
				String technology = rs.getString("technology");
				Patient patient = patientMan.getPatient(rs.getInt("patient_id"));
				
				
				Treatment t = new Treatment(days, hoursPerDay, goals,technology, patient);
				list.add(t);
			}
			rs.close();
			p.close();
	 	} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
		return list;
		
	}
		
	@Override
	public void assignStaffTreatment(int staff_id, int treatment_id) {
		try {
			String sql = "INSERT INTO STAFF_TREATMENT (staff_id, treatment_id) VALUES (?,?)";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, staff_id);
			p.setInt(2, treatment_id);
			p.executeUpdate();
			p.close();
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
		
	}
}
	

