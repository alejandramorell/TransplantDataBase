package transplant.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Organ implements Serializable{

	private static final long serialVersionUID = -3310068065836406234L;
	private Integer id;
	private String type;
	private String bloodType;
	private Donor donor;
	
	public Organ(String type, String bloodType, Donor donor) {
		super();
		this.type = type;
		this.bloodType = bloodType;
		this.donor = donor;
	}

	public Organ() {
		super();
		// TODO Auto-generated constructor stub
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
