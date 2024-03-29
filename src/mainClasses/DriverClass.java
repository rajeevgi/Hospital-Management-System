package mainClasses;

import java.util.ArrayList;
import java.util.Scanner;

import PatientDao.Patientdao;
import Sql.connection.DBconnection;
import entity.Patient;

public class DriverClass {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		boolean status = true;

		// Patient fields
		int pid, choice, age;
		String pname, gender, address;

		// Hospital fields
		int hId;
		String hname, haddress;

		// Doctor fields
		int dId;
		String dname, specialist;

		Scanner sc = new Scanner(System.in);
		Patientdao pDao = new Patientdao(DBconnection.getConnection());

		while (status) {
			System.out.println("\n\n-----------------------------------------------");
			System.out.println("~~~~~~Hospital Management System~~~~~~");
			System.out.println("-----------------------------------------------");
			System.out.println("1. Insert New Patient");
			System.out.println("2. Update Patient");
			System.out.println("3. Delete Patient");
			System.out.println("4. Get Patient By Patient ID Number");
			System.out.println("5. Get All Patients Information");
			System.out.println("6. Book an appointment....");
			System.out.println("7. Exit");
			System.out.println("-----------------------------------------------");
			System.out.println("Enter Your choice:");
			choice = sc.nextInt();

			switch (choice) {
			case 1:

				System.out.println("Please enter patient name: ");
				sc.nextLine();
				pname = sc.nextLine();

				System.out.println("Please enter patient age: ");
				age = sc.nextInt();
				sc.nextLine();

				System.out.println("Please enter patient gender: ");
				gender = sc.nextLine();

				System.out.println("Please enter address: ");
				haddress = sc.nextLine();

				Patient p1 = new Patient();
				p1.setPname(pname);
				p1.setAge(age);
				p1.setGender(gender);
				p1.setAddress(haddress);

				boolean c1 = pDao.insertPatient(p1);
				if (c1) {
					System.out.println("Data inserted successfully..");
				} else {
					System.out.println("Something wrong happen on server..");
				}
				break;

			case 2:
				System.out.println("Please enter patient Id number");
				pid = sc.nextInt();

				System.out.println("Please enter Patient new name: ");
				sc.nextLine();
				pname = sc.nextLine();

				System.out.println("Please enter patient age: ");
				age = sc.nextInt();
				sc.nextLine();

				System.out.println("Please enter patient gender: ");
				gender = sc.nextLine();

				System.out.println("Please enter patient new address: ");
				haddress = sc.nextLine();

				Patient p2 = new Patient();
				p2.setPid(pid);
				p2.setPname(pname);
				p2.setAge(age);
				p2.setGender(gender);
				p2.setAddress(haddress);

				boolean c2 = pDao.updatePatients(p2);
				if (c2) {
					System.out.println("Data updated successfully..");
				} else {
					System.out.println("Patient doesn't exists. Pls check patient id..");
				}

				break;

			case 3:

				System.out.println("Please enter patient Id number: ");
				pid = sc.nextInt();

				boolean c3 = pDao.deletePatients(pid);
				if (c3) {
					System.out.println("Data deleted successfully..");
				} else {
					System.out.println("Patient doesn't exists. Pls check patient Id num..");
				}

				break;

			case 4:

				System.out.println("Please enter patient Id number");
				pid = sc.nextInt();

				Patient pt = pDao.viewPatientById(pid);
				if (pt == null) {
					System.out.println("Patient doesn't exists");
				} else {
					System.out.println("-----------------------------------------------");
					System.out.println("~~Patient Information~~");
					System.out.println("-----------------------------------------------");
					System.out.println("PId: " + pt.getPid());
					System.out.println("Name: " + pt.getPname());
					System.out.println("Age: " + pt.getAge());
					System.out.println("Gender: " + pt.getGender());
					System.out.println("Address: " + pt.getAddress());
					System.out.println("-----------------------------------------------");
				}
				break;

			case 5:

				ArrayList<Patient> patients = pDao.viewAllPatient();
				if (patients.isEmpty()) {
					System.out.println("No Patient found");
				} else {
					for (Patient pt1 : patients) {
						System.out.println("-----------------------------------------------");
						System.out.println("~~Patient Information~~");
						System.out.println("-----------------------------------------------");
						System.out.println("Pid: " + pt1.getPid());
						System.out.println("Name: " + pt1.getPname());
						System.out.println("Age: " + pt1.getAge());
						System.out.println("Gender: " + pt1.getGender());
						System.out.println("Address: " + pt1.getAddress());
						System.out.println("-----------------------------------------------");

					}
				}

				break;

			case 6:
				
				System.out.println("Book an appointment for a check up:");
				pDao.listAllPatients();
				
				System.out.println("Enter patient ID:");
				pid = sc.nextInt();

				if(!(pDao.checkPatientsExists(pid))){
					System.out.println("Patient doesn't Exists in a data, please register first...");
					break;
				}

				System.out.println("Available Hospitals are: ");
				pDao.listAllHospitals();
				
				System.out.println("Enter Hospital ID:");
				hId = sc.nextInt();
				
				System.out.println("Available Doctors are: ");
				pDao.listAllDoctors();
				
				System.out.println("Enter Doctor's ID:");
				dId = sc.nextInt();
				
				pDao.BookAppointment(pid, hId, dId);
				
				System.out.println("Appointment Booked Successfully....");
				break;

			case 7:
				System.out.println("\n-----------------------------------------------");

				System.out.println("Thanks. Visit Again!!!");
				System.out.println("-----------------------------------------------");

				status = false;

				break;

			default:

			}
		}

	}

}
