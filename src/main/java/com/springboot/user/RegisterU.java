package com.springboot.user;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RegisterU {

	@Id
	private Long Id;
	private String firstName;
	private String lastName;
	private int phoneNumber;
	private String email;
	
	public RegisterU() {
		super();
	}

	public RegisterU(Long id, String firstName, String lastName, int phoneNumber, String email) {
		super();
		Id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
