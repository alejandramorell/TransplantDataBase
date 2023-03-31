package transplant.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Donor implements Serializable{
	
	private static final long serialVersionUID = -8208053288652575928L;
	private Integer id;
	private String name;
	private String adress;
	private Integer phone;
	private String livingState;
	
	public Donor(Integer id, String name, String adress, Integer phone, String livingState) {
		super();
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.phone = phone;
		this.livingState = livingState;
	}

	public Donor() {
		super();
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
	public String getLivingState() {
		return livingState;
	}
	public void setLivingState(String livingState) {
		this.livingState = livingState;
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
