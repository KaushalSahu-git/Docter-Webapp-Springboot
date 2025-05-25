package com.incapp.doctors.model;

import org.springframework.stereotype.Component;

@Component
public class DoctorAvail {
	private int id;
	
	private String mon_mor;
	private String mon_eve;
	private String tue_mor;
	private String tue_eve;
	private String wed_mor;
	private String wed_eve;
	private String thu_mor;
	private String thu_eve;
	private String fri_mor;
	private String fri_eve;
	private String sat_mor;
	private String sat_eve;
	private String sun_mor;
	private String sun_eve;
	private int max_mor_apmt;
	private int max_eve_apmt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMon_mor() {
		return mon_mor;
	}
	public void setMon_mor(String mon_mor) {
		this.mon_mor = mon_mor;
	}
	public String getMon_eve() {
		return mon_eve;
	}
	public void setMon_eve(String mon_eve) {
		this.mon_eve = mon_eve;
	}
	public String getTue_mor() {
		return tue_mor;
	}
	public void setTue_mor(String tue_mor) {
		this.tue_mor = tue_mor;
	}
	public String getTue_eve() {
		return tue_eve;
	}
	public void setTue_eve(String tue_eve) {
		this.tue_eve = tue_eve;
	}
	public String getWed_mor() {
		return wed_mor;
	}
	public void setWed_mor(String wed_mor) {
		this.wed_mor = wed_mor;
	}
	public String getWed_eve() {
		return wed_eve;
	}
	public void setWed_eve(String wed_eve) {
		this.wed_eve = wed_eve;
	}
	public String getThu_mor() {
		return thu_mor;
	}
	public void setThu_mor(String thu_mor) {
		this.thu_mor = thu_mor;
	}
	public String getThu_eve() {
		return thu_eve;
	}
	public void setThu_eve(String thu_eve) {
		this.thu_eve = thu_eve;
	}
	public String getFri_mor() {
		return fri_mor;
	}
	public void setFri_mor(String fri_mor) {
		this.fri_mor = fri_mor;
	}
	public String getFri_eve() {
		return fri_eve;
	}
	public void setFri_eve(String fri_eve) {
		this.fri_eve = fri_eve;
	}
	public String getSat_mor() {
		return sat_mor;
	}
	public void setSat_mor(String sat_mor) {
		this.sat_mor = sat_mor;
	}
	public String getSat_eve() {
		return sat_eve;
	}
	public void setSat_eve(String sat_eve) {
		this.sat_eve = sat_eve;
	}
	public String getSun_mor() {
		return sun_mor;
	}
	public void setSun_mor(String sun_mor) {
		this.sun_mor = sun_mor;
	}
	public String getSun_eve() {
		return sun_eve;
	}
	public void setSun_eve(String sun_eve) {
		this.sun_eve = sun_eve;
	}
	public int getMax_mor_apmt() {
		return max_mor_apmt;
	}
	public void setMax_mor_apmt(int max_mor_apmt) {
		this.max_mor_apmt = max_mor_apmt;
	}
	public int getMax_eve_apmt() {
		return max_eve_apmt;
	}
	public void setMax_eve_apmt(int max_eve_apmt) {
		this.max_eve_apmt = max_eve_apmt;
	}
	@Override
	public String toString() {
		return "DoctorAvail [id=" + id + ", mon_mor=" + mon_mor + ", mon_eve=" + mon_eve + ", tue_mor=" + tue_mor
				+ ", tue_eve=" + tue_eve + ", wed_mor=" + wed_mor + ", wed_eve=" + wed_eve + ", thu_mor=" + thu_mor
				+ ", thu_eve=" + thu_eve + ", fri_mor=" + fri_mor + ", fri_eve=" + fri_eve + ", sat_mor=" + sat_mor
				+ ", sat_eve=" + sat_eve + ", sun_mor=" + sun_mor + ", sun_eve=" + sun_eve + ", max_mor_apmt="
				+ max_mor_apmt + ", max_eve_apmt=" + max_eve_apmt + "]";
	}
	
}
