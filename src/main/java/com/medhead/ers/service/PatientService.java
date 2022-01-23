package com.medhead.ers.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medhead.ers.model.Patient;
import com.medhead.ers.repository.PatientRepository;

import lombok.Data;

@Data
@Service
public class PatientService {
	@Autowired
	private PatientRepository patientRepository;

	public Optional<Patient> getPatient(final Long id) {
		return patientRepository.findById(id);
	}

	public long getCount() {
		return patientRepository.count();
	}

}
