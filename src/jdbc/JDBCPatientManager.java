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
import transplant.pojos.Organ;
import transplant.pojos.Patient;
import transplant.pojos.Surgeon;
import transplant.pojos.WaitingList;

public class JDBCPatientManager implements PatientManager{
	Connection c;
	
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
	
	
	public void insertPatient(Patient patient) {
		try {
			Statement s = c.createStatement();
			String sql = "INSERT INTO PATIENT (id, sex, name, surname, date_of_birth, disease, blood_type, admission_date, address, phone )"
					+ " VALUES ('" + patient.getId() + "',"+ patient.getSex()+"', "+ patient.getName() + "', "+ patient.getSurname() +"', "+ 
					patient.getDisease() + "', "+ patient.getBloodType() + "', "+ patient.getAdmissionDate() + "', "+ patient.getAdress() + 
					", '" + patient.getPhone() +"')";
			s.executeUpdate(sql);
			s.close();
		} catch (SQLException e) {
			System.out.println("Database exception.");
			e.printStackTrace();
		}
	}
	
	public List<Patient> searchPatientById(Integer id){
		
		List<Patient> list = new ArrayList<Patient>();
		try {
			String sql = "SELECT * FROM PATIENT WHERE id LIKE ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, "%" + id + "%"); 
			ResultSet rs = p.executeQuery(); 
			while (rs.next()) {
				// Create a new Owner
						
			    String sex = rs.getString("sex");
			    String name = rs.getString("name");
			    String surname = rs.getString("surname");
			    Date dateOfBirth = rs.getDate("dateOfBirth");
			    String disease = rs.getString("disease");
			    String bloodType = rs.getString("bloodType");
			    Date admissionDate = rs.getDate("admissionDate");
				String address = rs.getString("address");
				Integer phone = rs.getInt("phone");
				//TODO correct how to do rs.get... for waitingList & requestedOrgan
				//WaitingList waitingList = 
				//List<Organ> requestedOrgan = 
	
				//Patient p = new Patient(id, sex, name, surname, dateOfBirth, disease, bloodType,
						//admissionDate, address, phone, waitingList, requestedOrgan);
	
				// Add the Patient to the list:
				//list.add(p);
			}
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	//TODO error raro con patient(dice noseque de surgeon)
	//public Patient getPatient(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}