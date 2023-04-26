package transplant.pojos;

import java.io.Serializable;
<<<<<<< HEAD
import java.util.List;
=======
import java.util.Objects;
>>>>>>> branch 'master' of https://github.com/alejandramorell/TransplantDataBase.git

public class Organ implements Serializable{

<<<<<<< HEAD
	private static final long serialVersionUID = -3310068065836406234L;
	private Integer id;
	private String bloodType;
	private List<Donor> donors;
	
	public Organ() {
		super();
		// TODO Auto-generated constructor stub
	}
=======
>>>>>>> branch 'master' of https://github.com/alejandramorell/TransplantDataBase.git
	
	private static final long serialVersionUID = 1681924843119231105L;
	private Integer id; 
	private String name;
	private String adress;
	private Integer phone;
	private String livingState;
	private Donor donor;
	
	public Organ() {
		super();
	}
	public Organ(Integer id, String name, String adress, Integer phone, String livingState, Donor donor) {
		super();
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.phone = phone;
		this.livingState = livingState;
		this.donor = donor;
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
