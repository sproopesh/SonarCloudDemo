package com.roop.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	private String userId;
	
	@Column
	private String fName;
	
	@Column
	private String lname;
	
	@Column
	private int salary;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public User() {
		
	}
	
	public User(String userId, String fName, String lname, int salary) {
		super();
		this.userId = userId;
		this.fName = fName;
		this.lname = lname;
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", fName=" + fName + ", lname=" + lname + ", salary=" + salary + "]";
	}


	
}
