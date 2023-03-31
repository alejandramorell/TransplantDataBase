package transplant.pojos;

import java.io.Serializable;

public class RequestedOrgan implements Serializable{

	private static final long serialVersionUID = 235152316789116909L;
	private Integer id;
	private String type;
	private Integer patient_id; //ver ultima clase FOREIGN KEY
	
	

}
