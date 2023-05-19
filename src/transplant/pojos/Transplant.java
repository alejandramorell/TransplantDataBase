package transplant.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Transplant implements Serializable {


	private static final long serialVersionUID = 6640455937191882784L;
	private Integer id; 
	private Date registrationDate;
	private String requestedType;
	private Organ requestedOrgan; 
	private Patient patient;
	private Theatre theatre;
	private List<Surgeon> surgeons;
	
	
	
	public Transplant(Integer id, Date registrationDate,Organ requestedOrgan,Patient patient,String requestedType, Theatre theatre) {
		super();
		this.id = id;
		this.registrationDate = registrationDate;
		this.requestedOrgan = requestedOrgan;
		this.patient = patient;
		this.requestedType = requestedType;
		this.theatre = theatre;
		
	}
	
	public Transplant(Date registrationDate,Organ requestedOrgan,Patient patient,String requestedType, Theatre theatre) {
		super();
		//this.id = id;
		this.registrationDate = registrationDate;
		this.requestedOrgan = requestedOrgan;
		this.patient = patient;
		this.requestedType = requestedType;
		this.theatre = theatre;
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
	public Organ getRequestedOrgan() {
		return requestedOrgan;
	}
	public void setRequestedOrgan(Organ requestedOrgan) {
		this.requestedOrgan = requestedOrgan;
	}
	
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public String getRequestedType() {
		return requestedType;
	}
	public void setRequestedType(String requestedType) {
		this.requestedType = requestedType;
	}
	public Theatre getTheatre() {
		return theatre;
	}
	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	
	@Override
	public String toString() {
		return "Transplant [id=" + id + ", registrationDate=" + registrationDate + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transplant other = (Transplant) obj;
		return Objects.equals(id, other.id);
	} 


}
