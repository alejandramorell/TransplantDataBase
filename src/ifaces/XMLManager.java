package ifaces;

import java.io.File;
import java.util.List;

import transplant.pojos.Donor;
import transplant.pojos.Donors;

public interface XMLManager {
	public void donor2Xml(List<Donor> d);
	public Donors xml2Donor(File xml);

}
