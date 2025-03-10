package ifaces;

import java.util.List;

import pojos.*;

public interface PatientManager {

	public void insertPatient(Patient patient);
	public List<Patient> searchPatientByName(String name);
	public Patient getPatient(int id); 
	public void updatePatient(Patient patient);
	public void removePatient(int id);
	
	
}
