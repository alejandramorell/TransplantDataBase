package ifaces;

import java.time.LocalDate;

import transplant.pojos.Theatre;
import transplant.pojos.Transplant;

public interface TransplantManager {

	public void insertTransplant(Transplant transplant);
	public Transplant getTransplant(int id); 
	public void updateInformation(Transplant transplant);
	public Integer getTransplant(LocalDate date);
	public Theatre getTheatre(int id);

	
}
