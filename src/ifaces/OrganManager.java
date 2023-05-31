package ifaces;

import java.util.List;

import transplant.pojos.*;



public interface OrganManager {

	public void insertOrgan(Organ organ);
	public List<Organ> searchOrganByType(String type);
	public Organ getOrgan(int id);
	public Donor getDonor(int id);
	public void insertDonor(Donor donor);
	public List<Donor> searchDonorByName(String name);
	public List<Donor> getAllDonors();
	public void removeOrgan(int id);
	public void assignOrganToPatient(int organId, int patientId);
	public List<Organ> searchOrgansByDonor(int id);
	
	
	
}
