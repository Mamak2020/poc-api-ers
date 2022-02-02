package com.medhead.ers.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "hospital_pathology")
public class HospitalPathology {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; /* Service Id */

	@Column(name = "id_pathology")
	private Long idPathology;

	@Column(name = "available_beds")
	private Long availableBeds;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_hospital")
	Hospital hospital;

	/*
	 * @Column(name = "id_hospital") private Long idHospital;
	 */
}
