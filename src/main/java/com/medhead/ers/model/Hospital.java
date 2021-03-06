package com.medhead.ers.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "hospital")
public class Hospital {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "id_zone")
	private Long idZone;

	private String name;

	private String address;

	private Double latitude;

	private Double longitude;

	@Column(name = "is_available_bed")
	private Boolean isAvailableBed;

}
