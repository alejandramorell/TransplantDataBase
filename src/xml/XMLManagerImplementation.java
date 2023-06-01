package xml;

import java.io.File;

import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import ifaces.XMLManager;
import transplant.pojos.Donor;
import transplant.pojos.Donors;

public class XMLManagerImplementation implements XMLManager {

	@Override
	public void donor2Xml(List<Donor> d) {
		try {
			// Create the JAXBContext
			JAXBContext jaxbContext = JAXBContext.newInstance(Donors.class);
			// Get the marshaller
			Marshaller marshaller = jaxbContext.createMarshaller();
			// Pretty formatting
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File file = new File("./xmls/Donors.xml");
			Donors donors = new Donors();
			donors.setDonors(d);
			//we use the POJO Donors because marshaller can only annotate 1 thing to XML
			marshaller.marshal(donors, file); 
//			marshaller.marshal(donors, System.out);   Not necessary to show in console
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Donors xml2Donor(File xml) {
		try {
		// Create the JAXBContext
		JAXBContext jaxbC = JAXBContext.newInstance(Donors.class);
		// Create the JAXBUnMarshaller
		Unmarshaller jaxbU = jaxbC.createUnmarshaller();
		// Create the object by reading from a file
		Donors donors = (Donors) jaxbU.unmarshal(xml);
		// Return donors
		return donors;

		}catch (Exception e) {
		e.printStackTrace();
		}
		return null;
	}
}
