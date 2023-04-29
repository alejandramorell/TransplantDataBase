package ifaces;

import java.util.List;

import transplant.pojos.*;

public interface SurgeonManager {
	
	public void insertSurgeon(Surgeon surgeon);
	public List<Surgeon> searchSurgeonByName(String name);
	public Surgeon getSurgeon(int id);
	

}
