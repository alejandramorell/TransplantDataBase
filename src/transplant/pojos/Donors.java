package transplant.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Donors")
@XmlType(propOrder = { "donors" })
public class Donors implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5717331298800528705L;
	
	@XmlElement(name = "Donor")
	private List<Donor> donors;

	public Donors() {
		super();
		this.donors = new ArrayList<Donor>();
	}

	public List<Donor> getDonors() {
		return donors;
	}

	public void setDonors(List<Donor> donors) {
		this.donors = donors;
	}

	@Override
	public int hashCode() {
		return Objects.hash(donors);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Donors other = (Donors) obj;
		return Objects.equals(donors, other.donors);
	}
	
	
}
