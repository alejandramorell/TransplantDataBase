package ifaces;

import java.util.List;

import transplant.pojos.*;

public interface NurseManager {

	public void insertNurse(Nurse nurse);
	public List<Nurse> searchNurseByName(String name);
	public Nurse getNurse(int id);
	public void assignSurgeonTransplant(int surgeon_id, int transplant_id); 
	
}
