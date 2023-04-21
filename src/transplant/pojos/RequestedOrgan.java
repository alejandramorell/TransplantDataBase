package transplant.pojos;

import java.io.Serializable;
import java.util.Objects;

public class RequestedOrgan implements Serializable{

	
	private static final long serialVersionUID = 4031768231552830582L;
	private Integer id;
	private String type;
	private Patient patient;
	public RequestedOrgan() {
		super();
	}
	public RequestedOrgan(Integer id, String type, Patient patient) {
		super();
		this.id = id;
		this.type = type;
		this.patient = patient;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestedOrgan other = (RequestedOrgan) obj;
		return Objects.equals(id, other.id);
	} 	
	

}
