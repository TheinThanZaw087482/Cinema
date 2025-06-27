package com.cinema.models;

import java.time.LocalDate;

public class User {
	private int userID;
	private String UserName;
	private String Email;
	private String UserType;
	private String phone;
	private LocalDate Hire_Date;
	private String address;
	private int Salary;
	private String Status;
	private String ImagePath;
	private String Pass;
	public User(int userID, String userName, String email, String userType, String phone, LocalDate hire_Date,
			String address, int salary, String status, String imagePath,String Pass) {
		super();
		this.userID = userID;
		UserName = userName;
		Email = email;
		UserType = userType;
		this.phone = phone;
		Hire_Date = hire_Date;
		this.address = address;
		Salary = salary;
		Status = status;
		ImagePath = imagePath;
		this.Pass = Pass;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	
	public String getPass() {
		return Pass;
	}
	public void setPass(String pass) {
		Pass = pass;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getUserType() {
		return UserType;
	}
	public void setUserType(String userType) {
		UserType = userType;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public LocalDate getHire_Date() {
		return Hire_Date;
	}
	public void setHire_Date(LocalDate hire_Date) {
		Hire_Date = hire_Date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getSalary() {
		return Salary;
	}
	public void setSalary(int salary) {
		Salary = salary;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getImagePath() {
		return ImagePath;
	}
	public void setImagePath(String imagePath) {
		ImagePath = imagePath;
	}
	
	
	
	

}
