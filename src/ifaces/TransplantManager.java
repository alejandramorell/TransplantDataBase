package ifaces;

import transplant.pojos.Transplant;

public interface TransplantManager {

	public void insertTransplant(Transplant transplant);
	public Transplant getTransplant(int id); 
	public void updateInformation(Transplant transplant);
	
	
	
}
