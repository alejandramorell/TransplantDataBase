package UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import ifaces.*;
import jdbc.*;
import jpa.JPAUserManager;
import transplant.pojos.Donor;
import transplant.pojos.Nurse;
import transplant.pojos.Organ;
import transplant.pojos.Patient;
import transplant.pojos.Surgeon;
import transplant.pojos.Theatre;
import transplant.pojos.Transplant;
import transplant.pojos.User;
import xml.XMLManagerImplementation;

import java.sql.Date;

public class Menu {
	
	private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	private static SurgeonManager surgeonMan; //it is and interface so i need to assign to surgeonMan a class that implements the interface 
	private static NurseManager nurseMan;
	private static PatientManager patientMan;
	private static OrganManager organMan;
	private static TransplantManager transplantMan;
	private static UserManager userMan;
	private static XMLManager xmlMan = new XMLManagerImplementation();
	
	public static void main(String[] args) {
		ConnectionManager conMan = new ConnectionManager();
		surgeonMan = new JDBCSurgeonManager(conMan.getConnection());
		nurseMan = new JDBCNurseManager(conMan.getConnection());
		patientMan = new JDBCPatientManager(conMan.getConnection());
		organMan = new JDBCOrganManager(conMan.getConnection());
		transplantMan = new JDBCTransplantManager(conMan.getConnection(), organMan, patientMan, transplantMan);
		userMan = new JPAUserManager();
		
		
		while(true) {
		try {
			// TODO Auto-generated method stub
			
			System.out.println("Welcome to the transplant application for hospitals");
			System.out.println("Choose an option:");
			System.out.println("1: LOG IN");
			System.out.println("2: REGISTER");
			System.out.println("0: EXIT");			
			
			int choice =Integer.parseInt(r.readLine());
			
			switch(choice) {
			case 1:{
				login();
				break;
			}
			case 2:{
				//TODO register
				register();
				break;
			}
			case 0:{
				conMan.closeConnection();
				break;
			}
			}
		
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("Please type a numer!");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	//TODO finish this method
	public static void login()throws IOException {
		while (true) {

			System.out.println("Write the name and password of your user:");
			System.out.println("Username:");
			String username = r.readLine();
			System.out.println("Password:");
			String password = r.readLine();

			User user = userMan.login(username, password);
			
			if (user != null) {
				if (user.getRole().getName().equals("surgeon")) {
					surgeonMenu(user.getEmail());
				}
				
				else if (user.getRole().getName().equals("nurse")) {
					nurseMenu(user.getEmail());
				}
				else if (user.getRole().getName().equals("transplant unit")) {
					transplantUnitMenu(user.getEmail()); 
				}
			}
			else {
				System.out.println("Wrong username/password combination.");
			}
		}
	}
	
	
		public static void register() throws IOException {
			while (true) {
				
				System.out.println("Who do you want to register as?:");
				System.out.println("1. Surgeon");
				System.out.println("2. Nurse");
				System.out.println("3. Patient");
			
				int choice = Integer.parseInt(r.readLine());

				switch (choice) {
				case 1: {
					registerSurgeon();
					break;
				}
				case 2: {
					registerNurse();
					break;
				}
				case 3: {
					registerPatient();
					break;
				}
				case 0: {
					return;
				}
				}
			}
		}

	
	public static void registerSurgeon() throws IOException{
			
		System.out.println("Please input surgeon data: ");
		System.out.println("Name: ");
		String name = r.readLine();
		System.out.println("Adress: ");
		String adress = r.readLine();
		System.out.println("Phone: ");
		Integer phone = Integer.parseInt(r.readLine());
		System.out.println("Speciality: ");
		String speciality = r.readLine();
		System.out.println("Hiring date (yyyy-MM-dd): ");	
		String date = r.readLine();
		Date hiringDate = Date.valueOf(date);
			
		Surgeon s = new Surgeon(name, adress, phone, speciality, hiringDate); //look for the constructor with and without id
		surgeonMan.insertSurgeon(s);
			
	}
		
	public static void registerNurse() throws IOException{
			
		System.out.println("Please input nurse data: ");
		System.out.println("Name: ");
		String name = r.readLine();
		
		System.out.println("Adress: ");
		String adress = r.readLine();
		
		System.out.println("Phone: ");
		Integer phone = Integer.parseInt(r.readLine());
		
		Nurse n = new Nurse(name, adress, phone);
		nurseMan.insertNurse(n); 
			
	}
	
	public static void registerDonor() throws IOException{
		
		System.out.println("Please input donor data: ");
		System.out.println("Name: ");
		String name = r.readLine();
		
		System.out.println("Adress: ");
		String adress = r.readLine();
		
		System.out.println("Phone: ");
		Integer phone = Integer.parseInt(r.readLine());
		
		System.out.println("Living state");
		String livingState = r.readLine();
		
		Donor d = new Donor(name, adress, phone, livingState);
		organMan.insertDonor(d); 
			
	}
	
	public static void registerOrgan(int id) throws IOException{
		
		System.out.println("Please input new organ's data: ");
		System.out.println("Type: ");
		String type = r.readLine();
		
		System.out.println("Blood type: ");
		String bloodType = r.readLine();
				
	
		Organ o = new Organ(type, bloodType, organMan.getDonor(id));
		organMan.insertOrgan(o);
			
	}
	

	public static void registerTransplant() throws IOException{
		
		System.out.println("Please input new transplant's data: ");
		System.out.println("Date (yyyy-MM-dd): ");
		String d = r.readLine();
		Date date = Date.valueOf(d);
		
		System.out.println("Type of organ");
		String type = r.readLine();
		List<Organ> listOrgan = organMan.searchOrganByType(type);
		System.out.println(listOrgan);
		System.out.println("Please, choose the id of the requested organ. ");
		Integer requestedOrganId = Integer.parseInt(r.readLine());
		Organ o = organMan.getOrgan(requestedOrganId);
		
		System.out.println("Name of the patient");
		String patientName = r.readLine();
		List<Patient> patients = patientMan.searchPatientByName(patientName);
		System.out.println(patients);
		
		System.out.println("Input the id of the patient");
		Integer patientId = Integer.parseInt(r.readLine());
		Patient p = patientMan.getPatient(patientId);
		
		System.out.println("Request");
		String request = r.readLine();
		
		System.out.println("Input the theatre information: ");
		Integer floor = Integer.parseInt(r.readLine());
		Integer number = Integer.parseInt(r.readLine());
		Theatre theatre = new Theatre(floor, number);
		
		
		Transplant transplant = new Transplant(date,o,p, request, theatre);
		transplantMan.insertTransplant(transplant); 
			
	}
		
		
	public static void registerPatient() throws IOException{
			
		System.out.println("Please input patient data: ");
		System.out.println("Id: ");
		Integer id = Integer.parseInt(r.readLine());
		//TODO check if the patie's id already exists
		System.out.println("Sex: ");
		
		String sex = r.readLine();
		System.out.println("Name: ");
		String name = r.readLine();
		System.out.println("Surname: ");
		String surname = r.readLine();
		System.out.println("Date of birth (yyyy-MM-dd): ");	
		String date = r.readLine();
		Date dateOfBirth = Date.valueOf(date);
		System.out.println("Disease: ");
		String disease = r.readLine();
		System.out.println("Blood type: ");
		String bloodType = r.readLine();
		System.out.println("Admission date (yyyy-MM-dd): ");
		String date2 = r.readLine();
		Date admissionDate = Date.valueOf(date2);
		System.out.println("Speciality: ");
		String address = r.readLine();
		System.out.println("Phone: ");
		Integer phone = Integer.parseInt(r.readLine());
		
		
		Patient p = new Patient( id, sex,  name,  surname,  dateOfBirth,  disease,  bloodType, admissionDate, address, phone);
		patientMan.insertPatient(p);
					
	}

		//TODO why do we want this method?
		public static void selectSurgeon() throws IOException{
			
			System.out.println("Let´s search by name: ");
			System.out.println("Name: ");
			String name = r.readLine();
			List<Surgeon> listSur = surgeonMan.searchSurgeonByName(name);
			System.out.println(listSur);
			System.out.println("Please choose a surgeon, type its Id: ");
			Integer id = Integer.parseInt(r.readLine());
			
		}
		
		public static void surgeonMenu(String email) {
			while (true) {
				try {
					System.out.println("Welcome surgeon");
					System.out.println("Choose an option please:");
					System.out.println("1. Check patient information");
					System.out.println("2. View transplant information");
					
					int choice = Integer.parseInt(r.readLine());

					switch (choice) {
					case 1: {
						System.out.println("Input the name of the patient to see their information");
						String name = r.readLine();
						List<Patient> patientsList = patientMan.searchPatientByName(name);
						System.out.println(patientsList);
						System.out.println("Please input the patient's id to check his/her information: ");
						int id = Integer.parseInt(r.readLine());
						checkPatient(id);
						break;
					}
					case 2: {
						List<Transplant> transplants = transplantMan.getAllTransplants();
						System.out.println(transplants);
						System.out.println("Please input the transplant´s id to check it's information: ");
						int transplantId = Integer.parseInt(r.readLine());
						checkTransplant(transplantId);
						break;
					}
					case 0: {
						return;
					}
					}

				} catch (NumberFormatException e) {
					System.out.println("Incorrect number");
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("I/O Exception.");
					e.printStackTrace();
				}
			}
		}
		
		public static void checkPatient(int id) throws IOException {
			System.out.println("Patient: " + id + " information: ");
			Patient patient = patientMan.getPatient(id);
			System.out.println(patient);
			
		}
		
		//TODO 
		public static void checkTransplant(int id) throws IOException {
			System.out.println("transplant: " + id + " information: ");
			Transplant transplant = transplantMan.getTransplant(id);
			System.out.println(transplant);
			
		}
		
		
		public static void donor2Xml(int id) throws IOException {
			System.out.println("Your organs in XML are:");
			List<Organ> listOrgan = organMan.searchOrganByDonor(id);
			Donor donor = organMan.getDonor(id);
			donor.setOrgans(listOrgan);
			xmlMan.donor2Xml(donor);
		}
		
		public static void nurseMenu(String email) {
			while (true) {
				try {

					System.out.println("What do you want to do as the nurse?:");
					System.out.println("1. View transplant information");
					System.out.println("2. Register patient");
					System.out.println("3. Register new transplant");
					System.out.println("4. Modify patient data");
					System.out.println("5. Assign surgeon to transplant");
					
					
					int choice = Integer.parseInt(r.readLine());

					switch (choice) {
					case 1: {
						List<Transplant> transplants = transplantMan.getAllTransplants();
						System.out.println(transplants);
						System.out.println("Please input the transplant´s id to check it's information: ");
						int transplantId = Integer.parseInt(r.readLine());
						checkTransplant(transplantId);
						break;
					}
					case 2: {
						registerPatient();
					}
						
					case 3: {
						registerTransplant();
						break;
					}
				
					case 4: {
						System.out.println("Input the patient's name to change his information");
						String patientName = r.readLine();
						List<Patient> patients = patientMan.searchPatientByName(patientName);
						System.out.println(patients);
						System.out.println("Now input the patient´s id");
						int patientId = Integer.parseInt(r.readLine());
						updatePatient(patientId);
						break;
					}
					case 5: {
						System.out.println("Introduce the surgeon's name to assign him to a transplant:");
						String name = r.readLine();
						List<Surgeon> surgeons = surgeonMan.searchSurgeonByName(name);
						System.out.println(surgeons);
						
						System.out.println("Input the id of the surgeon:");
						int surgeonId = Integer.parseInt(r.readLine());
						assignTransplant(surgeonId);
						
						break;
					}
					case 0: {
						return;
					}
					}

				} catch (NumberFormatException e) {
					System.out.println("Incorrect number");
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("I/O Exception.");
					e.printStackTrace();
				}
			}
		}
		
		
		public static void transplantUnitMenu(String email) {
			
			while (true) {
				try {

					System.out.println("What do you want to do as the transplant unit?:");
					System.out.println("1. Search for compatibility(assign organ to patient)");
					System.out.println("2. Register donor");
					System.out.println("3. Register new organ(donor must be registered first)");
					System.out.println("3. Remove organ");
					System.out.println("4. Remove patient");
					
					
					int choice = Integer.parseInt(r.readLine());

					switch (choice) {
					case 1: {
						System.out.println("Patient's name: ");
						String name = r.readLine();
						List<Patient> patients = patientMan.searchPatientByName(name);
						System.out.println(patients);
						System.out.println("Please input the patient's id: ");
						int patientId = Integer.parseInt(r.readLine());
						Patient t = patientMan.getPatient(patientId);
						assignOrgan(patientId, t.getBloodType());
						break;
					}
					case 2: {
						registerDonor();
						break;
					}
					case 3:{
						System.out.println("Input the name of the donor to register a new organ");  
						String donorName = r.readLine();
						List<Donor> donorList = organMan.searchDonorByName(donorName);
						System.out.println(donorList);
						System.out.println("Select the donor, type it's id");
						int donorId = Integer.parseInt(r.readLine());
						registerOrgan(donorId);
						break;
						
					}
					case 4:{
						System.out.println("Please input the organ type remove it: ");
						String type = r.readLine();
						List<Organ> organs = organMan.searchOrganByType(type);
						System.out.println(organs);
						System.out.println("Now please input the id of the organ to remove it");
						int organId = Integer.parseInt(r.readLine());
						removeOrgan(organId);
					}
					case 5:{
						
						System.out.println("Please input the patient's name to remove it: ");
						String patientName = r.readLine();
						List<Patient> patients = patientMan.searchPatientByName(patientName);
						System.out.println(patients);
						System.out.println("Now select the patient to remove by indicating it's id");
						int patientId = Integer.parseInt(r.readLine());
						removePatient(patientId);
						
					}
					case 0: {
						return;
					}
					}

				} catch (NumberFormatException e) {
					System.out.println("Incorrect number");
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("I/O Exception.");
					e.printStackTrace();
				}
				
				
			}
		}
		
		
			
			public static void removeOrgan(int id) throws IOException {
				organMan.removeOrgan(id);
				System.out.println("The organ has been removed. :(");
			}
			
			public static void removePatient(int id) throws IOException {
				patientMan.removePatient(id);
				System.out.println("The patient has been removed. :(");
			}
			
			public static void updatePatient(int patientId) throws IOException {
				Patient p = patientMan.getPatient(patientId);
				System.out.println("Type the new data, or press enter to keep actual data");
				System.out.println("Sex (" + p.getSex() + "):");
				String sex = r.readLine();
				if (!sex.equals("")) {
					p.setSex(sex);
				}
				System.out.println("Name (" + p.getName() + "):");
				String name = r.readLine();
				if (!name.equals("")) {
					p.setName(name);
				}
				System.out.println("Surname (" + p.getSurname() + "):");
				String surName = r.readLine();
				if (!surName.equals("")) {
					p.setSurname(surName);
				}
				System.out.println("Date of birth (yyyy-MM-dd) (" + p.getDateOfBirth() + "):");
				String dob = r.readLine();
				if (!dob.equals("")) {
		
					Date dobDate = Date.valueOf(dob);
					p.setDateOfBirth(dobDate);
				}
				System.out.println("Disease (" + p.getDisease() + "):");
				String disease = r.readLine();
				if (!disease.equals("")) {
					p.setDisease(disease);
				}
				System.out.println("Blood type (" + p.getBloodType() + "):");
				String bloodType = r.readLine();
				if (!bloodType.equals("")) {
					p.setBloodType(bloodType);
				}
				System.out.println("Admission date (yyyy-MM-dd) (" + p.getAdmissionDate() + "):");
				String date = r.readLine();
				if (!date.equals("")) {
		
					Date admissionDate = Date.valueOf(date);
					p.setAdmissionDate(admissionDate);
				}
				System.out.println("Phone (" + p.getPhone() + "):");
				Integer phone = Integer.parseInt(r.readLine());
				if (!phone.equals("")) {
		
					p.setPhone(phone);
				}
				patientMan.updatePatient(p);
			}

			
			public static void assignOrgan(int patientId, String patientBloodType) throws IOException {
				System.out.println("Let's search a compatible organ, please introduce the organ that the patient needs" );
				String type= r.readLine();
				List<Organ> listOrgan = organMan.searchOrganByType(type);
				System.out.println(listOrgan);
				System.out.println("Please choose an organ taking into account that the patient has a blood type: " + patientBloodType + " , type its Id:");
				Integer organId = Integer.parseInt(r.readLine());
				// Go to the owner's menu
				organMan.assignOrganToPatient(organId, patientId);
			}
			
			public static void assignTransplant(int surgeonId) throws IOException {
				List<Transplant> transplants = transplantMan.getAllTransplants();
				System.out.println(transplants);
				System.out.println("Please input the transplant´s id to assign a surgeon for it: ");
				int transplantId = Integer.parseInt(r.readLine());
				nurseMan.assignSurgeonTransplant(surgeonId, transplantId);
				
			}
		}
	


