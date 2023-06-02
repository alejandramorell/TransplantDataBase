package ifaces;

import java.time.LocalDate;
import java.util.List;

import transplant.pojos.Theatre;
import transplant.pojos.Transplant;

public interface TransplantManager {

	public void insertTransplant(Transplant transplant);
	public void insertTheatre(Theatre theatre);
	public Theatre getTheatre(int floor, int number);
	public Theatre getTheatreById(int id);
	public Transplant getTransplant(int id); 
	public Integer getTransplant(LocalDate date);
	public Theatre getTheatre(int id);
	public List<Transplant> getAllTransplants();
	public List<Transplant> getTransplants(LocalDate date);
	public void assignSurgeonTransplant(int surgeon_id, int transplant_id);

	
}
