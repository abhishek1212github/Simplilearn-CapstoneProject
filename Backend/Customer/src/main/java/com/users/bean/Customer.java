package com.users.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Customer {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Integer userId;
	   private String email;
	   private String password;
	   private String name;
	   private String contact;
	   private String address;
	   
	   
	   
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(Integer userId, String email, String password, String name, String contact, String address) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.name = name;
		this.contact = contact;
		this.address = address;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	   
}
