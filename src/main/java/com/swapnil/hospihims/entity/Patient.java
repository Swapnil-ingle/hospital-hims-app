package com.swapnil.hospihims.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="patients")
@ApiModel(description = "All details about the patient.")
public class Patient {
	@Id
	@Column(name="Identifier")
	@ApiModelProperty("The database generated patient ID")
	private int identifier;
	
	@Column(name="Patient_Id")
	@ApiModelProperty("Unique patient ID")
	private String pid;
	
	@Column(name="Name")
	@ApiModelProperty("The patient's name")
	private String name;
	
	@Column(name="Address")
	@ApiModelProperty(value = "The patient's address", allowEmptyValue = true)
	private String address;
	
	@Column(name="City")
	@ApiModelProperty(value = "The patient's city", allowEmptyValue = true)
	private String city;
	
	@Column(name="Contact_Number")
	@ApiModelProperty(value = "The patient's contact number", allowEmptyValue = true)
	private Integer contactNumber;
	
	@Column(name="Email")
	@ApiModelProperty("The patient's email address")
	private String email;
	
	public Patient() {
	
	}

	public Patient(String name, String address, String city, int contactNumber, String email) {
		this.name = name;
		this.address = address;
		this.city = city;
		this.contactNumber = contactNumber;
		this.email = email;
	}

	public int getIdentifier() {
		return identifier;
	}

	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@Override
	public String toString() {
		return "Patient [identifier=" + identifier + ", patient_Id=" + pid + ", name=" + name + ", address="
				+ address + ", city=" + city + ", contactNumber=" + contactNumber + ", email=" + email + "]";
	}
}
