package mainClasses;

import java.sql.Connection;
import java.util.Scanner;

import PatientDao.Patientdao;
import Sql.connection.DBconnection;
import entity.Patient;

public class insertPatient {
	public static void main(String[] args) {
		Patient patient = new Patient();  // patient object
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter Patient Name:");
		String pname = sc.nextLine();

		System.out.println("Enter Patient age:");
		int age = sc.nextInt();
		
		sc.nextLine();

		System.out.println("Enter Patient gender:");
		String gender = sc.nextLine();

		System.out.println("Enter Address:");
		String address = sc.nextLine();

		patient.setPname(pname);
		patient.setAge(age);
		patient.setGender(gender);
		patient.setAddress(address);

		Connection conn = DBconnection.getConnection(); // connection establish.

		Patientdao patientdao = new Patientdao(conn);

		boolean ins1 = patientdao.insertPatient(patient);

		if (ins1) {
			System.out.println("Data inserted successfully..");
		} else {
			System.out.println("Something bad happens on server..");

		}
	}
}
