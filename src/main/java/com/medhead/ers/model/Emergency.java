package com.medhead.ers.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "emergency_log")
public class Emergency {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "id_zone")
	private Long idZone;
	@Column(name = "id_responder")
	private Long idResponder;

	@Column(name = "id_patient")
	private Long idPatient;
	@Column(name = "patient_first_name")
	private String patientFirstName;
	@Column(name = "patient_last_name")
	private String patientLastName;
	@Column(name = "patient_gender")
	private char patientGender;
	@Column(name = "patient_age")
	private Long patientAge;
	@Column(name = "patient_address")
	private String patientAddress;
	@Column(name = "patient_latitude")
	private Double patientLatitude;
	@Column(name = "patient_longitude")
	private Double patientLongitude;
	@Column(name = "id_pathology")
	private Long idPathology;

	@Column(name = "id_hospital")
	private Long idHospital;
	@Column(name = "hospital_name")
	private String hospitalName;
	@Column(name = "hospital_address")
	private String hospitalAddress;
	@Column(name = "hospital_longitude")
	private Double hospitalLongitude;
	@Column(name = "hospital_latitude")
	private Double hospitalLatitude;
	@Column(name = "hospital_service_name")
	private String hospitalServiceName;
	@Column(name = "id_hospital_service")
	private Long idHospitalService;
	@Column(name = "distance")
	private Long distance;

	private String instructions;

	@Column(name = "dt_request")
	private Date dtRequest;
	@Column(name = "dt_response")
	private Date dtResponse;

	@Column(name = "duration")
	private Long duration;

}
