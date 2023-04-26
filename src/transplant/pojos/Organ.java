package transplant.pojos;

import java.io.Serializable;
import java.util.List;

public class Organ implements Serializable{

	private static final long serialVersionUID = -3310068065836406234L;
	private Integer id;
	private String bloodType;
	private List<Donor> donors;
	
	public Organ() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
