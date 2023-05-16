package xml;

import java.io.File;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

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
//			marshaller.marshal(donor, System.out); //Not necessary to show in console
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Donor xml2Donor(File xml) {
		try {
		// Create the JAXBContext
		JAXBContext jaxbC = JAXBContext.newInstance(Donor.class);
		// Create the JAXBUnMarshaller
		Unmarshaller jaxbU = jaxbC.createUnmarshaller();
		// Create the object by reading from a file
		Donor donor = (Donor) jaxbU.unmarshal(xml);
		// Return donor
		return donor;

	}catch (Exception e) {
		e.printStackTrace();
	}
		return null;
}}
