package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import transplant.pojos.Organ;
import transplant.pojos.Patient;
import transplant.pojos.Theatre;
import transplant.pojos.Transplant;
import ifaces.*;


public class JDBCTransplantManager implements TransplantManager{
	
	private Connection c;
	private OrganManager organMan;
	private PatientManager patientMan;
	private TransplantManager transplantMan;
	
	public JDBCTransplantManager(Connection c, OrganManager organMan, PatientManager patientMan, TransplantManager transplantMan) {
		super();
		this.c = c;
		this.organMan = organMan;
		this.patientMan = patientMan;
		this.transplantMan = transplantMan;
		
	}
	
	@Override
	public void insertTransplant(Transplant transplant) {
		try {
			String sql = "INSERT INTO TRANSPLANT (registration_date, requested_type, organ_id,theatre_id,patient_id) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement p = c.prepareStatement(sql);
			p.setDate(1, transplant.getRegistrationDate());
			p.setString(2, transplant.getRequestedType());
			p.setInt(3, transplant.getRequestedOrgan().getId());
			p.setInt(4, transplant.getTheatre().getId());
			p.setInt(5, transplant.getPatient().getId());
			p.executeUpdate();
			p.close();
		} catch (SQLException e) {
			System.out.println("Database exception.");
			e.printStackTrace();
		}
	}
	@Override
	public void insertTheatre(Theatre theatre) {
			try {
				String sql = "INSERT INTO THEATRE (floor, number) VALUES (?, ?)";
				PreparedStatement p = c.prepareStatement(sql);
				p.setInt(1, theatre.getFloor());
				p.setInt(2, theatre.getNumber());
				p.executeUpdate();
				p.close();
			} catch (SQLException e) {
				System.out.println("Database exception.");
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
	public Theatre getTheatreById(int id) {
		try {
		String sql = "SELECT * FROM THEATRE WHERE id LIKE ?";
		PreparedStatement p = c.prepareStatement(sql);
		p.setInt(1, id);
		ResultSet rs = p.executeQuery();
		rs.next();
		int floor = rs.getInt("floor");
		int number  = rs.getInt("id");
		Theatre t = new Theatre(id, floor, number);		
		rs.close();
		p.close();
		return t;
		}catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public Theatre getTheatre(int floor, int number ) {
		try {			
			String sql = "SELECT * FROM THEATRE WHERE floor LIKE ? AND number LIKE ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, floor);
			p.setInt(2,number);
			ResultSet rs = p.executeQuery();
			rs.next();
			int id = rs.getInt("id");
			Theatre t = new Theatre(id, floor, number);		
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
	//this method would return only 1 transplant if there where more transplants in the same date
	public Integer getTransplant(LocalDate date ) {
		try {			
			String sql = "SELECT id FROM TRANSPLANT WHERE date = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setDate(1, Date.valueOf(date));
			ResultSet rs = p.executeQuery();
			rs.next();
			int id = rs.getInt("id");
			rs.close();
			p.close();
			return id;			
			
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	@Override
	public List<Transplant> getAllTransplants() {
		List<Transplant> list = new ArrayList<Transplant> ();
		
		try {
			String sql = "SELECT * FROM TRANSPLANT";
			PreparedStatement p = c.prepareStatement(sql);
			ResultSet rs = p.executeQuery();
			
			while (rs.next()) {
				Integer id =  rs.getInt("id");
				Date registrationDate = rs.getDate("registration_date");
				String requestedType = rs.getString("requested_type");
				Organ requestedOrgan = organMan.getOrgan(rs.getInt("organ_id")); 
				Theatre theatre = getTheatreById(rs.getInt("theatre_id"));
				Patient patient = patientMan.getPatient(rs.getInt("patient_id"));
			
				Transplant t = new Transplant(id,registrationDate,requestedType, requestedOrgan,theatre, patient);
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
	public Theatre getTheatre(int id) {
		try {
			String sql = "SELECT * FROM THEATRE WHERE id = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();
			rs.next();
			Integer floor = rs.getInt("floor");
			Integer number = rs.getInt("number");
			Theatre t = new Theatre(id, floor, number);
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
	public Transplant getTransplant(int id) {
		try {
	 		String sql = "SELECT * FROM TRANSPLANT WHERE id = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();
			rs.next();
			Date registrationDate = rs.getDate("registration_date");
			String requestedType = rs.getString("requested_type");
			Organ requestedOrgan = organMan.getOrgan(rs.getInt("organ_id")); 
			Theatre theatre = getTheatre(rs.getInt("theatre_id"));
			Patient patient = patientMan.getPatient(rs.getInt("patient_id"));
			
			Transplant t = new Transplant(id,registrationDate, requestedType ,requestedOrgan,theatre , patient );
			rs.close();
			p.close();
			return t;
		} catch (SQLException e) {
			System.out.println("ERROR: the id doesn't corresspond to any transplant.");
		}
		return null;
	}

	public Connection getC() {
		return c;
	}

	public void setC(Connection c) {
		this.c = c;
	}
	


}
	

