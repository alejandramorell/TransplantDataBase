package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

	public JDBCTransplantManager()  {
		
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
	public void insertTransplant(Transplant transplant) {
		try {
			Statement s = c.createStatement();
			String sql = "INSERT INTO TRANSPLANT (id, date, requested_organ, organ_id, theatre_id)"
					+ " VALUES ('" + transplant.getId() + "',"+ transplant.getRegistrationDate()+"', "+ transplant.getRequestedOrgan() +"', "+ 
					transplant.getTheatre() +"')";
			s.executeUpdate(sql);
			s.close();
		} catch (SQLException e) {
			System.out.println("Database exception.");
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void updateInformation(Transplant transplant) {
		try {
			String sql = "UPDATE TRANSPLANT SET" + " date = ?, " + " requested_organ = ?, " + " organ_id = ? " + " theatre_id = ? ";
			PreparedStatement p;
			p = c.prepareStatement(sql);
			p.setDate(1, transplant.getRegistrationDate());
			//TODO check if the methods with another type of data need a cast
			p.setString(2, transplant.getRequestedOrgan().getType());
			p.setInt(3, transplant.getTheatre().getFloor());
			p.close();
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
	}

	@Override
	//TODO this method would return only 1 transplant if there where more transplants in the same date
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

	public List<Transplant> getTransplants(LocalDate date) {
		List<Transplant> list = new ArrayList<Transplant> ();
		
		try {
			String sql = "SELECT * FROM TRANSPLANT WHERE date = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setDate(1, Date.valueOf(date));
			ResultSet rs = p.executeQuery();
			
			while (rs.next()) {
				//Integer transplantId = rs.getInt("id"); 
				Date registrationDate = rs.getDate("registration_date");
				Organ requestedOrgan = organMan.getOrgan(rs.getInt("organ_id")); 
				Patient patient = patientMan.getPatient(rs.getInt("patient_id"));
				String requestedType = rs.getString("requested_type");
				Theatre theatre = transplantMan.getTheatre(rs.getInt("theatre_id"));
			
				Transplant t = new Transplant(registrationDate, requestedOrgan, patient, requestedType, theatre );
				list.add(t);
			}
			
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
		return list;
		
	}
	
	public List<Transplant> getAllTransplants() {
		List<Transplant> list = new ArrayList<Transplant> ();
		
		try {
			String sql = "SELECT * FROM TRANSPLANT";
			PreparedStatement p = c.prepareStatement(sql);
			ResultSet rs = p.executeQuery();
			
			while (rs.next()) {
				Integer id =  rs.getInt("id");
				Date registrationDate = rs.getDate("registration_date");
				Organ requestedOrgan = organMan.getOrgan(rs.getInt("organ_id")); 
				Patient patient = patientMan.getPatient(rs.getInt("patient_id"));
				String requestedType = rs.getString("requested_type");
				Theatre theatre = transplantMan.getTheatre(rs.getInt("theatre_id"));
			
				Transplant t = new Transplant(id,registrationDate, requestedOrgan, patient, requestedType, theatre );
				list.add(t);
			}
			
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
		return list;
		
	}
	
	
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
				Organ requestedOrgan = organMan.getOrgan(rs.getInt("organ_id")); 
				Patient patient = patientMan.getPatient(rs.getInt("patient_id"));
				String requestedType = rs.getString("requested_type");
				Theatre theatre = transplantMan.getTheatre(rs.getInt("theatre_id"));
			
				Transplant t = new Transplant(id,registrationDate, requestedOrgan, patient, requestedType, theatre );rs.close();
				p.close();
				return t;
			} catch (SQLException e) {
				System.out.println("Database error.");
				e.printStackTrace();
			}
			return null;
		}
	
	public void assignSurgeonToTransplant(int surgeonId, int transplantId) {
		try {
			String sql = "INSERT INTO TRANSPLANT_SURGEON (transplant_id, surgeon_id) VALUES (?,?)";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, surgeonId);
			p.setInt(2, transplantId);
			p.executeUpdate();
			p.close();
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
	}
	}
	

