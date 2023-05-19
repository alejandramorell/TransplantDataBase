package transplant.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Nurse implements Serializable {

	private static final long serialVersionUID = -4062894783010869655L;
	private Integer id;
	private String name;
	private String adress;
	private Integer phone;
	private String email;

	
	public Nurse() {
		super();
	}
	
	public Nurse(Integer id, String name, String adress, Integer phone, String email) {
		super();		
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.phone = phone;
		this.email = email;

	}
	
	public Nurse(String name, String adress, Integer phone, String email) {
		super();		
		this.name = name;
		this.adress = adress;
		this.phone = phone;
		this.email = email;

	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public Integer getPhone() {
		return phone;
	}
	public void setPhone(Integer phone) {
		this.phone = phone;
	}	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	
	@Override
	public String toString() {
		return "Nurse [id=" + id + ", name=" + name + ", adress=" + adress + ", phone=" + phone + ", email= "+email+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nurse other = (Nurse) obj;
		return Objects.equals(id, other.id);
	}	

}
