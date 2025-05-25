package com.incapp.doctors.dao;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.incapp.doctors.model.Appointments;
import com.incapp.doctors.model.Doctor;
import com.incapp.doctors.model.DoctorAvail;
import com.incapp.doctors.model.DoctorNotAvail;
import com.incapp.doctors.model.User;

import jakarta.persistence.EntityManager;

@Repository
public class DoctorDAO {
	@Autowired
	private EntityManager entityManager;

	public void save(Doctor doctor) {
		Session session = entityManager.unwrap(Session.class);
		DoctorAvail doctorAvail = new DoctorAvail();
		doctor.setDoctorAvail(doctorAvail);
		session.persist(doctorAvail);
		session.persist(doctor);
	}
	public void updateDoctor(Doctor doctor) {
		Session session = entityManager.unwrap(Session.class);
		Doctor d = session.get(Doctor.class, doctor.getEmail());
		d.setPhone(doctor.getPhone());
		d.setDob(doctor.getDob());
		d.setQualification(doctor.getQualification());
		d.setExperience(doctor.getExperience());
		d.setSpeciality(doctor.getSpeciality());
		d.setState(doctor.getState());
		d.setCity(doctor.getCity());
		d.setArea(doctor.getArea());
		d.setGender(doctor.getGender());
		session.persist(d);
	}

	public void updateDocAvail(String email, DoctorAvail doctorAvail) {
		// returns current session
		Session session = entityManager.unwrap(Session.class);
		Doctor doctor = session.get(Doctor.class, email);
		DoctorAvail da = doctor.getDoctorAvail();
		da.setMax_eve_apmt(doctorAvail.getMax_eve_apmt());
		da.setMax_mor_apmt(doctorAvail.getMax_mor_apmt());
		da.setMon_eve(doctorAvail.getMon_eve());
		da.setMon_mor(doctorAvail.getMon_mor());
		da.setTue_eve(doctorAvail.getTue_eve());
		da.setTue_mor(doctorAvail.getTue_mor());
		da.setWed_eve(doctorAvail.getWed_eve());
		da.setWed_mor(doctorAvail.getWed_mor());
		da.setThu_eve(doctorAvail.getThu_eve());
		da.setThu_mor(doctorAvail.getThu_mor());
		da.setFri_eve(doctorAvail.getFri_eve());
		da.setFri_mor(doctorAvail.getFri_mor());
		da.setSat_eve(doctorAvail.getSat_eve());
		da.setSat_mor(doctorAvail.getSat_mor());
		da.setSun_eve(doctorAvail.getSun_eve());
		da.setSun_mor(doctorAvail.getSun_mor());
		session.persist(da);
	}

	public List<Doctor> get() {
		Session session = entityManager.unwrap(Session.class);
		List<Doctor> list = session.createQuery("from Doctor", Doctor.class).list();
		return list;
	}

	public List<Doctor> get(String name) {
		Session session = entityManager.unwrap(Session.class);
		List<Doctor> list = session.createQuery("select d from Doctor d where d.name like :name", Doctor.class)
				.setParameter("name", "%" + name + "%").list();
		return list;
	}

	public List<Doctor> get(String state, String city) {
		Session session = entityManager.unwrap(Session.class);
		List<Doctor> list = session
				.createQuery("select d from Doctor d where d.state = :state and d.city = :city", Doctor.class)
				.setParameter("state", state).setParameter("city", city).list();
		return list;
	}

	public List<Doctor> getBySpeciality(String speciality) {
		Session session = entityManager.unwrap(Session.class);
		List<Doctor> list = session.createQuery("select d from Doctor d where d.speciality = :speciality", Doctor.class)
				.setParameter("speciality", speciality).list();
		return list;
	}

	public Doctor getByEmail(String email) {
		Session session = entityManager.unwrap(Session.class);
		Doctor doctor = session.get(Doctor.class, email);
		return doctor;
	}

	public List<DoctorNotAvail> getDocNotAvail(String email) {
		Session session = entityManager.unwrap(Session.class);
		List<DoctorNotAvail> list = session
				.createQuery("select e from DoctorNotAvail e where e.email = :email", DoctorNotAvail.class)
				.setParameter("email", email).list();
		return list;
	}

	public boolean addDocNotAvail(DoctorNotAvail doctorNotAvail) {
		Session session = entityManager.unwrap(Session.class);
		
		List<DoctorNotAvail> list = session
				.createQuery("select e from DoctorNotAvail e where e.email = :email and e.doc_date = :doc_date", DoctorNotAvail.class)
				.setParameter("doc_date", doctorNotAvail.getDoc_date())
				.setParameter("email", doctorNotAvail.getEmail()).list();
		if(list.isEmpty()) {
			session.persist(doctorNotAvail);
			return true;
		}else {
			return false;
		}
	}

	
	public Doctor login(String email,String password) {
		Session session= entityManager.unwrap(Session.class);
		List<Doctor> list= session.createQuery("from Doctor d where d.email= :email and d.password= :password ",Doctor.class)
				.setParameter("email", email)
				.setParameter("password", password)
				.list();
		if(list.isEmpty()) {
			return null;
		}else {
			return list.get(0);
		}
	}
}
