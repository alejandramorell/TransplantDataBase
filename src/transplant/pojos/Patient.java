package transplant.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Patient implements Serializable{
	
	private static final long serialVersionUID = -6037002940808004660L;
	
	private Integer id;
	private String sex;
	private String name;
	private String surname;
	private Date dateOfBirth;
	private String bloodType;
	private Date admissionDate;
	private String address;
	private Integer phone;
	private List<Transplant> transplants;
    
	public Patient() {
		super();
	}
	public Patient(Integer id, String sex, String name, String surname, Date dateOfBirth, String bloodType,
			Date admissionDate, String address, Integer phone) {
		super();
		this.id = id;
		this.sex = sex;
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.bloodType = bloodType;
		this.admissionDate = admissionDate;
		this.address = address;
		this.phone = phone;
	
	}

	public Patient(String sex, String name, String surname, Date dateOfBirth, String bloodType,
			Date admissionDate, String address, Integer phone) {
		super();
		this.sex = sex;
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.bloodType = bloodType;
		this.admissionDate = admissionDate;
		this.address = address;
		this.phone = phone;
	
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	} 
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public Date getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(Date ingressDate) {
		this.admissionDate = ingressDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	
	

	@Override
	public String toString() {
		return "Patient [id=" + id + ", sex=" + sex + ", name=" + name + ", surname=" + surname + ", dateOfBirth="
				+ dateOfBirth + ", bloodType=" + bloodType + ", admissionDate=" + admissionDate
				+ ", address=" + address + ", phone=" + phone + "]";
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
		Patient other = (Patient) obj;
		return Objects.equals(id, other.id);
	}	
	
}