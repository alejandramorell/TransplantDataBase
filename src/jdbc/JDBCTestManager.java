package jdbc;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ifaces.TestManager;
import pojos.Test;
import pojos.Treatment;


public class JDBCTestManager implements TestManager {
	
	private Connection c;
	
	public JDBCTestManager(Connection c) {
		this.c = c;
	}

	@Override
	public void insertTest(Test test) {
		try {
				Statement s = c.createStatement();
				String sql = "INSERT INTO TEST (type, result, dateOfTest, patient_id, treatmentId ) VALUES ('" + test.getType() + "', '"
						+ test.getResult() + "', " + test.getDateOfTest() + "', " + test.getPatient().getId() + "', " + test.getTreatment().getId() + ")";
				s.executeUpdate(sql);
				s.close();
			} catch (SQLException e) {
				System.out.println("Database exception.");
				e.printStackTrace();
			}
		}
	
	@Override
	public void insertTreatment(Treatment treatment) {
		try {
				Statement s = c.createStatement();
				String sql = "INSERT INTO TREATMENT (days, hoursPerDay, goals, technology,patient_id) VALUES ('" + treatment.getDays() + "', '"
						+ treatment.getHoursPerDay()+ "', " + treatment.getGoals() + ", '" + treatment.getTechnology() + + treatment.getPatient().getId()+"')";
				s.executeUpdate(sql);
				s.close();
		}catch (SQLException e) {
				System.out.println("Database exception.");
				e.printStackTrace();
			}
		}

	@Override
	public List<Treatment> searchTreatmentById(Integer id) {
		List<Treatment> list = new ArrayList<Treatment>();
		try {
			String sql = "SELECT * FROM TREATMENT WHERE id LIKE ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, "%" + id + "%"); 
			ResultSet rs = p.executeQuery(); //Result set = object with the information selected by the query
			while (rs.next()) {
				Integer days = rs.getInt("days");
				Integer hoursPerDay = rs.getInt("hoursPerDay");
				String goals = rs.getString("goals");
				String technology = rs.getString("technology");
				
				Treatment t = new Treatment(id,days, hoursPerDay, goals, technology);
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
	public List<Donor> getAllDonors() {
		List<Donor> list = new ArrayList<Donor>();
		try {
			String sql = "SELECT * FROM DONOR";
			PreparedStatement p = c.prepareStatement(sql);
			ResultSet rs = p.executeQuery(); //
			while (rs.next()) {
				
				Integer id = rs.getInt("id");
				String n = rs.getString("name");
				String address = rs.getString("address");
				Integer phone= rs.getInt("phone");
				String livingState = rs.getString("living_state");
				
				Donor d = new Donor(id, n, address, phone, livingState);
				d.setOrgans(searchOrgansByDonor(d.getId())); 
				list.add(d);
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
	public List<Organ> searchOrganByType(String type) {
		List<Organ> list = new ArrayList<Organ>();
		try {
			String sql = "SELECT * FROM ORGAN WHERE type LIKE ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, "%" + type + "%");
			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				// Create a new Organ
				Integer id = rs.getInt("id");
				String t = rs.getString("type");
				String bloodType = rs.getString("blood_type");
				Organ o = new Organ(id, t, bloodType);
				list.add(o);
				
			}
			rs.close();
			p.close();
		} catch (SQLException e) {
			System.out.println("database error.");
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Organ getOrgan(int id) {
		try {
			String sql = "SELECT * FROM ORGAN WHERE id = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();
			rs.next();
			String type = rs.getString("type");
			String bloodType = rs.getString("blood_type");
			Organ o = new Organ(id, type, bloodType);
			rs.close();
			p.close();
			return o;
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public Donor getDonor(int id) {
		try {
			String sql = "SELECT * FROM DONOR WHERE id = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();
			rs.next();
			String name = rs.getString("name");
			String address = rs.getString("address");
			Integer phone = rs.getInt("phone");
			String livingState = rs.getString("living_state");
			Donor d = new Donor(id, name, address, phone, livingState);
			rs.close();
			p.close();
			return d;
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public void removeOrgan(int id) {
		try {
			String sql = "DELETE FROM ORGAN WHERE id = ?";
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
	
	
	@Override
	public List<Organ> searchOrgansByDonor(int id) {
		List<Organ> list = new ArrayList<Organ>();
		try {
			String sql = "SELECT * FROM ORGAN WHERE donor_id = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				Integer organId = rs.getInt("id");
				String type = rs.getString("type");
				String bloodType = rs.getString("blood_type");
				Organ or = new Organ(organId,type,bloodType);
				list.add(or);
			}
			rs.close();
			p.close();
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
		return list;
	}
	
}
