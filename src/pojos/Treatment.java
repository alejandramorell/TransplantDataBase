package pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Treatment implements Serializable {

	private static final long serialVersionUID = 6640455937191882784L;
	private Integer id; 
	private int days;
	private int hoursPerDay;
	private String goals;
	private String technology;
	
	//1:N A TREATMENT CAN HAVE MANY TESTS
	private List<Test> tests;
	
	//N-N : A TREATMENT CAN BE APLIED BY MANY STAFF MEMBERS
	private List<Staff> staffMembers;
	
	//N-N : A TREATMENT CAN BE APLIED TO MANY PATIENTS
	private List<Patient> patients;
	
	public Treatment(Integer id,Integer days, Integer hoursPerDay, String goals, String technology,Patient patient, Staff staff) {
		super();
		this.id = id;
		this.days = days;
		this.hoursPerDay = hoursPerDay;
		this.goals = goals;
		this.technology = technology;
		this.patient = patient;	
		this.staffMembers = new ArrayList<>();
		
	}
	
	public Treatment(Integer days, Integer hoursPerDay, String goals, String technology,Patient patient) {
		super();
		this.days = days;
		this.hoursPerDay = hoursPerDay;
		this.goals = goals;
		this.technology = technology;
		this.patient = patient;
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public int getHoursPerDay() {
		return hoursPerDay;
	}

	public void setHoursPerDay(int hoursPerDay) {
		this.hoursPerDay = hoursPerDay;
	}

	public String getGoals() {
		return goals;
	}

	public void setGoals(String goals) {
		this.goals = goals;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	
	@Override
	public String toString() {
		return "Treatment [id=" + id + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Treatment other = (Treatment) obj;
		return Objects.equals(id, other.id);
	} 


}
