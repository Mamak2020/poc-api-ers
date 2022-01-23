package com.medhead.ers.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.medhead.ers.model.Patient;
import com.medhead.ers.service.PatientService;

/* A SUPPRIMER => DATA confidentiel à ne pas exposer en Endpoints */
//@RequestMapping("/api")
@RestController
public class PatientController {

	@Autowired
	private PatientService patientService;

	@GetMapping("/patients/{id}")
	public Optional<Patient> getPatient(@PathVariable("id") final Long id) {
		return patientService.getPatient(id);
	}

	@GetMapping("/count")
	public Long getCount() {
		return patientService.getCount();
	}

}
