package ifaces;

import java.util.List;
import pojos.*;

public interface StaffManager {

	public void insertStaff(Staff staff);
	public List<Staff> searchStaffByName(String name);
	public Staff getStaff(int id);
	///public Staff getStaffByName(String name);
	
	

}
