package transplant.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;


public class Surgeon implements Serializable {

	
	private static final long serialVersionUID = 3276117047544912872L;
	private Integer id; 
	private String name; 
	private String address;
	private Integer phone;
	private String speciality;
	private Date hiring_date;
	private String email;
	
	
	public Surgeon() {
		super();
	}


	public Surgeon(Integer id, String name, String address, Integer phone, String speciality, Date hiring_date, String email) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.speciality = speciality;
		this.hiring_date = hiring_date;
		this.email = email;
	}


	public Surgeon(String name, String address, Integer phone, String speciality, Date hiring_date, String email) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.speciality = speciality;
		this.hiring_date = hiring_date;
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


	public String getAddress() {
		return address;
	}


	public void setAddress(String adress) {
		this.address = adress;
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

	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "Surgeon [id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + ", speciality="
				+ speciality + ", hiring_date=" + hiring_date + ", email= "+email+ "]";
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
