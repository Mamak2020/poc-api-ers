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
@Table(name = "pathology")
public class Pathology {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "id_group")
	private Long idGroup;

	@Column(name = "name_pathology")
	private String namePathology;

	@Column(name = "name_group")
	private String nameGroup;

}
