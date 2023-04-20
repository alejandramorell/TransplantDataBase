package transplant.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Patient implements Serializable{
	
	private static final long serialVersionUID = 4988466936185560423L;
	private Integer id;
	private String sex;
	private String name;
	private Date dateOfBirth;
	private String disease;
	private String bloodType;
	private Date ingressDate;
	private String adress;
	private Integer phone;
    private WaitingList waitingList; 
    private List<Organ> requestedOrgan;
    
	public Patient() {
		super();
	}

	public Patient(Integer id, String sex, String name, Date dateOfBirth, String disease, String bloodType,
			Date ingressDate, String adress, Integer phone, WaitingList waitingList, List<Organ> requestedOrgan) {
		super();
		this.id = id;
		this.sex = sex;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.disease = disease;
		this.bloodType = bloodType;
		this.ingressDate = ingressDate;
		this.adress = adress;
		this.phone = phone;
		this.waitingList = waitingList;
		this.requestedOrgan = requestedOrgan;
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

	public Date getIngressDate() {
		return ingressDate;
	}

	public void setIngressDate(Date ingressDate) {
		this.ingressDate = ingressDate;
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