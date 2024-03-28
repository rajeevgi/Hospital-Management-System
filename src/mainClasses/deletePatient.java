package mainClasses;

import java.util.Scanner;

import PatientDao.Patientdao;
import Sql.connection.DBconnection;

public class deletePatient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Patient id :");
		int pid = sc.nextInt();
		
		Patientdao patientDao = new Patientdao(DBconnection.getConnection());
		
		boolean delete = patientDao.deletePatients(pid);
		
		if(delete) {
			System.out.println("Patient successfully Removed");
		}else {
			System.out.println("Patient doesn't exists");
		}
	}

}
