package transplant.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Transplant implements Serializable {


	private static final long serialVersionUID = 6640455937191882784L;
	private Integer id; 
	private Date registrationDate;
	private RequestedOrgan requestedOrgan; 
	private Theatre theatre;
	
	public Transplant() {
		super();
	}
	public Transplant(Integer id, Date registrationDate) {
		super();
		this.id = id;
		this.registrationDate = registrationDate;
		
	}
	
	public Transplant(Date registrationDate, RequestedOrgan requestedOrgan, Organ organ, Theatre theatre) {
		super();
		this.registrationDate = registrationDate;
		this.requestedOrgan = requestedOrgan;
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
	public RequestedOrgan getRequestedOrgan() {
		return requestedOrgan;
	}
	public void setRequestedOrgan(RequestedOrgan requestedOrgan) {
		this.requestedOrgan = requestedOrgan;
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
