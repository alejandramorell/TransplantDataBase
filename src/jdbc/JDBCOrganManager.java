package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ifaces.OrganManager;
import transplant.pojos.Donor;
import transplant.pojos.Organ;

public class JDBCOrganManager implements OrganManager {
	
	private Connection c;
	
	public JDBCOrganManager(Connection c) {
		this.c = c;
	}

	

	@Override
	public void insertOrgan(Organ organ) {
		try {
				Statement s = c.createStatement();
				String sql = "INSERT INTO ORGAN (type, bloodType, donorId) VALUES ('" + organ.getType() + "', "
						+ organ.getBloodType() + ", '" + organ.getDonor().getId() +"')";
				s.executeUpdate(sql);
				s.close();
			} catch (SQLException e) {
				System.out.println("Database exception.");
				e.printStackTrace();
			}
		}
	
	@Override
	public void insertDonor(Donor donor) {
		try {
				Statement s = c.createStatement();
				String sql = "INSERT INTO DONOR (name, adress, phone, living_state) VALUES ('" + donor.getName() + "', "
						+ donor.getAdress() + ", '" + donor.getPhone() + ", '" + donor.getLivingState()+"')";
				s.executeUpdate(sql);
				s.close();
			} catch (SQLException e) {
				System.out.println("Database exception.");
				e.printStackTrace();
			}
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
				String bloodType = rs.getString("blood type");
				Organ o = new Organ(id, t, bloodType);
				list.add(o);
			}
		} catch (SQLException e) {
			System.out.println("Database error.");
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
			String bloodType = rs.getString("blood type");
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
			String adress = rs.getString("adress");
			Integer phone = rs.getInt("phone");
			String livingState = rs.getString("living state");
			Donor d = new Donor(id, name, adress, phone, livingState);
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
	public void assignOrganToPatient(int organId, int patientId) {
		try {
			String sql = "INSERT INTO ORGAN_PATIENT (organId, patientId) VALUES (?,?)";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, organId);
			p.setInt(2, patientId);
			p.executeUpdate();
			p.close();
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Organ> searchOrganByDonor(int id) {
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
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
		return list;
	}
	
}
