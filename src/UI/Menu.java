package UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import transplant.pojos.Surgeon;

import java.sql.Date;

public class Menu {
	private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");



	public static void main(String[] args) {
		
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
				registerSurgeon();
				//TODO register
				break;
			}
			case 2:{
				//TODO log in
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
			
			Surgeon s = new Surgeon(name, adress, phone, speciality, Date.valueOf(hiringDate))); //look for the constructor with and without id
			
			
			//TODO Insert Surgeon in the dataBase
			
			
			
		}
		

	}

