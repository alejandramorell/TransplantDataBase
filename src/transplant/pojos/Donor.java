package transplant.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "address", "phone", "livingState", "organs"})

public class Donor implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6939768601635036985L;
	@XmlTransient
	private Integer id;
	@XmlAttribute
	private String name;
	@XmlElement
	private String address;
	@XmlElement
	private Integer phone;
	@XmlElement
	private String livingState;
	@XmlElement(name = "Organ")
	@XmlElementWrapper(name = "Organs")
	private List<Organ> organs;
	
	public Donor() {
		super();
		organs = new ArrayList<Organ>();
	}
	public Donor(Integer id, String name, String address, Integer phone, String livingState) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.livingState = livingState;
		organs = new ArrayList<Organ>();
	}
	
	public Donor(String name, String address, Integer phone, String livingState) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.livingState = livingState;
		organs = new ArrayList<Organ>();
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
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getPhone() {
		return phone;
	}
	public void setPhone(Integer phone) {
		this.phone = phone;
	}
	public String getLivingState() {
		return livingState;
	}
	public void setLivingState(String livingState) {
		this.livingState = livingState;
	}
	public List<Organ> getOrgans() {
		return organs;
	}
	public void setOrgans(List<Organ> organs) {
		this.organs = organs;
	}
	
	
	@Override
	public String toString() {
		return "Donor [id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + ", livingState="
				+ livingState + ", organs=" + organs + "]";
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
		Donor other = (Donor) obj;
		return Objects.equals(id, other.id);
	}
	
}