package ifaces;

import java.util.List;

import transplant.pojos.*;

public interface NurseManager {

	public void insertNurse(Nurse nurse);
	public List<Nurse> searchNurseByName(String name);
	public Nurse getNurse(int id);
	public void assignSurgeonTransplant(int surgeon_id, int transplant_id); 
	public void assignNurseTransplant(int nurse_id,int transplant_id);
}
