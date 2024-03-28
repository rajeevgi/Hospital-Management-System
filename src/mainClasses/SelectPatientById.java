package mainClasses;

import java.util.Scanner;
import entity.Patient;
import PatientDao.Patientdao;
import Sql.connection.DBconnection;

public class SelectPatientById {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Patientdao patientDao = new Patientdao(DBconnection.getConnection());
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Patient Id:");
		int pid = sc.nextInt();
		
		Patient pt = patientDao.viewPatientById(pid);
		
		if(pt == null) {
			System.out.println("Patient doesn't exists");
		}else {
			System.out.println("-------------------------------------------");
			System.out.println("~~Patient Information~~");
			System.out.println("-------------------------------------------");
			System.out.println("Patient Id: "+ pt.getPid());
			System.out.println("Name: "+pt.getPname());
			System.out.println("Age:"+pt.getAge());
			System.out.println("Gender:"+pt.getGender());
			System.out.println("Address: " + pt.getAddress());
			System.out.println("-------------------------------------------");
		}

	}

}
