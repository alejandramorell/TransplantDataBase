package ifaces;

import transplant.pojos.*;

public interface TransplantManager {

	public void showTransplant(Transplant id); 
	public void insertTransplant(Transplant transplant);
	public void updateInformation(Transplant transplant);
	public void insertOrgan(Organ organ);
	
	
}
