package transplant.pojos;

import java.io.Serializable;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "bloodType", "transplant"})

public class Organ implements Serializable{

	private static final long serialVersionUID = -3310068065836406234L;
	
	@XmlTransient	
	private Integer id;
	@XmlAttribute
	private String type;
	@XmlElement
	private String bloodType;
	@XmlTransient 
	//we made it Transient, because the donor is already shown before, and if nor we will be showing each donor and its organs infinite times. 
	private Donor donor;
	@XmlElement
	private Transplant transplant;
	
	
	
	public Organ(Integer id,String type, String bloodType) {
		super();
		this.id = id;
		this.type = type;
		this.bloodType = bloodType;
		
	}
	
	public Organ(String type, String bloodType, Donor donor) {
		super();
		this.type = type;
		this.bloodType = bloodType;
		this.donor = donor;
	}

	public Organ() {
		super();
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

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public Donor getDonor() {
		return donor;
	}

	public void setDonor(Donor donor) {
		this.donor = donor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	

	@Override
	public String toString() {
		return "Organ [id=" + id + ", type=" + type + ", bloodType=" + bloodType + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Organ other = (Organ) obj;
		return Objects.equals(id, other.id);
	}	

}
