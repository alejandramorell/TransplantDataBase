package xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import ifaces.XMLManager;
import transplant.pojos.Organ;

public class XMLManagerImplementation implements XMLManager {

	@Override
	public void Organ2Xml(Organ organ) {
		try {
			// Create the JAXBContext
			JAXBContext jaxbContext = JAXBContext.newInstance(Organ.class);
			// Get the marshaller
			Marshaller marshaller = jaxbContext.createMarshaller();
			// Pretty formatting
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File file = new File("./xmls/Organs.xml");
			marshaller.marshal(organ, file);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Organ xml2Organ(File xml) {
		// TODO Auto-generated method stub
		return null;
	}

}
