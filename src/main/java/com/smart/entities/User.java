package com.smart.entities;





import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="USER")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@NotBlank(message="Name field cannot be empty!!!")
	@Size(min=3,max=12,message="min 3 and max 12 characters are allowed!!!")
	private String name;
	@Column(unique=true)
	private String emailid;
	private int contactno;
	private String paymentmode;
	private String password;
	private String price;
	private String address;
	private String gender;
	private String description;
	private String date;
	private boolean enable;
	private String role;

	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="user")
	private List<Courses> courses=new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="user")
	private List<Contact> contacts=new ArrayList<>();
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public int getContactno() {
		return contactno;
	}

	public void setContactno(int contactno) {
		this.contactno = contactno;
	}

	public String getPaymentmode() {
		return paymentmode;
	}

	public void setPaymentmode(String paymentmode) {
		this.paymentmode = paymentmode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	
	

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	

	public List<Courses> getCourses() {
		return courses;
	}

	public void setCourses(List<Courses> courses) {
		this.courses = courses;
	}

	public User(List<Contact> contacts) {
		super();
		this.contacts = contacts;
	}

	/**/
	

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	

	public User(int id,
			@NotBlank(message = "Name field cannot be empty!!!") @Size(min = 3, max = 12, message = "min 3 and max 12 characters are allowed!!!") String name,
			String emailid, int contactno, String paymentmode, String password, String price, String address,
			String gender, String description, String date, boolean enable, String role, List<Contact> contacts) {
		super();
		this.id = id;
		this.name = name;
		this.emailid = emailid;
		this.contactno = contactno;
		this.paymentmode = paymentmode;
		this.password = password;
		this.price = price;
		this.address = address;
		this.gender = gender;
		this.description = description;
		this.date = date;
		this.enable = enable;
		this.role = role;
		this.contacts = contacts;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", emailid=" + emailid + ", contactno=" + contactno
				+ ", paymentmode=" + paymentmode + ", password=" + password + ", price=" + price + ", address="
				+ address + ", gender=" + gender + ", description=" + description + ", date=" + date + ", enable="
				+ enable + ", role=" + role + ", contacts=" + contacts + "]";
	}

	



	

	
	

	

}
