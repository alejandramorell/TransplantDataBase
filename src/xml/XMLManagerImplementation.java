package xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import ifaces.XMLManager;
import transplant.pojos.Donor;

public class XMLManagerImplementation implements XMLManager {

	@Override
	public void donor2Xml(Donor donor) {
		try {
			// Create the JAXBContext
			JAXBContext jaxbContext = JAXBContext.newInstance(Donor.class);
			// Get the marshaller
			Marshaller marshaller = jaxbContext.createMarshaller();
			// Pretty formatting
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File file = new File("./xmls/Organs.xml");
			marshaller.marshal(donor, file);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Donor xml2Donor(File xml) {
		// TODO Auto-generated method stub
		
		return null;
	}

}
