package transplant.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;


public class Surgeon implements Serializable {

	//private static final long serialVersionUID = 1L;
	private Integer id; 
	private String name; 
	private String adress;
	private Integer phone;
	private String speciality;
	private Date hiring_date;
	
	
	public Surgeon() {
		super();
	}


	public Surgeon(Integer id, String name, String adress, Integer phone, String speciality, Date hiring_date) {
		super();
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.phone = phone;
		this.speciality = speciality;
		this.hiring_date = hiring_date;
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


	public String getSpeciality() {
		return speciality;
	}


	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}


	public Date getHiring_date() {
		return hiring_date;
	}


	public void setHiring_date(Date hiring_date) {
		this.hiring_date = hiring_date;
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
		Surgeon other = (Surgeon) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
