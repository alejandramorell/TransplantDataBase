package ifaces;

import java.util.List;

import transplant.pojos.*;

public interface NurseManager {

	public void insertNurse(Nurse nurse);
	public List<Nurse> searchNurseByName(String name);
	public Nurse getNurse(int id);
	//TODO
	//public void assignSurgeon(Surgeon id, transplant id); 
	//public void assignNurse();

}
