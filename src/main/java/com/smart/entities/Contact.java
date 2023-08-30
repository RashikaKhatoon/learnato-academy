package com.smart.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="CONTACT")
public class Contact {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cId;
	private String name;
	@Column(unique=true)
	private String email;
	private String gender;
	@Column(length=500)
	private String about;
	private String phone;
	private String address;
	private String paymentmode;
	private String price;
	@ManyToOne
	private User user;
	
	public int getcId() {
		return cId;
	}
	
	
	public void setcId(int cId) {
		this.cId = cId;
	}
	
	public String getPaymentmode() {
		return paymentmode;
	}


	public void setPaymentmode(String paymentmode) {
		this.paymentmode = paymentmode;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public Contact(int cId, String name, String email, String gender, String about, String phone, String address,
			String paymentmode, String price, User user) {
		super();
		this.cId = cId;
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.about = about;
		this.phone = phone;
		this.address = address;
		this.paymentmode = paymentmode;
		this.price = price;
		this.user = user;
	}


	public Contact() {
		super();
	}
	@Override
	public String toString() {
		return "Contact [cId=" + cId + ", name=" + name + ", email=" + email + ", gender=" + gender + ", about=" + about
				+ ", phone=" + phone + ", address=" + address + "]";
	}
	
	
	

}
