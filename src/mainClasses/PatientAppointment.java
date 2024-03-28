package mainClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import PatientDao.Patientdao;
import Sql.connection.DBconnection;

public class PatientAppointment {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			Scanner sc = new Scanner(System.in);

			Connection conn = DBconnection.getConnection();
			
			Patientdao patientDao = new Patientdao(conn);


			System.out.println("Enter Patient ID to book an appointment:");
			int patient_id = sc.nextInt();

			if (!checkPatientExists(conn, patient_id)) {
				System.out.println("Patient Does not exists.Please register first!");
				return;
			}

			System.out.println("Check the available hospitals");
			listAllHospitals(conn);

			System.out.println("Enter a hospital id you want to visit");
			int hospital_id = sc.nextInt();

			System.out.println("Check the available doctors");
			listAllDoctors(conn);

			System.out.println("Enter a doctor id you want to appoint!");
			int doctor_id = sc.nextInt();

			if (BookAppointment(conn, patient_id, hospital_id, doctor_id)) {
				System.out.println("Appointment Booked successfuly!!");
			} else {
				System.out.println("Failed to book an appointment.Please try again!");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private static void listAllHospitals(Connection conn) throws Exception {
		// TODO Auto-generated method stub
		String q1 = "select * from hospitals";

		try {
			PreparedStatement ps = conn.prepareStatement(q1);
			ResultSet rs = ps.executeQuery(q1);

			while (rs.next()) {
				System.out.println("ID: " + rs.getInt("hid") + ", Name: " + rs.getString("hname") + ", Address: "
						+ rs.getString("haddress"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private static void listAllDoctors(Connection conn) throws Exception {
		// TODO Auto-generated method stub
		String q2 = "select * from doctors";

		try {
			PreparedStatement ps = conn.prepareStatement(q2);
			ResultSet rs = ps.executeQuery(q2);

			while (rs.next()) {
				System.out.println("ID: " + rs.getInt("dID") + ", Name: " + rs.getString("dname") + ", Specilization: "
						+ rs.getString("specialization"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

//	private static boolean checkDoctorExists(Connection conn, int doctor_id) {
//		// TODO Auto-generated method stub
//		String sql = "select * from doctors where dID = ?";
//		
//		try {
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setInt(1, doctor_id);
//			
//			ResultSet rs = ps.executeQuery();
//			
//			if (rs.next()) {
//				return rs.getInt("count") > 0;
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return false;
//	}

	private static boolean checkPatientExists(Connection conn, int patient_id) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from patients where pid = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, patient_id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	private static boolean BookAppointment(Connection conn, int patient_id, int hospital_id, int doctor_id) {
		// TODO Auto-generated method stub
		String query = "insert into appointments(patient_id,doctor_id,hospital_id, appointment_date) values(?,?,?,now())";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, patient_id);
			ps.setInt(2, doctor_id);
			ps.setInt(3, hospital_id);
			
			int affectedRows = ps.executeUpdate();
			return affectedRows > 0;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return false;
	}
}
