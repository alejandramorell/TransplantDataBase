package ifaces;

import transplant.pojos.*;

public interface TransplantManager {

	public void insertTransplant(Transplant transplant);
	public void getTranplant(int id); 
	public void updateInformation(Transplant transplant);
	public void insertOrgan(Organ organ);
	
	
}
