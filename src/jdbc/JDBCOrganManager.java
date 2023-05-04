package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ifaces.OrganManager;
import transplant.pojos.Organ;

public class JDBCOrganManager implements OrganManager {
	
	Connection c;

	

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeOrgan(int id) {
		// TODO Auto-generated method stub

	}

}
