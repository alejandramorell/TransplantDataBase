package ifaces;

import java.io.File;

import transplant.pojos.Donor;

public interface XMLManager {
	public void donor2Xml(Donor d);
	public Donor xml2Donor(File xml);
		
	

}
