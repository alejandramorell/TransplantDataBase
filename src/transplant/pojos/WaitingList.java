package transplant.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class WaitingList implements Serializable {

	
	private Integer id; 
	private Date registrationDate;
	private String Urgency; 
	private Date dateOfBirth;
	private List<Patient> patients;
	
	public WaitingList() {
		super();
	}
	
	public WaitingList(Integer id, Date registrationDate, String urgency, Date dateOfBirth, List<Patient> patients) {
		super();
		this.id = id;
		this.registrationDate = registrationDate;
		Urgency = urgency;
		this.dateOfBirth = dateOfBirth;
		this.patients = patients;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getUrgency() {
		return Urgency;
	}
	public void setUrgency(String urgency) {
		Urgency = urgency;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public List<Patient> getPatients() {
		return patients;
	}
	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WaitingList other = (WaitingList) obj;
		return Objects.equals(id, other.id);
	} 
	
	
}
