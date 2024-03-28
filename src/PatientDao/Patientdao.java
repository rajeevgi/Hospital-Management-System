package PatientDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import entity.Patient;

public class Patientdao {

	Connection conn;

	public Patientdao(Connection conn) {
		this.conn = conn;
	}

	public boolean insertPatient(Patient p) {
		boolean status = false;

		try {
			PreparedStatement ps = conn
					.prepareStatement("Insert into patients(pname, age, gender,address) values(?,?,?,?);");
			ps.setString(1, p.getPname());
			ps.setInt(2, p.getAge());
			ps.setString(3, p.getGender());
			ps.setString(4, p.getAddress());

			int p1 = ps.executeUpdate();

			if (p1 == 1) {
				status = true;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return status;
	}

	public Patient viewPatientById(int pid) {
		Patient p = null;

		try {
			PreparedStatement ps = conn.prepareStatement("select * from patients where pid = ?;");
			ps.setInt(1, pid);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				p = new Patient();
				p.setPid(rs.getInt(1));
				p.setPname(rs.getString(2));
				p.setAge(rs.getInt(3));
				p.setGender(rs.getString(4));
				p.setAddress(rs.getString(5));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return p;
	}

	public ArrayList<Patient> viewAllPatient() {
		ArrayList<Patient> patients = new ArrayList<Patient>();
		Patient p = null;

		try {
			PreparedStatement ps = conn.prepareStatement("Select * from patients;");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				// if data exists then this loop will execute and we wil create new student
				// object
				p = new Patient();
				p.setPid(rs.getInt(1));
				p.setPname(rs.getString(2));
				p.setAge(rs.getInt(3));
				p.setGender(rs.getString(4));
				p.setAddress(rs.getString(5));

				// adding student object in my ArrayList
				patients.add(p);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return patients;
	}

	public boolean updatePatients(Patient p) {
		boolean status = false;

		try {
			PreparedStatement ps = conn
					.prepareStatement("Update patients set pname = ?, age = ?, gender = ?, address = ? where pid = ?;");

			ps.setString(1, p.getPname());
			ps.setInt(2, p.getAge());
			ps.setString(3, p.getGender());
			ps.setString(4, p.getAddress());
			ps.setInt(5, p.getPid());

			int p1 = ps.executeUpdate();

			if (p1 == 1) {
				status = true;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return status;
	}

	public boolean deletePatients(int pid) {
		boolean status = false;

		try {
			PreparedStatement ps = conn.prepareStatement("Delete from patients where pid = ?;");
			ps.setInt(1, pid);

			int p1 = ps.executeUpdate();
			if (p1 == 1) {
				status = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;

	}
	
	public boolean listAllPatients() throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from patients";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);

			while (rs.next()) {
				System.out.println("ID: " + rs.getInt("pid") + ", Name: " + rs.getString("pname") + ", Age: "
						+ rs.getInt("age")+" Gender: "+rs.getString("gender")+" Address: "+rs.getString("address"));
			}

			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkPatientExists(int patient_id) throws Exception {
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
	
	public boolean listAllHospitals() throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from hospitals";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);

			while (rs.next()) {
				System.out.println("ID: " + rs.getInt("hid") + ", Name: " + rs.getString("hname") + ", Address: "
						+ rs.getString("haddress"));
			}
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean listAllDoctors() throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from doctors";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);

			while (rs.next()) {
				System.out.println("ID: " + rs.getInt("dID") + ", Name: " + rs.getString("dname") + ", Specilization: "
						+ rs.getString("specialization"));
			}
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean BookAppointment(int patient_id, int hospital_id, int doctor_id) {
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
		
		if (BookAppointment(patient_id, hospital_id, doctor_id)) {
			System.out.println("Appointment Booked successfuly!!");
		} else {
			System.out.println("Failed to book an appointment.Please try again!");
		}
		
		return false;
	}
}




