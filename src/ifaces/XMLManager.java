package ifaces;

import java.io.File;

import transplant.pojos.Organ;

public interface XMLManager {
	public void Organ2Xml(Organ organ);
	public Organ xml2Organ(File xml);
		
	

}
