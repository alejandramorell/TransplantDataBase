package ifaces;

import java.io.File;
import java.util.List;

import transplant.pojos.Donor;

public interface XMLManager {
	public void donor2Xml(List<Donor> d);
	public Donor xml2Donor(File xml);
		
	

}
