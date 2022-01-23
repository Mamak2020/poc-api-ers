package com.medhead.ers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medhead.ers.model.HospitalPathology;
import com.medhead.ers.model.HospitalPathologyDto;
import com.medhead.ers.repository.HospitalPathologyRepository;

import lombok.Data;

@Data
@Service
public class HospitalPathologyService {
	@Autowired
	private HospitalPathologyRepository hospitalPathologyRepository;

	/*
	 * public Iterable<HospitalPathology> getHospitalPathology(final Long
	 * idHospital) { return
	 * hospitalPathologyRepository.findByIdHospital(idHospital); }
	 */

	public Iterable<HospitalPathology> getHospitalPathologies() {
		return hospitalPathologyRepository.findAll();
	}

	public int bookingBed(final long id) {

		return hospitalPathologyRepository.bookingBed(id);
	}

	public List<HospitalPathologyDto> findAvailableHospitals(Long idZone, Long idPathology) {
		return hospitalPathologyRepository.findAvailableHospitals(idZone, idPathology);
	}
}
