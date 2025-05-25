package com.incapp.doctors.dao;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.incapp.doctors.model.Appointments;
import com.incapp.doctors.model.Doctor;
import com.incapp.doctors.model.DoctorNotAvail;

import jakarta.persistence.EntityManager;

@Repository
public class AppointmentDAO {
	@Autowired
	private EntityManager entityManager;

	public List<Appointments> getByUserEmail(String userEmail) {
		Session session = entityManager.unwrap(Session.class);
		List<Appointments> list = session.createQuery("select a from Appointments a where a.user_email = :user_email", Appointments.class)
				.setParameter("user_email", userEmail).list();
		return list;
	}
	public List<Appointments> getByDoctorEmail(String doctorEmail) {
		Session session = entityManager.unwrap(Session.class);
		List<Appointments> list = session.createQuery("select a from Appointments a where a.doctor_email = :doctor_email", Appointments.class)
				.setParameter("doctor_email", doctorEmail).list();
		return list;
	}
	public boolean delete(int id) {
		Session session = entityManager.unwrap(Session.class);
		Appointments appointment =session.get(Appointments.class,id);
		 
		if(appointment!=null) {
			session.remove(appointment);
			return true;
		}
		else
			return false;
	}
	public String addAppointment(Appointments appointment) {
		Session session = entityManager.unwrap(Session.class);

		String user_email = appointment.getUser_email();
		String doctor_email = appointment.getDoctor_email();
		java.util.Date doc_booking_date = appointment.getDoc_booking_date();
		String doc_booking_time = appointment.getDoc_booking_time();
		java.util.Date currentDate = new java.util.Date();

		if (doc_booking_date.after(currentDate) || currentDate.toString().equals(doc_booking_date.toString())) {
			List<Appointments> ll = session.createQuery(
					"select a from Appointments a where a.doc_booking_time = :doc_booking_time and a.doc_booking_date = :doc_booking_date and a.user_email = :user_email",
					Appointments.class)
					.setParameter("doc_booking_time", doc_booking_time)
					.setParameter("doc_booking_date", doc_booking_date)
					.setParameter("user_email", user_email).list();
			if(!(ll.isEmpty())) {
				return "You Already Booked!";
			}
			List<DoctorNotAvail> list = session
					.createQuery("select d from DoctorNotAvail d where d.email = :email and d.doc_date = :doc_date",
							DoctorNotAvail.class)
					.setParameter("email", doctor_email).setParameter("doc_date", doc_booking_date).list();
			if (list.isEmpty()) {

				Doctor doctor = session.get(Doctor.class, doctor_email);

				Calendar cal = Calendar.getInstance();
				cal.setTime(doc_booking_date);
				LocalDate localDate = LocalDate.of(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH) + 1,cal.get(Calendar.DAY_OF_MONTH));
				
				String day   = localDate.getDayOfWeek().toString();
				String btime=day.substring(0, 3)+"_"+doc_booking_time.substring(0, 3);
				String docTime="";
				switch(btime.toLowerCase()) {
					case "mon_mor": docTime=doctor.getDoctorAvail().getMon_mor();break;
					case "mon_eve": docTime=doctor.getDoctorAvail().getMon_eve();break;
					case "tue_mor": docTime=doctor.getDoctorAvail().getTue_mor();break;
					case "tue_eve": docTime=doctor.getDoctorAvail().getTue_eve();break;
					case "wed_mor": docTime=doctor.getDoctorAvail().getWed_mor();break;
					case "wed_eve": docTime=doctor.getDoctorAvail().getWed_eve();break;
					case "thu_mor": docTime=doctor.getDoctorAvail().getThu_mor();break;
					case "thu_eve": docTime=doctor.getDoctorAvail().getThu_eve();break;
					case "fri_mor": docTime=doctor.getDoctorAvail().getFri_mor();break;
					case "fri_eve": docTime=doctor.getDoctorAvail().getFri_eve();break;
					case "sat_mor": docTime=doctor.getDoctorAvail().getSat_mor();break;
					case "sat_eve": docTime=doctor.getDoctorAvail().getSat_eve();break;
					case "sun_mor": docTime=doctor.getDoctorAvail().getSun_mor();break;
					case "sun_eve": docTime=doctor.getDoctorAvail().getSun_eve();break;
				}
				if(!docTime.equals("")) {
					List<Appointments> l = session.createQuery(
							"select a from Appointments a where a.doc_booking_time = :doc_booking_time and a.doc_booking_date = :doc_booking_date and a.doctor_email = :doctor_email",
							Appointments.class)
							.setParameter("doc_booking_time", doc_booking_time)
							.setParameter("doc_booking_date", doc_booking_date)
							.setParameter("doctor_email", doctor_email)
							.list();
					int totalBooked = l.size();
					int maxBooking;
					if (doc_booking_time.equalsIgnoreCase("morning")) {
						maxBooking = doctor.getDoctorAvail().getMax_mor_apmt();
					} else {
						maxBooking = doctor.getDoctorAvail().getMax_eve_apmt();
					}
					if (maxBooking - totalBooked > 0) {
						session.persist(appointment);
						return "Booking Successfull!";
					} else {
						return "Booking Falied! (Slots Full)";
					}
				}else {
					return "Doctor not availbale on this time!";
				}
			} else {
				return "Doctor on Leave!";
			}
		} else {
			return "Wrong Date Selected!";
		}
	}
}
