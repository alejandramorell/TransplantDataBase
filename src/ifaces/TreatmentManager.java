package ifaces;

import java.util.List;

import pojos.Patient;
import pojos.Treatment;

public interface TreatmentManager {

		public void insertTreatment(Treatment treatment);
		public void insertPatient(Patient patient);
		public Patient getPatientById(int id);
		public Treatment getTreatment(int id); 
		public List<Treatment> getAllTreatments();
		public void assignStaffTreatment(int staff_id, int treatment_id);

}
