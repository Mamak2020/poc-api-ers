package com.medhead.ers.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medhead.ers.model.Hospital;
import com.medhead.ers.repository.HospitalRepository;

import lombok.Data;

@Data
@Service
public class HospitalService {
	@Autowired
	private HospitalRepository hospitalRepository;

	public Optional<Hospital> getHospital(final Long id) {
		return hospitalRepository.findById(id);
	}

	public Iterable<Hospital> getHospitals() {
		return hospitalRepository.findAll();
	}

}
