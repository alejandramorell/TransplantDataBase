package transplant.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Transplant implements Serializable {

	private static final long serialVersionUID = 3257926599369942939L;
	private Integer id; 
	private Date registrationDate;
	private RequestedOrgan requestedOrgan; 
	private Organ organ; 
	private Theatre theatre;
	
	public Transplant() {
		super();
	}
	public Transplant(Integer id, Date registrationDate, RequestedOrgan requestedOrgan, Organ organ, Theatre theatre) {
		super();
		this.id = id;
		this.registrationDate = registrationDate;
		this.requestedOrgan = requestedOrgan;
		this.organ = organ;
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
	public Organ getOrgan() {
		return organ;
	}
	public void setOrgan(Organ organ) {
		this.organ = organ;
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
