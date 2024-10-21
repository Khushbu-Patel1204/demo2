package com.example.demo2.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import javax.persistence.*;

@EntityScan
@Table(name = "employee_data")
public class employeeModel{
	
	int id;
	String Name;
	String MobileNo;
	String EmailId;
	
	 @Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
	    @Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getMobileNo() {
		return MobileNo;
	}
	public void setMobileNo(String mobileNo) {
		MobileNo = mobileNo;
	}
	public String getEmailId() {
		return EmailId;
	}
	public void setEmailId(String emailId) {
		EmailId = emailId;
	}
}