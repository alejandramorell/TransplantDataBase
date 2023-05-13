package ifaces;

import java.time.LocalDate;
import java.util.List;

import transplant.pojos.Theatre;
import transplant.pojos.Transplant;

public interface TransplantManager {

	public void insertTransplant(Transplant transplant);
	public Transplant getTransplant(int id); 
	public void updateInformation(Transplant transplant);
	public Integer getTransplant(LocalDate date);
	public Theatre getTheatre(int id);
	public List<Transplant> getAllTransplants();
	public List<Transplant> getTransplants(LocalDate date);
	public void assignSurgeonToTransplant(int surgeonId, int transplantId);

	
}
