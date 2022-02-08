package com.medhead.ers.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medhead.ers.model.Emergency;
import com.medhead.ers.service.EmergencyService;

@RestController
public class EmergencyController {
	@Autowired
	private EmergencyService emergencyService;

	@GetMapping("/emergencies/{id}")
	public Optional<Emergency> getEmergency(@PathVariable("id") final Long id) {
		return emergencyService.getEmergency(id);
	}

	@GetMapping("/emergencies")
	public Iterable<Emergency> getEmergencies() {
		return emergencyService.getEmergencies();
	}

	@PostMapping("/emergencies")
	public Emergency createEmergencyLog(@RequestBody Emergency emergency) {
		try {
			return emergencyService.requestMedicalEmergency(emergency);
		} catch (Exception e) {
			throw e;
		}
	}
}
