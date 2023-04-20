package transplant.pojos;

import java.io.Serializable;
import java.sql.Date;

public class Transplant implements Serializable {

	private static final long serialVersionUID = -7220158433575001571L;
	private Integer id; 
	private Date registrationDate;
	private RequestedOrgan requestedOrgan; 
	private Organ organ; 
	private Theatre theatre; 
}
