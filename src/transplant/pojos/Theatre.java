package transplant.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Theatre implements Serializable {

	
	private static final long serialVersionUID = 3081191966672640367L;
	private Integer id;
	private Integer floor;
	private Integer number;
	
	public Theatre() {
		super();
	}
	
	public Theatre(Integer id, Integer floor, Integer number) {
		super();
		this.id = id;
		this.floor = floor;
		this.number = number;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getFloor() {
		return floor;
	}
	public void setFloor(Integer floor) {
		this.floor = floor;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	
	
	@Override
	public String toString() {
		return "Theatre [id=" + id + ", floor=" + floor + ", number=" + number + "]";
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
		Theatre other = (Theatre) obj;
		return Objects.equals(id, other.id);
	}


}
