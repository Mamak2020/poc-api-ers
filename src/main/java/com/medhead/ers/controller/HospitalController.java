package com.medhead.ers.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.medhead.ers.model.Hospital;
import com.medhead.ers.service.HospitalService;

@RestController
public class HospitalController {
	@Autowired
	private HospitalService hospitalService;

	@GetMapping("/hospitals/{id}")
	public Optional<Hospital> getHospital(@PathVariable("id") final Long id) {
		return hospitalService.getHospital(id);
	}

	@GetMapping("/hospitals")
	public Iterable<Hospital> getHospitals() {
		return hospitalService.getHospitals();
	}

}
