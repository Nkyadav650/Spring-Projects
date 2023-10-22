package com.employee.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {
	@Id
private int id;
private String name;
private String password;
private String email;
private int mobile;
private String gender;
private String address;
private String city;
private String state;
private String country;
public Employee() {
	super();
}
public Employee(int id, String name, String password, String email, int mobile, String gender, String address,
		String city, String state, String country) {
	super();
	this.id = id;
	this.name = name;
	this.password = password;
	this.email = email;
	this.mobile = mobile;
	this.gender = gender;
	this.address = address;
	this.city = city;
	this.state = state;
	this.country = country;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public int getMobile() {
	return mobile;
}
public void setMobile(int mobile) {
	this.mobile = mobile;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
@Override
public String toString() {
	return "Employee [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", mobile="
			+ mobile + ", gender=" + gender + ", address=" + address + ", city=" + city + ", state=" + state
			+ ", country=" + country + "]";
}

}
