package ifaces;

import transplant.pojos.*;

public interface NurseManager {

	public void assignSurgeonTransplant(int surgeon_id, int transplant_id);
	public Nurse getNurseByEmail(String email); 
	
}
