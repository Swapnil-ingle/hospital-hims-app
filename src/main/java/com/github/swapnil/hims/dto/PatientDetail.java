package com.github.swapnil.hims.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.swapnil.hims.entities.Patient;

import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientDetail {
	private Long identifier;

	private String patientId;

	private String name;

	private String address;

	private String city;

	private Long contactNumber;

	private String email;

	public static PatientDetail from(Patient patient) {
		PatientDetail detail = new PatientDetail();

		detail.setIdentifier(patient.getId());
		detail.setPatientId(patient.getPatientId());
		detail.setName(patient.getName());
		detail.setAddress(patient.getAddress());
		detail.setCity(patient.getCity());
		detail.setContactNumber(patient.getContactNumber());
		detail.setEmail(patient.getEmail());

		return detail;
	}

	public Patient toPatient() {
		Patient patient = new Patient();
		patient.setPatientId(this.patientId);
		patient.setName(this.name);
		patient.setAddress(this.address);
		patient.setCity(this.city);
		patient.setContactNumber(this.contactNumber);
		patient.setEmail(this.email);
		return patient;
	}
}
