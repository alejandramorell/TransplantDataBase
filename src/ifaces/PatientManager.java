package ifaces;

import java.util.List;

import transplant.pojos.*;

public interface PatientManager {

	public void insertPatient(Patient patient);
	public List<Patient> searchPatientByName(String name);
	public Surgeon getPatient(int id);
	public void showPatient(Patient id); 
	public void updatePatient(Patient patient);
	
}
