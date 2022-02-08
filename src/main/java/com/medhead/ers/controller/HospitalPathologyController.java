package com.medhead.ers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.medhead.ers.model.HospitalPathology;
import com.medhead.ers.service.HospitalPathologyService;

@RestController
public class HospitalPathologyController {
	@Autowired
	private HospitalPathologyService hospitalPathologyService;

	@GetMapping("/pathologies")
	public Iterable<HospitalPathology> getHospitalPathologies() {
		return hospitalPathologyService.getHospitalPathologies();
	}

	@GetMapping("/pathologies/{idPathology}")
	public Iterable<HospitalPathology> getHospitalByPathology(
			@PathVariable("idPathology") final Long idPathology) {
		return hospitalPathologyService.getHospitalByPathology(idPathology);
	}
}