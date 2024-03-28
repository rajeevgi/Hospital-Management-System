package mainClasses;

import java.sql.Connection;
import java.util.Scanner;

import PatientDao.Patientdao;
import Sql.connection.DBconnection;
import entity.Patient;

public class updatePatientDetails {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Patient patient = new Patient();
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter Patient id to update:");
		int p_id = sc.nextInt();

		sc.nextLine();

		System.out.println("Enter new patient name: ");
		String name = sc.nextLine();

		System.out.println("Enter new patient age: ");
		int age = sc.nextInt();

		sc.nextLine();

		System.out.println("Enter new gender: ");
		String gender = sc.nextLine();

		System.out.println("Enter new Patient address: ");
		String address = sc.nextLine();

		patient.setPid(p_id);
		patient.setPname(name);
		patient.setAge(age);
		patient.setGender(gender);
		patient.setAddress(address);
		
		
		Connection conn = DBconnection.getConnection();

		Patientdao patientDao = new Patientdao(conn);

		boolean up1 = patientDao.updatePatients(patient);

		if (up1) {
			System.out.println("Patient Data updated successfully..");
		} else {
			System.out.println("Patient doesn't exists..");

		}
	}

}
