package com.github.swapnil.hims.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Table(name = "patient", uniqueConstraints = @UniqueConstraint(columnNames = "Patient_Id"))
@Data
@ApiModel(description = "All details about the patient.")
public class Patient {
	@Id
	@Column(name = "Identifier")
	@GeneratedValue
	@ApiModelProperty("The database generated patient ID")
	private Long id;

	@Column(name = "Patient_Id")
	@ApiModelProperty("Unique patient ID")
	@NotNull
	private String patientId;

	@Column(name = "Name")
	@ApiModelProperty("The patient's name")
	private String name;

	@Column(name = "Address")
	@ApiModelProperty(value = "The patient's address", allowEmptyValue = true)
	private String address;

	@Column(name = "City")
	@ApiModelProperty(value = "The patient's city", allowEmptyValue = true)
	private String city;

	@Column(name = "Contact_Number")
	@ApiModelProperty(value = "The patient's contact number", allowEmptyValue = true)
	private Long contactNumber;

	@Column(name = "Email") @NotNull
	@ApiModelProperty("The patient's email address")
	private String email;
}
