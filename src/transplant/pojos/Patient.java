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
	private String disease;
	private String bloodType;
	private Date admissionDate;
	//TODO change ingressDate to admissionDate everywhere
	private String address;
	private Integer phone;
    private WaitingList waitingList; 
    private List<Organ> requestedOrgan;
    
	public Patient() {
		super();
		requestedOrgan = new ArrayList<Organ>();
	}
	public Patient(Integer id, String sex, String name, String surname, Date dateOfBirth, String disease, String bloodType,
			Date admissionDate, String address, Integer phone) {
		super();
		this.id = id;
		this.sex = sex;
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.disease = disease;
		this.bloodType = bloodType;
		this.admissionDate = admissionDate;
		this.address = address;
		this.phone = phone;
		requestedOrgan = new ArrayList<Organ>();
	
	}

	public Patient(String sex, String name, String surname, Date dateOfBirth, String disease, String bloodType,
			Date admissionDate, String address, Integer phone, WaitingList waitingList) {
		super();
		this.sex = sex;
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.disease = disease;
		this.bloodType = bloodType;
		this.admissionDate = admissionDate;
		this.address = address;
		this.phone = phone;
		this.waitingList = waitingList;
		requestedOrgan = new ArrayList<Organ>();
	
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

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
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

	public void setAddress(String adress) {
		this.address = adress;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public WaitingList getWaitingList() {
		return waitingList;
	}

	public void setWaitingList(WaitingList waitingList) {
		this.waitingList = waitingList;
	}

	public List<Organ> getRequestedOrgan() {
		return requestedOrgan;
	}

	public void setRequestedOrgan(List<Organ> requestedOrgan) {
		this.requestedOrgan = requestedOrgan;
	}
	
	

	@Override
	public String toString() {
		return "Patient [id=" + id + ", sex=" + sex + ", name=" + name + ", surname=" + surname + ", dateOfBirth="
				+ dateOfBirth + ", disease=" + disease + ", bloodType=" + bloodType + ", admissionDate=" + admissionDate
				+ ", address=" + address + ", phone=" + phone + ", requestedOrgan=" + requestedOrgan + "]";
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