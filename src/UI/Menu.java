package UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import ifaces.SurgeonManager;
import jdbc.JDBCSurgeonManager;
import transplant.pojos.Nurse;
import transplant.pojos.Patient;
import transplant.pojos.Surgeon;

import java.sql.Date;

public class Menu {
	
	private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static SurgeonManager surgeonMan; //it is and interface so i need to assign to surgeonMan a class that implements the interface 


	public static void main(String[] args) {
		
		while(true) {
		try {
			// TODO Auto-generated method stub
			surgeonMan = new JDBCSurgeonManager();
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
			case 3:{
				return;
			}
			}
		
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	
	public static void login()throws IOException {
		while (true) {

			System.out.println("Write the name and password of your user:");
			System.out.println("Username:");
			String username = r.readLine();
			System.out.println("Password:");
			String password = r.readLine();

			transplant.pojos.User user = userMan.login(username, password);
			
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
		System.out.println("Hiring date: ");	
		String date = r.readLine();
		LocalDate hiringDate = LocalDate.parse(date, formatter);
			
		Surgeon s = new Surgeon(name, adress, phone, speciality, Date.valueOf(hiringDate)); //look for the constructor with and without id
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
		nurseMan.insertNurse(n); //TODO nurseMan
			
	}
		
		
	public static void registerPatient() throws IOException{
			
		System.out.println("Please input patient data: ");
		System.out.println("Id: ");
		Integer id = Integer.parseInt(r.readLine());
		//TODO metodo que compruebe si hay id repetido
		System.out.println("Sex: ");
		
		String sex = r.readLine();
		System.out.println("Name: ");
		String name = r.readLine();
		System.out.println("Surname: ");
		String surname = r.readLine();
		System.out.println("Date of birth: ");	
		String date = r.readLine();
		LocalDate dateOfBirth = LocalDate.parse(date, formatter);
		System.out.println("Disease: ");
		String disease = r.readLine();
		System.out.println("Blood type: ");
		String bloodType = r.readLine();
		System.out.println("Admission date: ");
		String date2 = r.readLine();
		LocalDate admissionDate = LocalDate.parse(date2, formatter);
		System.out.println("Speciality: ");
		String address = r.readLine();
		System.out.println("Phone: ");
		Integer phone = Integer.parseInt(r.readLine());
		
		//TODO corregir esto
		Patient p = new Patient( id, sex,  name,  surname,  Date.valueOf(dateOfBirth),  disease,  bloodType, Date.valueOf(admissionDate), address, phone);
		//patientMan.insertPatient(p); //TODO patientMan
					
	}

		public static void selectSurgeon() throws IOException{
			
			
			System.out.println("Let´s search by name: ");
			System.out.println("Name: ");
			String name = r.readLine();
			List<Surgeon> listSur = surgeonMan.searchSurgeonByName(name);
			System.out.println(listSur);
			System.out.println("Please choose a surgeon, type its Id: ");
			Integer id = Integer.parseInt(r.readLine());
			//Go to the surgeon's menu
			surgeonMenu(email);
		}
		
		public static void surgeonMenu(String email) {
			//TODO offer the surgeon options
			while (true) {
				try {

					System.out.println("What do you want to do as the surgeon?:");
					System.out.println("1. Check patient information");
					System.out.println("2. View transplant information");
					
					int choice = Integer.parseInt(r.readLine());

					switch (choice) {
					case 1: {
						showPatient(id);
						break;
					}
					case 2: {
						showTransplant(id);
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
		
		public static void nurseMenu(String email) {
			while (true) {
				try {

					System.out.println("What do you want to do as the nurse?:");
					System.out.println("1. View transplant information");
					System.out.println("2. Set new transplant");
					System.out.println("3. Modify transplant");
					System.out.println("4. Assign surgeon to transplant");
					System.out.println("5. Assign nurse to transplant");
					
					
					int choice = Integer.parseInt(r.readLine());

					switch (choice) {
					case 1: {
						showTransplant(id);
						break;
					}
					case 2: {
						insertTransplant(transplant);
						break;
					}
					case 3: {
						updateTransplant(id);
						break;
					}
					case 4: {
						//TODO assignSurgeon(Surgeon id, transplant id);
						break;
					}
					case 5: {
						//TODO assignNurse(Nurse id, transplant id);
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
					System.out.println("1. Search for compatibility");
					System.out.println("2. Register new organ");
					
					int choice = Integer.parseInt(r.readLine());

					switch (choice) {
					case 1: {
						//TODO create the method "search for compatibility"
						break;
					}
					case 2: {
						insertOrgan(organ);
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
	}


