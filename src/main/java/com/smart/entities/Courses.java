package com.smart.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "COURSES")
public class Courses {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cid;
	 @Column( nullable = false)
	private String cname;

	private String tname;
	private String level;
	private String cimage;
	private String cvideo;
	
	private String date;
	@Column(length = 1000)
	private String cdescription;
	private String pdf;

	@ManyToOne
	private User user;

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getCimage() {
		return cimage;
	}

	public void setCimage(String cimage) {
		this.cimage = cimage;
	}

	public String getCvideo() {
		return cvideo;
	}

	public void setCvideo(String cvideo) {
		this.cvideo = cvideo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCdescription() {
		return cdescription;
	}

	public void setCdescription(String cdescription) {
		this.cdescription = cdescription;
	}

	public String getPdf() {
		return pdf;
	}

	public void setPdf(String pdf) {
		this.pdf = pdf;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	public Courses(int cid, String cname, String tname, String level, String cimage, String cvideo, String date,
			String cdescription, String pdf, User user) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.tname = tname;
		this.level = level;
		this.cimage = cimage;
		this.cvideo = cvideo;
		this.date = date;
		this.cdescription = cdescription;
		this.pdf = pdf;
		this.user = user;
	}

	public Courses() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return this.cid==((Courses)obj).getCid();
	}

	@Override
	public String toString() {
		return "Courses [cid=" + cid + ", cname=" + cname + ", tname=" + tname + ", level=" + level + ", cimage="
				+ cimage + ", cvideo=" + cvideo + ", date=" + date + ", cdescription=" + cdescription + ", pdf=" + pdf
				+ ", user=" + user + "]";
	}


}
